package com.puresol.purifinity.license.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;

import com.puresol.purifinity.license.creator.LicenseManager;
import com.puresol.purifinity.license.creator.store.LicenseStore;
import com.puresol.purifinity.license.creator.store.LicenseStoreFactory;
import com.puresol.purifinity.license.test.TestFileSystemLicenseStore;
import com.puresol.purifinity.utils.DirectoryUtilities;

public class AbstractLicenseFacilityTest {

	private static final String EXPECTED_LICENSE_STORAGE_DIRECTORY = "target/licenses";
	private static final File licenseStorageDirectory = new File(
			EXPECTED_LICENSE_STORAGE_DIRECTORY);
	private static LicenseStore licenseStore;
	private static LicenseManager licenseManager;

	@BeforeClass
	public static void initialize() {
		DirectoryUtilities.deleteDirectoryRecursivly(licenseStorageDirectory);
		licenseStore = LicenseStoreFactory.getFactory().createLicenseStore();
		assertEquals(
				"The license directory needs to be a test directory for testing!",
				TestFileSystemLicenseStore.class, licenseStore.getClass());
		licenseManager = new LicenseManager();
	}

	public static LicenseStore getLicenseStore() {
		return licenseStore;
	}

	public static LicenseManager getLicenseManager() {
		return licenseManager;
	}
}
