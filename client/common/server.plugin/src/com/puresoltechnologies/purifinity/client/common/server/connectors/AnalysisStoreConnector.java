package com.puresoltechnologies.purifinity.client.common.server.connectors;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.client.common.server.Activator;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;

public class AnalysisStoreConnector {

	public static AnalysisStore getStore() {
		BundleContext bundleContext = Activator.getDefault().getBundle()
				.getBundleContext();
		ServiceReference<AnalysisStore> serviceReference = bundleContext
				.getServiceReference(AnalysisStore.class);
		return bundleContext.getService(serviceReference);
	}

}
