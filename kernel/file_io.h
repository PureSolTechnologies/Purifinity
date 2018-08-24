#ifndef _SAKE_FILE_IO_H
#define _SAKE_FILE_IO_H

#include <linux/kthread.h>  // for threads, needed to handle I/O context

#include <linux/fs.h>
#include <asm/segment.h>
#include <asm/uaccess.h>
#include <linux/buffer_head.h>

struct file *open_file(const char *, int, umode_t);
void close_file(struct file *);
ssize_t write_file(struct file *, const char *, size_t);

#endif
