package com.puresol.commons.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an abstract Activator implementation which can be used by all OSGi
 * bundles to provide a simple Activator which logs at least the start and stop
 * and provides simple functionality like {@link BundleContext} retrieval.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractActivator implements BundleActivator {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractActivator.class);

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
		AbstractActivator.context = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping bundle " + getClass().getPackage().getName()
				+ "...");
		AbstractActivator.context = null;
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
