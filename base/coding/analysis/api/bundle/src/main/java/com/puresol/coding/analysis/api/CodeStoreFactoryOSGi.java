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
public class CodeStoreFactoryOSGi extends CodeStoreFactory {

	private CodeStore codeStore = null;

	@Override
	public CodeStore getInstance() {
		if (codeStore == null) {
			createInstance();
		}
		return codeStore;
	}

	private synchronized void createInstance() {
		if (codeStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<CodeStore> serviceReference = bundleContext
					.getServiceReference(CodeStore.class);
			if (serviceReference != null) {
				codeStore = bundleContext.getService(serviceReference);
			}
		}
	}
}
