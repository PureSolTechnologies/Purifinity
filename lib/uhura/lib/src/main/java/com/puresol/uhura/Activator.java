package com.puresol.uhura;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(Activator.class);

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Uhura library...");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping Uhura library...");
    }

}
