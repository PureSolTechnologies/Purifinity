package com.puresol.purifinity.coding.metrics;

import org.mockito.Mockito;

import com.puresol.purifinity.license.api.LicenseManagerClient;
import com.puresol.purifinity.license.api.LicenseManagerClientFactory;

public class LicenseManagerClientTestFactory extends
		LicenseManagerClientFactory {

	private static final LicenseManagerClient INSTANCE = Mockito
			.mock(LicenseManagerClient.class);

	@Override
	public LicenseManagerClient createLicenseManagerClient() {
		return INSTANCE;
	}

}
