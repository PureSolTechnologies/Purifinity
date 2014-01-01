package com.puresoltechnologies.purifinity.framework.store.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

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
			ServiceReference serviceReference = bundleContext
					.getServiceReference(FileStore.class.getName());
			if (serviceReference != null) {
				fileStore = (FileStore) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
