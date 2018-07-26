#include<linux/module.h>
#include<linux/kernel.h>

//MODULE_LICENSE("GPL");
//MODULE_AUTHOR("Rick-Rainer Ludwig");
//MODULE_DESCRIPTION("Monitoring for Linux Kernel.");
//MODULE_VERSION("0.01");

int init_module(void)
{
 printk(KERN_INFO "Hello World 1 \n");
 return 0;
}

void cleanup_module(void)
{
 printk(KERN_INFO "Goodbye World\n");
}
