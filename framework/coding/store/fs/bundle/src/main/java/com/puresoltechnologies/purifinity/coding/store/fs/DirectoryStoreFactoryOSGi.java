package com.puresoltechnologies.purifinity.coding.store.fs;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.analysis.api.DirectoryStore;
import com.puresoltechnologies.purifinity.analysis.api.DirectoryStoreFactory;

/**
 * This is the central factory for a directory store. It creates
 * {@link DirectoryStore} objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DirectoryStoreFactoryOSGi extends DirectoryStoreFactory {

	private DirectoryStore directoryStore = null;

	@Override
	public DirectoryStore getInstance() {
		if (directoryStore == null) {
			createInstance();
		}
		return directoryStore;
	}

	private synchronized void createInstance() {
		if (directoryStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<DirectoryStore> serviceReference = bundleContext
					.getServiceReference(DirectoryStore.class);
			if (serviceReference != null) {
				directoryStore = bundleContext.getService(serviceReference);
			}
		}
	}
}
