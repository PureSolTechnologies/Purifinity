package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ModuleStoreFactory {

	private static ModuleStore directoryStore = null;

	public static ModuleStore getInstance() {
		if (directoryStore == null) {
			createInstance();
		}
		return directoryStore;
	}

	private static synchronized void createInstance() {
		if (directoryStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference serviceReference = bundleContext
					.getServiceReference(ModuleStore.class.getName());
			if (serviceReference != null) {
				directoryStore = (ModuleStore) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
