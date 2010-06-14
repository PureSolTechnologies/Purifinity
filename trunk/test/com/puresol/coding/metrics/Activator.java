package com.puresol.coding.metrics;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.evaluator.Evaluators;

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
		logger.info("Starting Metrics Base Pack...");
		Evaluators.getInstance().registerLanguage(language)
		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Metrics Base Pack...");
		ProgrammingLanguages.getInstance().unregisterLanguage(
				Java.getInstance());
		logger.info("Stopped.");
	}

}
