package com.puresol.coding.analysis.api.storage;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.Activator;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreFactoryOSGI extends AnalysisStoreFactory {

	private AnalysisStore analysisStore = null;

	public AnalysisStore getInstance() {
		if (analysisStore == null) {
			createInstance();
		}
		return analysisStore;
	}

	private synchronized void createInstance() {
		if (analysisStore == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference serviceReference = bundleContext
					.getServiceReference(AnalysisStore.class.getName());
			if (serviceReference != null) {
				analysisStore = (AnalysisStore) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
