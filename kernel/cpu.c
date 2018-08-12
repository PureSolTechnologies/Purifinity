#include "cpu.h"

/*
 * Taken from: /LinuxKernel/fs/proc/stat.c
 */

int thread_fn(void* data)
{
        while (!kthread_should_stop()) {
                pr_info("In thread1");
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
        pr_info("in init");
        cpu_thread = kthread_create(thread_fn, NULL, our_thread);
        if (cpu_thread) {
                pr_info("in if");
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

