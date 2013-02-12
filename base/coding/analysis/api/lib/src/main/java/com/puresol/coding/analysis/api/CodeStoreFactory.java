package com.puresol.coding.analysis.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a code store. It creates {@link CodeStore}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class CodeStoreFactory {

	private static CodeStoreFactory instance;

	public static CodeStoreFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<CodeStoreFactory> serviceLoader = ServiceLoader
					.load(CodeStoreFactory.class);
			Iterator<CodeStoreFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ CodeStoreFactory.class.getName() + "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ CodeStoreFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract CodeStore getInstance();

}
