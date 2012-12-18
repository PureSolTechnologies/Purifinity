package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private static BundleContext bundleContext;

    public static BundleContext getBundleContext() {
	if (bundleContext == null) {
	    throw new RuntimeException("BundleContext is null!");
	}
	return bundleContext;
    }

    @Override
    public void start(BundleContext context) throws Exception {
	if (bundleContext != null) {
	    throw new RuntimeException("BundleContext already set!");
	}
	bundleContext = context;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	if (bundleContext == null) {
	    throw new RuntimeException("No BundleContext set, yet!");
	}
	bundleContext = null;
    }

}
