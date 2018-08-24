#include "file_io.h"

struct file *open_file(const char *file_path, int open_mode,
                umode_t permissions)
{
        struct file *opened_file;
        mm_segment_t oldfs;

        oldfs = get_fs();
        set_fs(get_ds());
        printk("Opening %s file...\n", file_path);
        opened_file = filp_open(file_path, open_mode, permissions);
        if (IS_ERR(opened_file)) {
                printk("Error opening %s file: code=%ld\n", file_path,
                                PTR_ERR(opened_file));
                opened_file = NULL;
        } else {
                printk("%s file opened.\n", file_path);
        }
        set_fs(oldfs);
        return opened_file;
}

void close_file(struct file *opened_file)
{
        filp_close(opened_file, NULL);
}

static loff_t offset = 0;

inline ssize_t write_file(struct file *opened_file, const char *output, size_t output_size)
{
        return vfs_write(opened_file, output, output_size, &offset);
}
