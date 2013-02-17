package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a code store. It creates {@link CodeStore}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ModuleStoreFactoryOSGI extends ModuleStoreFactory {

	private ModuleStore moduleStore = null;

	@Override
	public ModuleStore getInstance() {
		if (moduleStore == null) {
			createInstance();
		}
		return moduleStore;
	}

	private synchronized void createInstance() {
		if (moduleStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<ModuleStore> serviceReference = bundleContext
					.getServiceReference(ModuleStore.class);
			if (serviceReference != null) {
				moduleStore = bundleContext.getService(serviceReference);
			}
		}
	}
}
