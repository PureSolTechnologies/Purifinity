package com.puresol.license.creator;

import static org.junit.Assert.assertNotNull;

import java.security.Signature;

import org.junit.Test;

public class LicenseManagerTest {

	private final LicenseManager licenseManager = new LicenseManager();

	@Test
	public void testCreateOrLoadSignature() {
		Signature signature = licenseManager.createOrLoadSignature();
		assertNotNull(signature);
	}
}
