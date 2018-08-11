#include "cpu.h"

/*
 * Taken from: /LinuxKernel/fs/proc/stat.c
 */

//int thread_fn(void* data)
//{
//        unsigned long j0, j1;
//        int delay = 600 * HZ;
//        j0 = jiffies;
//        j1 = j0 + delay;
//
//        pr_info("In thread1");
//
//        while (time_before(jiffies, j1)) {
//                schedule();
//        }
//        return 0;
//}
//
//static struct task_struct *cpu_thread;
void sake_start_cpu_monitor(void)
{
//        char our_thread[9] = "sake-cpu";

        pr_info("Starting CPU monitor...");
//        pr_info("in init");
//        cpu_thread = kthread_create(thread_fn, NULL, our_thread);
//        if ((cpu_thread)) {
//                pr_info("in if");
//                wake_up_process(cpu_thread);
//        }

        pr_info("CPU monitor started.");
}

void sake_stop_cpu_monitor(void)
{
        pr_info("Stopping CPU monitor...");
        pr_info("CPU monitor stopped.");
}

