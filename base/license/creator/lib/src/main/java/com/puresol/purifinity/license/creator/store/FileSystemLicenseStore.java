package com.puresol.purifinity.license.creator.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.util.List;
import java.util.Properties;

import com.puresol.purifinity.license.api.Licensee;
import com.puresol.purifinity.license.creator.exception.LicenseStoreException;
import com.puresol.purifinity.utils.crypt.RSAUtilities;

public class FileSystemLicenseStore implements LicenseStore {

	private static final String LICENSEE_FILE_NAME = "licensee.properties";
	private static final String KEY_PAIR_FILE_NAME = "keypair.properties";

	public FileSystemLicenseStore() {
		if (!getStorageDirectory().exists()) {
			if (!getStorageDirectory().mkdirs()) {
				throw new RuntimeException(
						"Cannot create license storage directory '"
								+ getStorageDirectory() + "'!");
			}
		}
	}

	protected File getStorageDirectory() {
		return new File("/media/server1/PureSol-Technologies/licenses");
	}

	@Override
	public List<Licensee> getLicensees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLicensee(Licensee licensee, KeyPair keyPair)
			throws LicenseStoreException {
		File licenseeDirectory = getLicenseeFolder(licensee.getCustomerId());
		if (licenseeDirectory.exists()) {
			throw new LicenseStoreException("Licensee '" + licensee
					+ "' already exists!");
		}
		if (!licenseeDirectory.mkdirs()) {
			throw new LicenseStoreException(
					"Could not create licensee directory '" + licenseeDirectory
							+ "'!");
		}
		storeLicensee(licenseeDirectory, licensee);
		storeKeyPair(licenseeDirectory, keyPair);
	}

	private void storeLicensee(File licenseeDirectory, Licensee licensee)
			throws LicenseStoreException {
		Properties properties = new Properties();
		properties.put("customerId", licensee.getCustomerId());
		properties.put("name", licensee.getName());
		File licenseeFile = new File(licenseeDirectory, LICENSEE_FILE_NAME);
		try {
			OutputStream outStream = new FileOutputStream(licenseeFile);
			try {
				properties.store(outStream, "Licensee information");
			} finally {
				outStream.close();
			}
		} catch (IOException e) {
			throw new LicenseStoreException(
					"Could not store licensee information to '" + licenseeFile
							+ "'!", e);
		}
	}

	private void storeKeyPair(File licenseeDirectory, KeyPair keyPair)
			throws LicenseStoreException {
		String publicKey = RSAUtilities.convertPublicKeyToString(keyPair
				.getPublic());
		String privateKey = RSAUtilities.convertPrivateKeyToString(keyPair
				.getPrivate());

		Properties properties = new Properties();
		properties.put("publicKey", publicKey);
		properties.put("privateKey", privateKey);
		File keyPairFile = new File(licenseeDirectory, KEY_PAIR_FILE_NAME);
		try {
			OutputStream outStream = new FileOutputStream(keyPairFile);
			try {
				properties.store(outStream, "Licensee information");
			} finally {
				outStream.close();
			}
		} catch (IOException e) {
			throw new LicenseStoreException("Could not store key pair to '"
					+ keyPairFile + "'!", e);
		}
	}

	@Override
	public KeyPair getKeyPair(String customerId) throws LicenseStoreException {
		File licenseeDirectory = getLicenseeFolder(customerId);
		Properties properties = new Properties();
		File keyPairFile = new File(licenseeDirectory, KEY_PAIR_FILE_NAME);
		try {
			InputStream inStream = new FileInputStream(keyPairFile);
			try {
				properties.load(inStream);
			} finally {
				inStream.close();
			}
		} catch (IOException e) {
			throw new LicenseStoreException("Could not read key pair from '"
					+ keyPairFile + "'!", e);
		}
		String publicKey = properties.getProperty("publicKey");
		String privateKey = properties.getProperty("privateKey");
		return restoreKeyPair(publicKey, privateKey);
	}

	private KeyPair restoreKeyPair(String publicKey, String privateKey)
			throws LicenseStoreException {
		try {
			return RSAUtilities.getKeyPairFromString(publicKey, privateKey);
		} catch (IllegalArgumentException e) {
			throw new LicenseStoreException("Could not restore key pair!", e);
		}
	}

	private File getLicenseeFolder(String customerId) {
		return new File(getStorageDirectory(), customerId);
	}

}
