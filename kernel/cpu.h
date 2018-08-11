#ifndef _SAKE_CPU_H
#define _SAKE_CPU_H

#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/kthread.h>  // for threads
#include <linux/time.h>   // for using jiffies
#include <linux/timer.h>

extern void sake_start_cpu_monitor(void);

extern void sake_stop_cpu_monitor(void);

#endif
