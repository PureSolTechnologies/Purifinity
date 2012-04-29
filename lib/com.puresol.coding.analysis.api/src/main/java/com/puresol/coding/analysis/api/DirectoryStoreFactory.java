package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DirectoryStoreFactory {

    private static DirectoryStore directoryStore = null;

    public static DirectoryStore getInstance() {
	if (directoryStore == null) {
	    createInstance();
	}
	return directoryStore;
    }

    private static synchronized void createInstance() {
	if (directoryStore == null) {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference serviceReference = bundleContext
		    .getServiceReference(DirectoryStore.class.getName());
	    if (serviceReference != null) {
		directoryStore = (DirectoryStore) bundleContext
			.getService(serviceReference);
	    }
	}
    }
}
