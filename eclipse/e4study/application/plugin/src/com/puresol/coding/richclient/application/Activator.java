package com.puresol.coding.richclient.application;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator extends Plugin {

	private static final Logger logger = LoggerFactory
			.getLogger(Activator.class);

	/**
	 * This field holds the current bundle context. This field is static due to
	 * the fact that the {@link BundleActivator} is treaded like a singleton and
	 * is only called once per state.
	 */
	private static BundleContext context = null;

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Staring bundle " + getClass().getPackage().getName()
				+ "...");
		Activator.context = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping bundle " + getClass().getPackage().getName()
				+ "...");
		Activator.context = null;
	}

	/**
	 * This method returns the current bundle context.
	 * 
	 * @return A {@link BundleContext} is returned containing the current
	 *         context. The return value is expected to be not null due to this
	 *         class is called during bundle startup. If the bundle was not
	 *         started, the return value is null.
	 */
	public static final BundleContext getBundleContext() {
		return context;
	}
}
