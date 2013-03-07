package com.puresol.coding.analysis.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class DirectoryStoreFactory {

	private static DirectoryStoreFactory instance;

	public static DirectoryStoreFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<DirectoryStoreFactory> serviceLoader = ServiceLoader
					.load(DirectoryStoreFactory.class);
			Iterator<DirectoryStoreFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ DirectoryStoreFactory.class.getName() + "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ DirectoryStoreFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract DirectoryStore getInstance();

}
