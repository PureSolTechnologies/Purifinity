package com.puresol.coding.analysis.api.storage;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.Activator;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ModuleStoreFactoryOSGI extends ModuleStoreFactory {

	private ModuleStore moduleStore = null;

	public ModuleStore getInstance() {
		if (moduleStore == null) {
			createInstance();
		}
		return moduleStore;
	}

	private synchronized void createInstance() {
		if (moduleStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference serviceReference = bundleContext
					.getServiceReference(ModuleStore.class.getName());
			if (serviceReference != null) {
				moduleStore = (ModuleStore) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
