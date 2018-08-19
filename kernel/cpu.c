#include "cpu.h"

static int logcpu = 1;
module_param(logcpu, int, S_IRUGO | S_IWUSR);
MODULE_PARM_DESC(logcpu,"Monitor CPU.");

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

static u64 nsec_to_msec(u64 x)
{
        return div_u64(x, 1000000);
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
static cpu_values *current_cpu_values = NULL;

static void read_cpu_values(cpu_values *cpu)
{
        int cpu_id;
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
        }
}

static struct file *cpu_log_file = NULL;

static void print_cpu_stat(void)
{
        struct timespec current_time;
        struct cpu_stat_output {
                u32 cpu_id;
                u32 user;
                u32 nice;
                u32 system;
                u32 idle;
                u32 iowait;
                u32 irq;
                u32 softirq;
                u32 steal;
                u32 guest;
                u32 guest_nice;
        } output;
        struct sysinfo i;
        long available;

        mm_segment_t oldfs;
        void *swap_ptr;

        getnstimeofday(&current_time);
        read_cpu_values(current_cpu_values);
        si_meminfo(&i);
        si_swapinfo(&i);
        available = si_mem_available();

        oldfs = get_fs();
        set_fs(get_ds());

        if (cpu_log_file) {
                printk("Writing to cpu.log %d...", logcpu);
                write_file(cpu_log_file, (const char *) &current_time,
                                sizeof(current_time));
                write_file(cpu_log_file, (const char *) &cpu_count,
                                sizeof(int));
                for_each_online_cpu(output.cpu_id)
                {
                        output.user =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].user
                                                                        - old_cpu_values[output.cpu_id].user);
                        output.nice =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].nice
                                                                        - old_cpu_values[output.cpu_id].nice);
                        output.system =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].system
                                                                        - old_cpu_values[output.cpu_id].system);
                        output.idle =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].idle
                                                                        - old_cpu_values[output.cpu_id].idle);
                        output.iowait =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].iowait
                                                                        - old_cpu_values[output.cpu_id].iowait);
                        output.irq =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].irq
                                                                        - old_cpu_values[output.cpu_id].irq);
                        output.softirq =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].softirq
                                                                        - old_cpu_values[output.cpu_id].softirq);
                        output.steal =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].steal
                                                                        - old_cpu_values[output.cpu_id].steal);
                        output.guest =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].guest
                                                                        - old_cpu_values[output.cpu_id].guest);
                        output.guest_nice =
                                        nsec_to_msec(
                                                        current_cpu_values[output.cpu_id].guest_nice
                                                                        - old_cpu_values[output.cpu_id].guest_nice);
                        printk("cpu%d %u %u %u %u %u %u %u %u %u %u\n",
                                        output.cpu_id, output.user, output.nice,
                                        output.system, output.idle,
                                        output.iowait, output.irq,
                                        output.softirq, output.steal,
                                        output.guest, output.guest_nice);
                        write_file(cpu_log_file, (const char *) &output,
                                        sizeof(output));
                }
        }

        set_fs(oldfs);

        swap_ptr = old_cpu_values;
        old_cpu_values = current_cpu_values;
        current_cpu_values = swap_ptr;
}

static int cpu_thread_fn(void* data)
{
        while (!kthread_should_stop()) {
                ssleep(5);
                print_cpu_stat();
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
        // Count number of CPUs (no CPU hot plugging supported!)
        for_each_online_cpu(cpu_id)
        {
                cpu_count++;
        }
        // Initialize variables
        old_cpu_values = (cpu_values *) kmalloc(cpu_count * sizeof(cpu_values),
                        GFP_KERNEL);
        current_cpu_values = (cpu_values *) kmalloc(
                        cpu_count * sizeof(cpu_values), GFP_KERNEL);
        read_cpu_values(old_cpu_values);
        // Create and/or open file...
        if (!cpu_log_file) {
                cpu_log_file = open_file("/var/log/cpu.log",
                                O_APPEND | O_WRONLY | O_CREAT, 0644);
        }
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
        // Close the file
        if (cpu_log_file) {
                close_file(cpu_log_file);
                cpu_log_file = NULL;
        }
        // Free variables
        kfree(current_cpu_values);
        current_cpu_values = NULL;
        kfree(old_cpu_values);
        old_cpu_values = NULL;
        pr_info("CPU monitor stopped.");
}

