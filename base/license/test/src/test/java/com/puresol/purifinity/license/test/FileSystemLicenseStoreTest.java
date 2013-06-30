package com.puresol.purifinity.license.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.KeyPair;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.puresol.purifinity.license.api.Licensee;
import com.puresol.purifinity.license.creator.exception.LicenseStoreException;
import com.puresol.purifinity.license.test.TestFileSystemLicenseStore;
import com.puresol.purifinity.utils.DirectoryUtilities;
import com.puresol.purifinity.utils.crypt.RSAUtilities;

public class FileSystemLicenseStoreTest extends AbstractLicenseFacilityTest {

	@Before
	public void setup() {
		TestFileSystemLicenseStore licenseStore = (TestFileSystemLicenseStore) getLicenseStore();
		File storageDirectory = licenseStore.getStorageDirectory();
		if (storageDirectory.exists()) {
			assertTrue("Could not clean test license directory!",
					DirectoryUtilities
							.deleteDirectoryRecursivly(storageDirectory));
		}
	}

	@Test
	public void testAddLicensee() throws LicenseStoreException {
		KeyPair keyPair = RSAUtilities.generateKeyPair(512);
		Licensee licensee = new Licensee("me", "PureSol Technologies");
		getLicenseStore().addLicensee(licensee, keyPair);
		TestFileSystemLicenseStore licenseStore = (TestFileSystemLicenseStore) getLicenseStore();
		File meDirectory = new File(licenseStore.getStorageDirectory(), "me");
		assertTrue(meDirectory.exists());
	}

	@Test
	public void testGetKeyPair() throws LicenseStoreException {
		KeyPair keyPair = RSAUtilities.generateKeyPair(512);
		Licensee licensee = new Licensee("me", "PureSol Technologies");
		getLicenseStore().addLicensee(licensee, keyPair);
		TestFileSystemLicenseStore licenseStore = (TestFileSystemLicenseStore) getLicenseStore();
		File meDirectory = new File(licenseStore.getStorageDirectory(), "me");
		assertTrue(meDirectory.exists());
		KeyPair readKeyPair = getLicenseStore().getKeyPair(
				licensee.getCustomerId());
		assertEquals(keyPair.getPublic(), readKeyPair.getPublic());
		assertEquals(keyPair.getPrivate(), readKeyPair.getPrivate());
	}

	@Test
	public void testListLicensees() throws LicenseStoreException {
		KeyPair keyPair = RSAUtilities.generateKeyPair(512);
		Licensee licensee = new Licensee("me", "PureSol Technologies");
		TestFileSystemLicenseStore licenseStore = (TestFileSystemLicenseStore) getLicenseStore();
		licenseStore.addLicensee(licensee, keyPair);
		licensee = new Licensee("me2", "PureSol Technologies");
		licenseStore.addLicensee(licensee, keyPair);
		List<Licensee> licensees = licenseStore.getLicensees();
		// TODO
	}

}
