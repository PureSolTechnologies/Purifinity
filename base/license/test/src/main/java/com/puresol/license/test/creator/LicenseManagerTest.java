package com.puresol.license.test.creator;

import static org.junit.Assert.assertNotNull;

import java.security.Signature;

import org.junit.Test;

import com.puresol.license.creator.LicenseManager;

public class LicenseManagerTest {

	private final LicenseManager licenseManager = new LicenseManager();

	@Test
	public void testCreateOrLoadSignature() {
		Signature signature = licenseManager.createOrLoadSignature();
		assertNotNull(signature);
	}
}
