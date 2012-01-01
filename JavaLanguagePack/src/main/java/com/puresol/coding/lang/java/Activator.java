package com.puresol.coding.lang.java;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.config.APIInformation;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

    private static final Logger logger = Logger.getLogger(Activator.class);

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Java Language Pack...");
	Java java = Java.getInstance();
	java.setBundleContext(context);

	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", java.getName());
	properties.put("service.description", java.getName());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	registration = context.registerService(
		ProgrammingLanguage.class.getName(), java, properties);

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
