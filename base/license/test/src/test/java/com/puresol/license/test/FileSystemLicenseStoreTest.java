package com.puresol.license.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.KeyPair;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.license.api.Licensee;
import com.puresol.license.creator.exception.LicenseStoreException;
import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.crypt.AESUtilities;

public class FileSystemLicenseStoreTest {

    private static TestFileSystemLicenseStore licenseStore;

    @BeforeClass
    public static void initialize() {
	licenseStore = new TestFileSystemLicenseStore();
	assertEquals(
		"The license directory needs to be a test directory for testing!",
		new File("target/licenses"), licenseStore.getStorageDirectory());
    }

    @Before
    public void setup() {
	File storageDirectory = licenseStore.getStorageDirectory();
	if (storageDirectory.exists()) {
	    assertTrue("Could not clean test license directory!",
		    DirectoryUtilities
			    .deleteDirectoryRecursivly(storageDirectory));
	}
    }

    @Test
    public void testAddLicensee() throws LicenseStoreException {
	KeyPair keyPair = AESUtilities.generateKeyPair(512);
	Licensee licensee = new Licensee("me", "PureSol Technologies");
	licenseStore.addLicensee(licensee, keyPair);
	File meDirectory = new File(licenseStore.getStorageDirectory(), "me");
	assertTrue(meDirectory.exists());
    }

    @Test
    public void testGetKeyPair() throws LicenseStoreException {
	KeyPair keyPair = AESUtilities.generateKeyPair(512);
	Licensee licensee = new Licensee("me", "PureSol Technologies");
	licenseStore.addLicensee(licensee, keyPair);
	File meDirectory = new File(licenseStore.getStorageDirectory(), "me");
	assertTrue(meDirectory.exists());
	KeyPair readKeyPair = licenseStore.getKeyPair(licensee);
	assertEquals(keyPair.getPublic(), readKeyPair.getPublic());
	assertEquals(keyPair.getPrivate(), readKeyPair.getPrivate());
    }

    @Test
    public void testListLicensees() throws LicenseStoreException {
	KeyPair keyPair = AESUtilities.generateKeyPair(512);
	Licensee licensee = new Licensee("me", "PureSol Technologies");
	licenseStore.addLicensee(licensee, keyPair);
	licensee = new Licensee("me2", "PureSol Technologies");
	licenseStore.addLicensee(licensee, keyPair);
	List<Licensee> licensees = licenseStore.getLicensees();
	// TODO
    }

}
