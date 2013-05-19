package com.puresol.license.creator;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.puresol.license.api.License;
import com.puresol.license.api.LicensedClass;
import com.puresol.license.api.Licensee;
import com.puresol.license.api.Licenser;
import com.puresol.license.api.Product;
import com.puresol.license.creator.exception.LicenseStoreException;
import com.puresol.license.creator.store.LicenseStore;
import com.puresol.license.creator.store.LicenseStoreFactory;
import com.puresol.license.impl.lib.LicenseFile;
import com.puresol.utils.crypt.RSAUtilities;

public class LicenseManager {

	private static final String SIGNATURE_ALGORITHM = "SHA512withRSA";
	private static final int DEFAULT_KEY_SIZE = 1024;

	private final LicenseStore licenseStore = LicenseStoreFactory.getFactory()
			.createLicenseStore();

	public Licensee createLicensee(String customerId, String name)
			throws LicenseStoreException {
		Licensee licensee = new Licensee(customerId, name);
		KeyPair keyPair = RSAUtilities.generateKeyPair(DEFAULT_KEY_SIZE);
		licenseStore.addLicensee(licensee, keyPair);
		return licensee;
	}

	public Signature createOrLoadSignature() {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			return signature;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(SIGNATURE_ALGORITHM);
		}
	}

	public List<Licensee> getLicensees() {
		return licenseStore.getLicensees();
	}

	public License createLicense(Product product, Licenser licenser,
			Licensee licensee, Date expirationDate)
			throws LicenseStoreException {
		KeyPair keyPair = licenseStore.getKeyPair(licensee.getCustomerId());
		return new License(keyPair.getPublic(), product, new Date(),
				expirationDate, licenser, licensee,
				new HashSet<LicensedClass>());
	}

	public void exportLicenseToFile(License license, File file)
			throws IOException {
		LicenseFile licenseFile = new LicenseFile(file);
		licenseFile.writeLicense(license);
	}
}
