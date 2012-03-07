package com.puresol.coding.lang.java;

import java.util.Dictionary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.ProgrammingLanguage;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(Activator.class);

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Java Language Pack...");
	Java java = Java.getInstance();
	java.setBundleContext(context);

	Dictionary<String, String> headers = context.getBundle().getHeaders();

	registration = context.registerService(ProgrammingLanguage.class, java,
		headers);

	logger.info("Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping Java Language Pack...");
	registration.unregister();
	registration = null;
	logger.info("Stopped.");
    }

}
