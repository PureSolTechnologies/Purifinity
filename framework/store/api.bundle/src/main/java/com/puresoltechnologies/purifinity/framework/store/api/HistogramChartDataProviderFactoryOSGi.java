package com.puresoltechnologies.purifinity.framework.store.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HistogramChartDataProviderFactoryOSGi extends
		HistogramChartDataProviderFactory {

	private HistogramChartDataProvider histogramChartDataProvider = null;

	@Override
	public HistogramChartDataProvider getInstance() {
		if (histogramChartDataProvider == null) {
			createInstance();
		}
		return histogramChartDataProvider;
	}

	private synchronized void createInstance() {
		if (histogramChartDataProvider == null) {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference<?> serviceReference = bundleContext
					.getServiceReference(HistogramChartDataProvider.class);
			if (serviceReference != null) {
				histogramChartDataProvider = (HistogramChartDataProvider) bundleContext
						.getService(serviceReference);
			}
		}
	}
}
