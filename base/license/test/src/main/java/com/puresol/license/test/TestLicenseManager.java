package com.puresol.license.test;
import java.io.File;

import com.puresol.license.api.LicenseManager;
import com.puresol.license.api.exception.LicenseException;

public class TestLicenseManager implements LicenseManager {

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

}
