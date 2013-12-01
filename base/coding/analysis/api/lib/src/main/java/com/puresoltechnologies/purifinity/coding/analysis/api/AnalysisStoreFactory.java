package com.puresoltechnologies.purifinity.coding.analysis.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AnalysisStoreFactory {

	private static AnalysisStoreFactory instance;

	public static AnalysisStoreFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<AnalysisStoreFactory> serviceLoader = ServiceLoader
					.load(AnalysisStoreFactory.class);
			Iterator<AnalysisStoreFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ AnalysisStoreFactory.class.getName() + "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ AnalysisStoreFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract AnalysisStore getInstance();

}
