#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/moduleparam.h>
#include <linux/timer.h>
#include <linux/cdev.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Rick-Rainer Ludwig <ludwig@puresol-technologies.com>");
MODULE_DESCRIPTION("Monitoring for Linux Kernel.");
MODULE_VERSION("0.01");

static int logcpu = 1;
static int logmem = 1;
static int logio = 0;
static int lognet = 0;
static int logprocs = 0;

module_param(logcpu, int, S_IRUGO | S_IWUSR);
module_param(logmem, int, S_IRUGO | S_IWUSR);
module_param(logio, int, S_IRUGO | S_IWUSR);
module_param(lognet, int, S_IRUGO | S_IWUSR);
module_param(logprocs, int, S_IRUGO | S_IWUSR);

MODULE_PARM_DESC(logcpu,"Monitor CPU.");
MODULE_PARM_DESC(logmem,"Monitor memory.");
MODULE_PARM_DESC(logio,"Monitor I/O.");
MODULE_PARM_DESC(lognet,"Monitor network.");
MODULE_PARM_DESC(logprocs,"Monitor processes.");

static struct timer_list my_timer;

static const int MONITOR_PERIOD = 1000;

void my_timer_callback(struct timer_list *timer)
{
        int retval;
        pr_info("%s called (%ld).\n", __FUNCTION__, jiffies);
        retval = mod_timer(&my_timer,
                        jiffies + msecs_to_jiffies(MONITOR_PERIOD));
        if (retval) {
                pr_err("monitor timer firing failed\n");
        }
}

static void init_timer(void)
{
        int retval;
        timer_setup(&my_timer, my_timer_callback, 0);
        pr_info("Setup timer to fire in %dms (%ld)\n", MONITOR_PERIOD, jiffies);
        retval = mod_timer(&my_timer,
                        jiffies + msecs_to_jiffies(MONITOR_PERIOD));
        if (retval) {
                pr_err("monitor timer firing failed\n");
        }
}

#define MONITOR_DEVICE_NAME "monitor"
#define MONITOR_CLASS "monitor-class"
struct class *monitor_class;
struct cdev monitor_cdev;
dev_t dev_num;

static int monitor_open(struct inode *inode, struct file *filp)
{
        return 0;
}

static int monitor_release(struct inode *inode, struct file *filp)
{
        return 0;
}
static const struct file_operations eep_fops = { //
                .owner = THIS_MODULE, //
                                .read = NULL, //
                                .write = NULL, //
                                .open = monitor_open, //
                                .release = monitor_release, //
                                .llseek = NULL, //
                                .poll = NULL, //
                                .unlocked_ioctl = NULL, //
                };
static void init_device(void)
{
        dev_t curr_dev;

        /* Request the kernel for EEP_NBANK devices */
        alloc_chrdev_region(&dev_num, 0, 1, MONITOR_DEVICE_NAME);
        /* Let's create our device's class, visible in /sys/class */
        monitor_class = class_create(THIS_MODULE, MONITOR_CLASS);

        /* Tie file_operations to the cdev */
        cdev_init(&monitor_cdev, &eep_fops);
        monitor_cdev.owner = THIS_MODULE;
        /* Device number to use to add cdev to the core */
        curr_dev = MKDEV(MAJOR(dev_num), MINOR(dev_num));/* Now make the device live for the users to access */
        cdev_add(&monitor_cdev, curr_dev, 1);
        /* create a device node each device /dev/eep-mem0, /dev/eep-mem1,
         * With our class used here, devices can also be viewed under
         * /sys/class/eep-class.
         */
        device_create(monitor_class, NULL, /* no parent device */
        curr_dev, NULL, /* no additional data */
        MONITOR_DEVICE_NAME "%d", 1); /* eep-mem[0-7] */
}

static int __init monitor_init(void) {
        pr_info("Monitor module loaded\n");
        init_device();
        init_timer();
        return 0;
}

static void __exit monitor_exit(void) {
        int retval;

        device_destroy(monitor_class, dev_num);
        class_destroy(monitor_class);

        retval = del_timer(&my_timer);
        /* Is timer still active (1) or no (0) */
        if (retval) {
                pr_warn("The monitor timer is still in use...\n");
        }
        pr_info("Monitor module unloaded\n");
}

module_init( monitor_init);
module_exit( monitor_exit);
