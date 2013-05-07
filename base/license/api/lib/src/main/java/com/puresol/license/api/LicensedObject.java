package com.puresol.license.api;

/**
 * This is the base class for all classes which needs to be licensed.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class LicensedObject {

	public static void checkIfLicensed(Class<?> clazz) {
		LicenseManagerFactory factory = LicenseManagerFactory.getFactory();
		LicenseManager licenseManager = factory.createLicenseManager();
		licenseManager.checkIfLicensed(clazz);
	}

	public LicensedObject() {
		checkIfLicensed(getClass());
	}

}
