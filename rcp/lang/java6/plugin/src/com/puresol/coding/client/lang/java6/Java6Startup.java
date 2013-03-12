package com.puresol.coding.client.lang.java6;

import org.eclipse.ui.IStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.java.Activator;

public class Java6Startup implements IStartup {

    private static final Logger logger = LoggerFactory
	    .getLogger(Java6Startup.class);

    @Override
    public void earlyStartup() {
	logger.debug("Trigger activation of '"
		+ Activator.class.getPackage().getName() + "'...");
    }
}
