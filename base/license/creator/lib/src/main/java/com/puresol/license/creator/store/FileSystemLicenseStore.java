package com.puresol.license.creator.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.util.List;
import java.util.Properties;

import com.puresol.license.api.Licensee;
import com.puresol.license.creator.exception.LicenseStoreException;
import com.puresol.utils.StringUtils;

public class FileSystemLicenseStore implements LicenseStore {

    private static final String LICENSEE_FILE_NAME = "licensee.properties";
    private static final String KEY_PAIR_FILE_NAME = "keypair.properties";

    private final File storageDirectory = new File(
	    "/media/server1/PureSol-Technologies/licenses");

    public FileSystemLicenseStore() {
	if (!storageDirectory.exists()) {
	    if (!storageDirectory.mkdirs()) {
		throw new RuntimeException(
			"Cannot create license storage directory '"
				+ storageDirectory + "'!");
	    }
	}
    }

    @Override
    public List<Licensee> getLicensees() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void addLicensee(Licensee licensee, KeyPair keyPair)
	    throws LicenseStoreException {
	File licenseeDirectory = getLicenseeFolder(licensee);
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
	String publicKey = StringUtils.convertByteArrayToString(keyPair
		.getPublic().getEncoded());
	String privateKey = StringUtils.convertByteArrayToString(keyPair
		.getPrivate().getEncoded());
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
    public KeyPair getKeyPair(Licensee licensee) throws LicenseStoreException {
	File licenseeDirectory = getLicenseeFolder(licensee);
	Properties properties = new Properties();
	File keyPairFile = new File(licenseeDirectory, KEY_PAIR_FILE_NAME);
	try {
	    OutputStream outStream = new FileOutputStream(keyPairFile);
	    try {
		properties.store(outStream, "Licensee information");
	    } finally {
		outStream.close();
	    }
	} catch (IOException e) {
	    throw new LicenseStoreException("Could not read key pair from '"
		    + keyPairFile + "'!", e);
	}
	String publicKey = properties.getProperty("publicKey");
	String privateKey = properties.getProperty("privateKey");
	// TODO
	return new KeyPair(null, null);
    }

    private File getLicenseeFolder(Licensee licensee) {
	return new File(storageDirectory, licensee.getCustomerId());
    }

}
