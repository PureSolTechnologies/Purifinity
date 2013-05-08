package com.puresol.license.test;

import com.puresol.license.api.LicenseManagerClient;
import com.puresol.license.api.LicenseManagerClientFactory;

public class TestLicenseManagerClientFactory extends LicenseManagerClientFactory {

	@Override
	public LicenseManagerClient createLicenseManagerClient() {
		return new TestLicenseManagerClient();
	}

}
