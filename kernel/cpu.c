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
#if (NSEC_PER_SEC % USER_HZ) == 0
        return div_u64(x, NSEC_PER_SEC / USER_HZ);
#elif (USER_HZ % 512) == 0
        return div_u64(x * USER_HZ / 512, NSEC_PER_SEC / 512);
#else
        /*
         * max relative error 5.7e-8 (1.8s per year) for USER_HZ <= 1024,
         * overflow after 64.99 years.
         * exact for HZ=60, 72, 90, 120, 144, 180, 300, 600, 900, ...
         */
        return div_u64(x * 9, (9ull * NSEC_PER_SEC + (USER_HZ / 2)) / USER_HZ);
#endif
}


static void print_cpu_stat(void)
{
        int cpu_id, j;
        u64 user = 0;
        u64 nice = 0;
        u64 system = 0;
        u64 idle = 0;
        u64 iowait = 0;
        u64 irq = 0;
        u64 softirq = 0;
        u64 steal = 0;
        u64 guest = 0;
        u64 guest_nice = 0;
        u64 sum = 0;
        u64 sum_softirq = 0;
        unsigned int per_softirq_sums[NR_SOFTIRQS] = { 0 };

        for_each_possible_cpu(cpu_id)
        {
                user += kcpustat_cpu(cpu_id).cpustat[CPUTIME_USER];
                nice += kcpustat_cpu(cpu_id).cpustat[CPUTIME_NICE];
                system += kcpustat_cpu(cpu_id).cpustat[CPUTIME_SYSTEM];
                idle += get_idle_time(cpu_id);
                iowait += get_iowait_time(cpu_id);
                irq += kcpustat_cpu(cpu_id).cpustat[CPUTIME_IRQ];
                softirq += kcpustat_cpu(cpu_id).cpustat[CPUTIME_SOFTIRQ];
                steal += kcpustat_cpu(cpu_id).cpustat[CPUTIME_STEAL];
                guest += kcpustat_cpu(cpu_id).cpustat[CPUTIME_GUEST];
                guest_nice += kcpustat_cpu(cpu_id).cpustat[CPUTIME_GUEST_NICE];
                sum += kstat_cpu_irqs_sum(cpu_id);

                for (j = 0; j < NR_SOFTIRQS; j++) {
                        unsigned int softirq_stat = kstat_softirqs_cpu(j,
                                        cpu_id);

                        per_softirq_sums[j] += softirq_stat;
                        sum_softirq += softirq_stat;
                }
        }
        printk("cpu   %llu %llu %llu %llu %llu %llu %llu %llu %llu %llu\n",
                        nsec_to_clock_t2(user), nsec_to_clock_t2(nice),
                        nsec_to_clock_t2(system), nsec_to_clock_t2(idle),
                        nsec_to_clock_t2(iowait), nsec_to_clock_t2(irq),
                        nsec_to_clock_t2(softirq), nsec_to_clock_t2(steal),
                        nsec_to_clock_t2(guest), nsec_to_clock_t2(guest_nice));

        for_each_online_cpu(cpu_id)
        {
                /* Copy values here to work around gcc-2.95.3, gcc-2.96 */
                user = kcpustat_cpu(cpu_id).cpustat[CPUTIME_USER];
                nice = kcpustat_cpu(cpu_id).cpustat[CPUTIME_NICE];
                system = kcpustat_cpu(cpu_id).cpustat[CPUTIME_SYSTEM];
                idle = get_idle_time(cpu_id);
                iowait = get_iowait_time(cpu_id);
                irq = kcpustat_cpu(cpu_id).cpustat[CPUTIME_IRQ];
                softirq = kcpustat_cpu(cpu_id).cpustat[CPUTIME_SOFTIRQ];
                steal = kcpustat_cpu(cpu_id).cpustat[CPUTIME_STEAL];
                guest = kcpustat_cpu(cpu_id).cpustat[CPUTIME_GUEST];
                guest_nice = kcpustat_cpu(cpu_id).cpustat[CPUTIME_GUEST_NICE];
                printk(
                                "cpu%d %llu %llu %llu %llu %llu %llu %llu %llu %llu %llu\n",
                                cpu_id, nsec_to_clock_t2(user),
                                nsec_to_clock_t2(nice), nsec_to_clock_t2(system),
                                nsec_to_clock_t2(idle), nsec_to_clock_t2(iowait),
                                nsec_to_clock_t2(irq), nsec_to_clock_t2(softirq),
                                nsec_to_clock_t2(steal), nsec_to_clock_t2(guest),
                                nsec_to_clock_t2(guest_nice));
        }
        printk("intr %llu\n", (unsigned long long) sum);
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
        char our_thread[9] = "sake-cpu";

        pr_info("Starting CPU monitor...");
        cpu_thread = kthread_create(cpu_thread_fn, NULL, our_thread);
        if (cpu_thread) {
                wake_up_process(cpu_thread);
        }
        pr_info("CPU monitor started.");
}

void sake_stop_cpu_monitor(void)
{
        pr_info("Stopping CPU monitor...");
        if (cpu_thread) {
                kthread_stop(cpu_thread);
                pr_info("Thread stopped");
        }
        pr_info("CPU monitor stopped.");
}

