#include "mem.h"

/*
 * Taken from: /LinuxKernel/fs/proc/meminfo.c
 */

void sake_start_memory_monitor(void)
{
        pr_info("Starting memory monitor...");
        pr_info("Memory monitor started.");
}

void sake_stop_memory_monitor(void)
{
        pr_info("Stopping memory monitor...");
        pr_info("Memory monitor stopped.");
}
