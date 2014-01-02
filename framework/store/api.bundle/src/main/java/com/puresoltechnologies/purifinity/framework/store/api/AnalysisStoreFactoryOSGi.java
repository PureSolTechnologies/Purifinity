package com.puresoltechnologies.purifinity.framework.store.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreFactoryOSGi extends AnalysisStoreFactory {

	private AnalysisStore analysisStore = null;

	@Override
	public AnalysisStore getInstance() {
		if (analysisStore == null) {
			createInstance();
		}
		return analysisStore;
	}

	private synchronized void createInstance() {
		if (analysisStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<?> serviceReference = bundleContext
					.getServiceReference(AnalysisStore.class.getName());
			if (serviceReference != null) {
				analysisStore = (AnalysisStore) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
