package com.puresol.coding.lang.java;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.api.ProgrammingLanguage;
import com.puresol.commons.osgi.AbstractActivator;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator extends AbstractActivator {

	private static final Logger logger = LoggerFactory
			.getLogger(Activator.class);

	private static BundleContext bundleContext;

	private ServiceRegistration registration;

	@Override
	public void start(BundleContext context) throws Exception {
		if (Activator.bundleContext != null) {
			throw new RuntimeException("Bundle was already started!");

		}
		Activator.bundleContext = context;
		logger.info("Starting Java Language Pack...");
		Java java = Java.getInstance();
		registration = context.registerService(
				ProgrammingLanguage.class.getName(), java,
				new Hashtable<String, String>());

		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (Activator.bundleContext == null) {
			throw new RuntimeException("Bundle was never started!");

		}
		logger.info("Stopping Java Language Pack...");
		registration.unregister();
		registration = null;
		Activator.bundleContext = null;
		logger.info("Stopped.");
	}

}
