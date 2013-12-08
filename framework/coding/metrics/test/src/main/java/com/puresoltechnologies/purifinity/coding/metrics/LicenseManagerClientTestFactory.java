package com.puresoltechnologies.purifinity.coding.metrics;

import org.mockito.Mockito;

import com.puresoltechnologies.purifinity.license.api.LicenseManagerClient;
import com.puresoltechnologies.purifinity.license.api.LicenseManagerClientFactory;

public class LicenseManagerClientTestFactory extends
		LicenseManagerClientFactory {

	private static final LicenseManagerClient INSTANCE = Mockito
			.mock(LicenseManagerClient.class);

	@Override
	public LicenseManagerClient createLicenseManagerClient() {
		return INSTANCE;
	}

}
