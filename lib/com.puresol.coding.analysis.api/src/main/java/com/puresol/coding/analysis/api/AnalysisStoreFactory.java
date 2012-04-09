package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreFactory {

    private static AnalysisStore analysisStore = null;

    public static AnalysisStore getInstance() {
	if (analysisStore == null) {
	    createInstance();
	}
	return analysisStore;
    }

    private static synchronized void createInstance() {
	if (analysisStore == null) {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference<AnalysisStore> serviceReference = bundleContext
		    .getServiceReference(AnalysisStore.class);
	    if (serviceReference != null) {
		analysisStore = bundleContext.getService(serviceReference);
	    }
	}
    }
}