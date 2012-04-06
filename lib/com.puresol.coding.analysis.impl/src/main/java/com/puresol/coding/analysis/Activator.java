package com.puresol.coding.analysis;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.analysis.api.AnalysisStore;

public class Activator implements BundleActivator {

    private static BundleContext context = null;
    private ServiceRegistration<AnalysisStore> analysisStoreService;

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
	analysisStoreService = context.registerService(AnalysisStore.class,
		new AnalysisStoreImpl(), new Hashtable<String, Object>());
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
	context.ungetService(analysisStoreService.getReference());
	analysisStoreService = null;
	Activator.context = null;
    }

    public static BundleContext getBundleContext() {
	if (Activator.context == null) {
	    throw new RuntimeException("Plugin " + Activator.class.getName()
		    + " was never activated!");
	}
	return context;
    }

}
