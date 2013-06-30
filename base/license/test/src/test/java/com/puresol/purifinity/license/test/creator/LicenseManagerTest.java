package com.puresol.purifinity.license.test.creator;

import static org.junit.Assert.assertNotNull;

import java.security.Signature;

import org.junit.Test;

import com.puresol.purifinity.license.creator.LicenseManager;
import com.puresol.purifinity.license.test.AbstractLicenseFacilityTest;

public class LicenseManagerTest extends AbstractLicenseFacilityTest {

	private final LicenseManager licenseManager = new LicenseManager();

	@Test
	public void testCreateOrLoadSignature() {
		Signature signature = licenseManager.createOrLoadSignature();
		assertNotNull(signature);
	}
}
