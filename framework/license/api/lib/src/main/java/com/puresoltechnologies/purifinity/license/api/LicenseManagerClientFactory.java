package com.puresoltechnologies.purifinity.license.api;

import java.util.Iterator;
import java.util.ServiceLoader;

public abstract class LicenseManagerClientFactory {

	private static LicenseManagerClientFactory instance;

	public static LicenseManagerClientFactory getFactory() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			ServiceLoader<LicenseManagerClientFactory> serviceLoader = ServiceLoader
					.load(LicenseManagerClientFactory.class);
			Iterator<LicenseManagerClientFactory> iterator = serviceLoader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException("No implementation for '"
						+ LicenseManagerClientFactory.class.getName()
						+ "' was found!");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many implementations for '"
								+ LicenseManagerClientFactory.class.getName()
								+ "' were found!");
			}
		}
	}

	public abstract LicenseManagerClient createLicenseManagerClient();

}
