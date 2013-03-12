package com.puresol.coding.client.common.osgi;

import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleActivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an abstract startup class implementation. This can be used to create
 * startup classes implementing the {@link IStartup} interface to get started
 * via the org.eclipse.ui.startup extension point.
 * 
 * This is done by touching the activator class of the bundle to be started. The
 * bundle needs to add 'Bundle-ActivationPolicy: lazy' to it MANIFEST.MF file.
 * Otherwise Eclipse will not activate the bundle.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractStartup implements IStartup {

    /**
     * Is the logger used to log.
     */
    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractStartup.class);

    /**
     * Contains the activator class which is touched to trigger the lazy
     * activation of the bundle.
     */
    private final Class<? extends BundleActivator> activatorClass;

    /**
     * This is the constructor to be used by the implementing class to set the
     * activator class to be touch for activation trigging.
     * 
     * @param activatorClass
     *            is the activator class to be touched. This class need to
     *            implement the {@link BundleActivator} interface.
     */
    protected AbstractStartup(Class<? extends BundleActivator> activatorClass) {
	this.activatorClass = activatorClass;
    }

    @Override
    public final void earlyStartup() {
	System.err.println("Trigger activation of '"
		+ activatorClass.getPackage().getName() + "'...");
	logger.debug("Trigger activation of '"
		+ activatorClass.getPackage().getName() + "'...");
    }
}
