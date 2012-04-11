package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileStoreFactory {

    private static FileStore fileStore = null;

    public static FileStore getInstance() {
	if (fileStore == null) {
	    createInstance();
	}
	return fileStore;
    }

    private static synchronized void createInstance() {
	if (fileStore == null) {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference<FileStore> serviceReference = bundleContext
		    .getServiceReference(FileStore.class);
	    if (serviceReference != null) {
		fileStore = bundleContext.getService(serviceReference);
	    }
	}
    }
}
