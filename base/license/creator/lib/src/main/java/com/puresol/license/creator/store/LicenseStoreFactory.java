package com.puresol.license.creator.store;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * This is a base class for a factory for {@link LicenseStore} objects.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class LicenseStoreFactory {

    private static LicenseStoreFactory instance;

    public static LicenseStoreFactory getFactory() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    ServiceLoader<LicenseStoreFactory> serviceLoader = ServiceLoader
		    .load(LicenseStoreFactory.class);
	    Iterator<LicenseStoreFactory> iterator = serviceLoader.iterator();
	    if (!iterator.hasNext()) {
		throw new IllegalStateException("No implementation for '"
			+ LicenseStoreFactory.class.getName() + "' was found!");
	    }
	    instance = iterator.next();
	    if (iterator.hasNext()) {
		throw new IllegalStateException(
			"Too many implementations for '"
				+ LicenseStoreFactory.class.getName()
				+ "' were found!");
	    }
	}
    }

    public abstract LicenseStore createLicenseStore();

}
