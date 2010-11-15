package com.puresol.coding.lang.java;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.puresol.coding.ProgrammingLanguages;

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
		ProgrammingLanguages.getInstance().registerLanguage(Java.getInstance());
		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Java Language Pack...");
		ProgrammingLanguages.getInstance().unregisterLanguage(
				Java.getInstance());
		logger.info("Stopped.");
	}

}
