package com.puresoltechnologies.purifinity.coding.analysis.api;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is the central factory for a code store. It creates {@link FileStore}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class FileStoreFactory {

    private static FileStoreFactory instance;

    public static FileStoreFactory getFactory() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    ServiceLoader<FileStoreFactory> serviceLoader = ServiceLoader
		    .load(FileStoreFactory.class);
	    Iterator<FileStoreFactory> iterator = serviceLoader.iterator();
	    if (!iterator.hasNext()) {
		throw new IllegalStateException("No implementation for '"
			+ FileStoreFactory.class.getName() + "' was found!");
	    }
	    instance = iterator.next();
	    if (iterator.hasNext()) {
		throw new IllegalStateException(
			"Too many implementations for '"
				+ FileStoreFactory.class.getName()
				+ "' were found!");
	    }
	}
    }

    public abstract FileStore getInstance();

}
