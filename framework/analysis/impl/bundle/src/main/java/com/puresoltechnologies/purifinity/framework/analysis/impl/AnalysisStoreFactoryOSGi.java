package com.puresoltechnologies.purifinity.framework.analysis.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisStore;

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
			ServiceReference<AnalysisStore> serviceReference = bundleContext
					.getServiceReference(AnalysisStore.class);
			if (serviceReference != null) {
				analysisStore = bundleContext.getService(serviceReference);
			}
		}
	}
}
