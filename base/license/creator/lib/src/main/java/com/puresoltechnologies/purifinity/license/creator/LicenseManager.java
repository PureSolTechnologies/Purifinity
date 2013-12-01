package com.puresoltechnologies.purifinity.license.creator;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.puresoltechnologies.commons.license.domain.License;
import com.puresoltechnologies.commons.license.domain.LicenseFile;
import com.puresoltechnologies.commons.license.domain.LicensedClass;
import com.puresoltechnologies.commons.license.domain.Licensee;
import com.puresoltechnologies.commons.license.domain.Licenser;
import com.puresoltechnologies.commons.license.domain.Product;
import com.puresoltechnologies.commons.utils.crypt.RSAUtilities;
import com.puresoltechnologies.purifinity.license.creator.exception.LicenseStoreException;
import com.puresoltechnologies.purifinity.license.creator.store.LicenseStore;
import com.puresoltechnologies.purifinity.license.creator.store.LicenseStoreFactory;

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
