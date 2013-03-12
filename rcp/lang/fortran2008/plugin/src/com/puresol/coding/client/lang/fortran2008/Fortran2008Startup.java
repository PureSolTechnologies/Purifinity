package com.puresol.coding.client.lang.fortran2008;

import org.eclipse.ui.IStartup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.fortran.Activator;

public class Fortran2008Startup implements IStartup {

    private static final Logger logger = LoggerFactory
	    .getLogger(Fortran2008Startup.class);

    @Override
    public void earlyStartup() {
	logger.debug("Trigger activation of '"
		+ Activator.class.getPackage().getName() + "'...");
    }
}
