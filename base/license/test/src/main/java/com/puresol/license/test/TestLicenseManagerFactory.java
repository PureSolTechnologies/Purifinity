package com.puresol.license.test;

import com.puresol.license.api.LicenseManager;
import com.puresol.license.api.LicenseManagerFactory;

public class TestLicenseManagerFactory extends LicenseManagerFactory {

	@Override
	public LicenseManager createLicenseManager() {
		return new TestLicenseManager();
	}

}
