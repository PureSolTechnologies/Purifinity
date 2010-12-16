package com.puresol.coding.lang.java;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.puresol.coding.ProgrammingLanguageManager;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

	private static final Logger logger = Logger.getLogger(Activator.class);

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting Java Language Pack...");
		Java java = Java.getInstance();
		java.setBundleContext(context);
		ProgrammingLanguageManager.getInstance().register(java);
		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Java Language Pack...");
		ProgrammingLanguageManager.getInstance().unregister(
				Java.getInstance());
		logger.info("Stopped.");
	}

}
