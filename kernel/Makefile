obj-m := sakemodule.o
sakemodule-objs := sake.o cpu.o mem.o
 
KERNELDIR ?= /lib/modules/$(shell uname -r)/build

all default: modules

install: modules_install

modules modules_install help clean:
		$(MAKE) -C $(KERNELDIR) M=$(shell pwd) $@
