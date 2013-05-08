package com.puresol.license.api;

/**
 * This is the base class for all classes which needs to be licensed.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class LicensedObject {

	public static void checkIfLicensed(Class<?> clazz) {
		LicenseManagerClientFactory factory = LicenseManagerClientFactory.getFactory();
		LicenseManagerClient licenseManager = factory.createLicenseManagerClient();
		licenseManager.checkIfLicensed(clazz);
	}

	public LicensedObject() {
		checkIfLicensed(getClass());
	}

}
