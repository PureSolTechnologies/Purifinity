package com.puresol.coding.lang.c11;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
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

	private ServiceRegistration registration = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		C11 c11 = C11.getInstance();
		registration = context.registerService(
				AnalyzableProgrammingLanguage.class.getName(), c11,
				new Hashtable<String, String>());
		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		registration.unregister();
		registration = null;
		logger.info("Stopped.");
	}

}
