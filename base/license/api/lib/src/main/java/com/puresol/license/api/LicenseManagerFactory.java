package com.puresol.license.api;

import java.util.Iterator;
import java.util.ServiceLoader;

public abstract class LicenseManagerFactory {

	private static LicenseManagerFactory instance;

	public static LicenseManagerFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<LicenseManagerFactory> serviceLoader = ServiceLoader
					.load(LicenseManagerFactory.class);
			Iterator<LicenseManagerFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ LicenseManagerFactory.class.getName()
						+ "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ LicenseManagerFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract LicenseManager createLicenseManager();

}
