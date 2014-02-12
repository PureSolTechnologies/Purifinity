package com.puresoltechnologies.purifinity.framework.store.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParetoChartDataProviderFactoryOSGi extends
		ParetoChartDataProviderFactory {

	private ParetoChartDataProvider paretoChartDataProvider = null;

	@Override
	public ParetoChartDataProvider getInstance() {
		if (paretoChartDataProvider == null) {
			createInstance();
		}
		return paretoChartDataProvider;
	}

	private synchronized void createInstance() {
		if (paretoChartDataProvider == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<?> serviceReference = bundleContext
					.getServiceReference(ParetoChartDataProvider.class);
			if (serviceReference != null) {
				paretoChartDataProvider = (ParetoChartDataProvider) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
