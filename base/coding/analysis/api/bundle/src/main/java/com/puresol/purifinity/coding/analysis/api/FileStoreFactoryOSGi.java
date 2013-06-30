package com.puresol.purifinity.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresol.purifinity.coding.analysis.api.FileStore;
import com.puresol.purifinity.coding.analysis.api.FileStoreFactory;

/**
 * This is the central factory for a code store. It creates {@link CodeStore}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileStoreFactoryOSGi extends FileStoreFactory {

    private FileStore fileStore = null;

    @Override
    public FileStore getInstance() {
	if (fileStore == null) {
	    createInstance();
	}
	return fileStore;
    }

    private synchronized void createInstance() {
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
