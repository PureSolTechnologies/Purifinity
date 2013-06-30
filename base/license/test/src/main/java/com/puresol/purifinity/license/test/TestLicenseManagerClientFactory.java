package com.puresol.purifinity.license.test;

import com.puresol.purifinity.license.api.LicenseManagerClient;
import com.puresol.purifinity.license.api.LicenseManagerClientFactory;

public class TestLicenseManagerClientFactory extends LicenseManagerClientFactory {

	@Override
	public LicenseManagerClient createLicenseManagerClient() {
		return new TestLicenseManagerClient();
	}

}
