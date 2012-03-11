package com.puresol.coding.analysis;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private static BundleContext context = null;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    @Override
    public void start(BundleContext context) throws Exception {
	if (Activator.context != null) {
	    throw new RuntimeException("Plugin " + getClass().getName()
		    + " was already activated!");
	}
	Activator.context = context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
	if (Activator.context == null) {
	    throw new RuntimeException("Plugin " + getClass().getName()
		    + " was never activated!");
	}
	context = null;
    }

    public static BundleContext getContext() {
	if (Activator.context == null) {
	    throw new RuntimeException("Plugin " + Activator.class.getName()
		    + " was never activated!");
	}
	return context;
    }

}
