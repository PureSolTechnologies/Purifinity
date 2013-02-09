package com.puresol.coding.analysis.api.storage;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class ModuleStoreFactory {

	private static ModuleStoreFactory instance;

	public static ModuleStoreFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<ModuleStoreFactory> serviceLoader = ServiceLoader
					.load(ModuleStoreFactory.class);
			Iterator<ModuleStoreFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ ModuleStoreFactory.class.getName() + "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ ModuleStoreFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract ModuleStore getInstance();

}
