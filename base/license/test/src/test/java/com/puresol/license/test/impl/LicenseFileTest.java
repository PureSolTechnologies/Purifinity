package com.puresol.license.test.impl;

import java.io.File;
import java.security.KeyPair;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.license.api.License;
import com.puresol.license.api.Licensee;
import com.puresol.license.api.Licenser;
import com.puresol.license.api.Product;
import com.puresol.license.api.Version;
import com.puresol.license.creator.LicenseManager;
import com.puresol.license.creator.exception.LicenseStoreException;
import com.puresol.license.impl.lib.LicenseFile;
import com.puresol.license.test.AbstractLicenseFacilityTest;
import com.puresol.license.test.TestFileSystemLicenseStore;
import com.puresol.utils.crypt.RSAUtilities;

public class LicenseFileTest extends AbstractLicenseFacilityTest {

	private static final File testLicenseFile = new File(
			"target/licenses/testLicenseFile.license");

	private static Licensee testLicensee;

	@BeforeClass
	public static void initialitzeTestData() throws LicenseStoreException {
		KeyPair generatedKeyPair = RSAUtilities.generateKeyPair(512);
		testLicensee = new Licensee("me", "PureSol Technologies");
		TestFileSystemLicenseStore licenseStore = (TestFileSystemLicenseStore) getLicenseStore();
		licenseStore.addLicensee(testLicensee, generatedKeyPair);
	}

	@Test
	public void testWriteAndRead() throws Exception {
		GregorianCalendar expirationDate = new GregorianCalendar(2100, 0, 1);
		LicenseManager licenseManager = getLicenseManager();
		Product product = new Product("Purifinity", new Version(0, 8, 15));
		Licenser licenser = new Licenser("PureSol Technologies",
				"contact@puresol-technologies.com");
		License license = licenseManager.createLicense(product, licenser,
				testLicensee, expirationDate.getTime());
		LicenseFile licenseFile = new LicenseFile(testLicenseFile);
		KeyPair keyPair = getLicenseStore().getKeyPair(
				license.getLicensee().getCustomerId());
		licenseFile.writeLicense(license, keyPair.getPrivate());
		License readLicense = licenseFile.readLicense();
		// assertEquals(license, readLicense);
	}
}
