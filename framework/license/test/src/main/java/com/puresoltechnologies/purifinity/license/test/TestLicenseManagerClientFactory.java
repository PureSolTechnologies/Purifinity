package com.puresoltechnologies.purifinity.license.test;

import com.puresoltechnologies.purifinity.license.api.LicenseManagerClient;
import com.puresoltechnologies.purifinity.license.api.LicenseManagerClientFactory;

public class TestLicenseManagerClientFactory extends LicenseManagerClientFactory {

	@Override
	public LicenseManagerClient createLicenseManagerClient() {
		return new TestLicenseManagerClient();
	}

}
