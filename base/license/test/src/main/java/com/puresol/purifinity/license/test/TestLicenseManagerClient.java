package com.puresol.purifinity.license.test;

import java.io.File;
import java.util.Set;

import com.puresol.commons.license.domain.License;
import com.puresol.commons.license.domain.LicenseException;
import com.puresol.purifinity.license.api.LicenseManagerClient;

public class TestLicenseManagerClient implements LicenseManagerClient {

	@Override
	public void checkIfLicensed(Class<?> clazz) throws LicenseException {
		/*
		 * This method was intentionally left empty. For tests the licenses are
		 * valid per default.
		 */
	}

	@Override
	public void addLicenseFile(File licenseFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<License> getInstalledLicenses() {
		// TODO Auto-generated method stub
		return null;
	}

}
