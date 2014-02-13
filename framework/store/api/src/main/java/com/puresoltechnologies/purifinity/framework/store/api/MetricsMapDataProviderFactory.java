package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class MetricsMapDataProviderFactory {

	private static MetricsMapDataProviderFactory instance;

	public static MetricsMapDataProviderFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<MetricsMapDataProviderFactory> serviceLoader = ServiceLoader
					.load(MetricsMapDataProviderFactory.class);
			Iterator<MetricsMapDataProviderFactory> iterator = serviceLoader
					.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ MetricsMapDataProviderFactory.class.getName()
						+ "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ MetricsMapDataProviderFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract MetricsMapDataProvider getInstance();

}
