#include "cpu.h"

/*
 * Reading of CPU KPIs: /LinuxKernel/fs/proc/stat.c
 */

#ifdef arch_idle_time

static u64 get_idle_time(int cpu)
{
        u64 idle;

        idle = kcpustat_cpu(cpu).cpustat[CPUTIME_IDLE];
        if (cpu_online(cpu) && !nr_iowait_cpu(cpu))
        idle += arch_idle_time(cpu);
        return idle;
}

static u64 get_iowait_time(int cpu)
{
        u64 iowait;

        iowait = kcpustat_cpu(cpu).cpustat[CPUTIME_IOWAIT];
        if (cpu_online(cpu) && nr_iowait_cpu(cpu))
        iowait += arch_idle_time(cpu);
        return iowait;
}

#else

static u64 get_idle_time(int cpu)
{
        u64 idle, idle_usecs = -1ULL;

        if (cpu_online(cpu))
                idle_usecs = get_cpu_idle_time_us(cpu, NULL);

        if (idle_usecs == -1ULL)
                /* !NO_HZ or cpu offline so we can rely on cpustat.idle */
                idle = kcpustat_cpu(cpu).cpustat[CPUTIME_IDLE];
        else
                idle = idle_usecs * NSEC_PER_USEC;

        return idle;
}

static u64 get_iowait_time(int cpu)
{
        u64 iowait, iowait_usecs = -1ULL;

        if (cpu_online(cpu))
                iowait_usecs = get_cpu_iowait_time_us(cpu, NULL);

        if (iowait_usecs == -1ULL)
                /* !NO_HZ or cpu offline so we can rely on cpustat.iowait */
                iowait = kcpustat_cpu(cpu).cpustat[CPUTIME_IOWAIT];
        else
                iowait = iowait_usecs * NSEC_PER_USEC;

        return iowait;
}

#endif

static u64 nsec_to_clock_t2(u64 x)
{
//#if (NSEC_PER_SEC % USER_HZ) == 0
        return div_u64(x, NSEC_PER_SEC / USER_HZ);
//#elif (USER_HZ % 512) == 0
//        return div_u64(x * USER_HZ / 512, NSEC_PER_SEC / 512);
//#else
//        /*
//         * max relative error 5.7e-8 (1.8s per year) for USER_HZ <= 1024,
//         * overflow after 64.99 years.
//         * exact for HZ=60, 72, 90, 120, 144, 180, 300, 600, 900, ...
//         */
//        return div_u64(x * 9, (9ull * NSEC_PER_SEC + (USER_HZ / 2)) / USER_HZ);
//#endif
}

typedef struct {
        u64 user;
        u64 nice;
        u64 system;
        u64 idle;
        u64 iowait;
        u64 irq;
        u64 softirq;
        u64 steal;
        u64 guest;
        u64 guest_nice;
} cpu_values;

static int cpu_count = 0;
static cpu_values *old_cpu_values = NULL;
static cpu_values *old_total_values = NULL;
static cpu_values *current_cpu_values = NULL;
static cpu_values *current_total_values = NULL;

static void read_cpu_values(cpu_values *cpu, cpu_values *total)
{
        int cpu_id;
        total->user = 0;
        total->nice = 0;
        total->system = 0;
        total->idle = 0;
        total->iowait = 0;
        total->irq = 0;
        total->softirq = 0;
        total->steal = 0;
        total->guest = 0;
        total->guest_nice = 0;

        for_each_possible_cpu(cpu_id)
        {
                cpu[cpu_id].user = kcpustat_cpu(cpu_id).cpustat[CPUTIME_USER];
                cpu[cpu_id].nice = kcpustat_cpu(cpu_id).cpustat[CPUTIME_NICE];
                cpu[cpu_id].system =
                                kcpustat_cpu(cpu_id).cpustat[CPUTIME_SYSTEM];
                cpu[cpu_id].idle = get_idle_time(cpu_id);
                cpu[cpu_id].iowait = get_iowait_time(cpu_id);
                cpu[cpu_id].irq = kcpustat_cpu(cpu_id).cpustat[CPUTIME_IRQ];
                cpu[cpu_id].softirq =
                                kcpustat_cpu(cpu_id).cpustat[CPUTIME_SOFTIRQ];
                cpu[cpu_id].steal = kcpustat_cpu(cpu_id).cpustat[CPUTIME_STEAL];
                cpu[cpu_id].guest = kcpustat_cpu(cpu_id).cpustat[CPUTIME_GUEST];
                cpu[cpu_id].guest_nice =
                                kcpustat_cpu(cpu_id).cpustat[CPUTIME_GUEST_NICE];

                total->user += cpu[cpu_id].user;
                total->nice += cpu[cpu_id].nice;
                total->system += cpu[cpu_id].system;
                total->idle += cpu[cpu_id].idle;
                total->iowait += cpu[cpu_id].iowait;
                total->irq += cpu[cpu_id].irq;
                total->softirq += cpu[cpu_id].softirq;
                total->steal += cpu[cpu_id].steal;
                total->guest += cpu[cpu_id].guest;
                total->guest_nice += cpu[cpu_id].guest_nice;
        }
}

static u64 calc_total_time_difference(cpu_values *o, cpu_values *c)
{
        u64 total_time_difference;
        total_time_difference = c->user - o->user;
        total_time_difference += c->system - o->system;
        total_time_difference += c->idle - o->idle;
        total_time_difference += c->iowait - o->iowait;
        total_time_difference += c->irq - o->irq;
        total_time_difference += c->softirq - o->softirq;
        total_time_difference += c->steal - o->steal;
        total_time_difference += c->guest - o->guest;
        total_time_difference += c->guest_nice - o->guest_nice;
        return total_time_difference;
}

static u64 calc_percentage(u64 old_value, u64 current_value, u64 total_value)
{
        return (current_value - old_value);
}

static void print_cpu_stat(void)
{
        int cpu_id;
        u64 total_time_difference;
        u64 user;
        u64 nice;
        u64 system;
        u64 idle;
        u64 iowait;
        u64 irq;
        u64 softirq;
        u64 steal;
        u64 guest;
        u64 guest_nice;

        current_total_values = (cpu_values *) kmalloc(sizeof(cpu_values),
                        GFP_KERNEL);
        current_cpu_values = (cpu_values *) kmalloc(
                        cpu_count * sizeof(cpu_values), GFP_KERNEL);

        read_cpu_values(current_cpu_values, current_total_values);
        total_time_difference = calc_total_time_difference(old_total_values,
                        current_total_values);

        printk("cpu   %llu %llu %llu %llu %llu %llu %llu %llu %llu %llu\n",
                        nsec_to_clock_t2(current_total_values->user),
                        nsec_to_clock_t2(current_total_values->nice),
                        nsec_to_clock_t2(current_total_values->system),
                        nsec_to_clock_t2(current_total_values->idle),
                        nsec_to_clock_t2(current_total_values->iowait),
                        nsec_to_clock_t2(current_total_values->irq),
                        nsec_to_clock_t2(current_total_values->softirq),
                        nsec_to_clock_t2(current_total_values->steal),
                        nsec_to_clock_t2(current_total_values->guest),
                        nsec_to_clock_t2(current_total_values->guest_nice));
        printk("cpu   total: %llu\n", nsec_to_clock_t2(total_time_difference));

        user = calc_percentage(old_total_values->user,
                        current_total_values->user, total_time_difference);
        nice = calc_percentage(old_total_values->nice,
                        current_total_values->nice, total_time_difference);
        system = calc_percentage(old_total_values->system,
                        current_total_values->system, total_time_difference);
        idle = calc_percentage(old_total_values->idle,
                        current_total_values->idle, total_time_difference);
        iowait = calc_percentage(old_total_values->iowait,
                        current_total_values->iowait, total_time_difference);
        irq = calc_percentage(old_total_values->irq, current_total_values->irq,
                        total_time_difference);
        softirq = calc_percentage(old_total_values->softirq,
                        current_total_values->softirq, total_time_difference);
        steal = calc_percentage(old_total_values->steal,
                        current_total_values->steal, total_time_difference);
        guest = calc_percentage(old_total_values->guest,
                        current_total_values->guest, total_time_difference);
        guest_nice = calc_percentage(old_total_values->guest_nice,
                        current_total_values->guest_nice,
                        total_time_difference);
        printk("cpu   %llu %llu %llu %llu %llu %llu %llu %llu %llu %llu\n",
                        nsec_to_clock_t2(user), nsec_to_clock_t2(nice),
                        nsec_to_clock_t2(system), nsec_to_clock_t2(idle),
                        nsec_to_clock_t2(iowait), nsec_to_clock_t2(irq),
                        nsec_to_clock_t2(softirq), nsec_to_clock_t2(steal),
                        nsec_to_clock_t2(guest), nsec_to_clock_t2(guest_nice));

        for_each_online_cpu(cpu_id)
        {
                printk(
                                "cpu%d %llu %llu %llu %llu %llu %llu %llu %llu %llu %llu\n",
                                cpu_id,
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].user),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].nice),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].system),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].idle),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].iowait),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].irq),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].softirq),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].steal),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].guest),
                                nsec_to_clock_t2(
                                                current_cpu_values[cpu_id].guest_nice));
                total_time_difference = calc_total_time_difference(
                                &old_cpu_values[cpu_id],
                                &current_cpu_values[cpu_id]);
                printk("cpu%d total: %llu\n", cpu_id,
                                nsec_to_clock_t2(total_time_difference));
        }

        kfree(old_cpu_values);
        kfree(old_total_values);
        old_cpu_values = current_cpu_values;
        old_total_values = current_total_values;

}

static int cpu_thread_fn(void* data)
{
        while (!kthread_should_stop()) {
                print_cpu_stat();
                ssleep(5);
        }
        pr_info("Thread Stopping\n");
        do_exit(0);
        return 0;
}

static struct task_struct *cpu_thread;

void sake_start_cpu_monitor(void)
{
        int cpu_id;
        char our_thread[9] = "sake-cpu";

        pr_info("Starting CPU monitor...");
// Count number of CPUs (not hot plugging supported)
        for_each_online_cpu(cpu_id)
        {
                cpu_count++;
        }
// Initialize variables
        old_total_values = (cpu_values *) kmalloc(sizeof(cpu_values),
                        GFP_KERNEL);
        old_cpu_values = (cpu_values *) kmalloc(cpu_count * sizeof(cpu_values),
                        GFP_KERNEL);
        read_cpu_values(old_cpu_values, old_total_values);
// Start CPU monitor thread
        cpu_thread = kthread_create(cpu_thread_fn, NULL, our_thread);
        if (cpu_thread) {
                wake_up_process(cpu_thread);
        }
        pr_info("CPU monitor started.");
}

void sake_stop_cpu_monitor(void)
{
        pr_info("Stopping CPU monitor...");
        // Stop the CPU monitor thread
        if (cpu_thread) {
                kthread_stop(cpu_thread);
                pr_info("CPU monitor thread stopped");
        }
        // Free variables
        kfree(old_total_values);
        old_total_values = NULL;
        kfree(old_cpu_values);
        old_cpu_values = NULL;
        pr_info("CPU monitor stopped.");
}

