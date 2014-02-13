package com.puresoltechnologies.purifinity.framework.store.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MetricsMapDataProviderFactoryOSGi extends
		MetricsMapDataProviderFactory {

	private MetricsMapDataProvider metricsMapDataProvider = null;

	@Override
	public MetricsMapDataProvider getInstance() {
		if (metricsMapDataProvider == null) {
			createInstance();
		}
		return metricsMapDataProvider;
	}

	private synchronized void createInstance() {
		if (metricsMapDataProvider == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<?> serviceReference = bundleContext
					.getServiceReference(MetricsMapDataProvider.class);
			if (serviceReference != null) {
				metricsMapDataProvider = (MetricsMapDataProvider) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
