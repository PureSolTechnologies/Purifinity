package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class HistogramChartDataProviderFactory {

	private static HistogramChartDataProviderFactory instance;

	public static HistogramChartDataProviderFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<HistogramChartDataProviderFactory> serviceLoader = ServiceLoader
					.load(HistogramChartDataProviderFactory.class);
			Iterator<HistogramChartDataProviderFactory> iterator = serviceLoader
					.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ HistogramChartDataProviderFactory.class.getName()
						+ "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ HistogramChartDataProviderFactory.class
										.getName() + "' were found!");
			}
		}
	}

	public abstract HistogramChartDataProvider getInstance();

}
