package com.puresol.coding.client.lang.cpp11;

import org.eclipse.ui.IStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.cpp.Activator;

public class CPP11Startup implements IStartup {

    private static final Logger logger = LoggerFactory
	    .getLogger(CPP11Startup.class);

    @Override
    public void earlyStartup() {
	logger.debug("Trigger activation of '"
		+ Activator.class.getPackage().getName() + "'...");
    }
}
