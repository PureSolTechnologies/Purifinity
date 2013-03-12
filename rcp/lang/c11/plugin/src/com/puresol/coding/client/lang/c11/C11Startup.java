package com.puresol.coding.client.lang.c11;

import org.eclipse.ui.IStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.c11.Activator;

public class C11Startup implements IStartup {

    private static final Logger logger = LoggerFactory
	    .getLogger(C11Startup.class);

    @Override
    public void earlyStartup() {
	logger.debug("Trigger activation of '"
		+ Activator.class.getPackage().getName() + "'...");
    }
}
