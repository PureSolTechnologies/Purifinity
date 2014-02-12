package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class ParetoChartDataProviderFactory {

	private static ParetoChartDataProviderFactory instance;

	public static ParetoChartDataProviderFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<ParetoChartDataProviderFactory> serviceLoader = ServiceLoader
					.load(ParetoChartDataProviderFactory.class);
			Iterator<ParetoChartDataProviderFactory> iterator = serviceLoader
					.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ ParetoChartDataProviderFactory.class.getName()
						+ "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ ParetoChartDataProviderFactory.class
										.getName() + "' were found!");
			}
		}
	}

	public abstract ParetoChartDataProvider getInstance();

}
