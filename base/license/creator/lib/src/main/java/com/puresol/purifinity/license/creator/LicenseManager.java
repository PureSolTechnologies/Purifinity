package com.puresol.purifinity.license.creator;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.puresol.license.domain.License;
import com.puresol.license.domain.LicenseFile;
import com.puresol.license.domain.LicensedClass;
import com.puresol.license.domain.Licensee;
import com.puresol.license.domain.Licenser;
import com.puresol.license.domain.Product;
import com.puresol.purifinity.license.creator.exception.LicenseStoreException;
import com.puresol.purifinity.license.creator.store.LicenseStore;
import com.puresol.purifinity.license.creator.store.LicenseStoreFactory;
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
			throws IOException, LicenseStoreException {
		try {
			LicenseFile licenseFile = new LicenseFile(file);
			KeyPair keyPair = licenseStore.getKeyPair(license.getLicensee()
					.getCustomerId());
			licenseFile.writeLicense(license, keyPair.getPrivate());
		} catch (InvalidKeyException e) {
			throw new LicenseStoreException("Could not store license to file.",
					e);
		}
	}
}
