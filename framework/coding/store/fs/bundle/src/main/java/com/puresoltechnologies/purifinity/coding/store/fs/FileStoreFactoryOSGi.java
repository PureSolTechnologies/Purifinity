package com.puresoltechnologies.purifinity.coding.store.fs;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.analysis.api.FileStore;
import com.puresoltechnologies.purifinity.analysis.api.FileStoreFactory;

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
