package com.puresol.purifinity.license.impl.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.purifinity.license.api.License;
import com.puresol.purifinity.license.api.LicensedClass;
import com.puresol.purifinity.license.api.Licensee;
import com.puresol.purifinity.license.api.Licenser;
import com.puresol.purifinity.license.api.Product;
import com.puresol.purifinity.license.api.exception.IllegalLicenseFileException;
import com.puresol.purifinity.utils.crypt.AESUtilities;
import com.puresol.purifinity.utils.crypt.InputStreamScrambler;
import com.puresol.purifinity.utils.crypt.OutputStreamScrambler;
import com.puresol.purifinity.utils.crypt.RSAUtilities;

public class LicenseFile {

    private static final Logger logger = LoggerFactory
	    .getLogger(LicenseFile.class);

    private static final byte[] pat = new byte[16];
    static {
	for (int i = 0; i < pat.length; i++) {
	    pat[i] = (byte) (i * 2);
	}
    }

    private static final String KEY_ENTRY_NAME = "key";
    private static final String KEY2_ENTRY_NAME = "key2";
    private static final String LICENSE_CONTENT_ENTRY_NAME = "license.content";

    private final File file;

    public LicenseFile(File file) {
	super();
	this.file = file;
    }

    public License readLicense() throws IOException {
	FileInputStream fileInputStream = new FileInputStream(file);
	try {
	    InputStreamScrambler inputStreamScrambler = new InputStreamScrambler(
		    pat, fileInputStream);
	    try {
		ZipInputStream zipInputStream = new ZipInputStream(
			inputStreamScrambler);
		try {
		    return read(zipInputStream);
		} finally {
		    zipInputStream.close();
		}
	    } finally {
		inputStreamScrambler.close();
	    }
	} finally {
	    fileInputStream.close();
	}
    }

    private License read(ZipInputStream zipInputStream) throws IOException {
	PublicKey publicKey = extractPublicKey(zipInputStream);
	SecretKey secretKey = extractSecretKey(zipInputStream, publicKey);
	return extractLicenseContent(zipInputStream, secretKey);
    }

    private PublicKey extractPublicKey(ZipInputStream zipInputStream)
	    throws IOException {
	ZipEntry keyEntry = zipInputStream.getNextEntry();
	String keyEntryName = keyEntry.getName();
	if (!KEY_ENTRY_NAME.equals(keyEntryName)) {
	    throw new IllegalLicenseFileException(
		    "This license file is not valid!");
	}
	ByteArrayOutputStream publicKeyStream = new ByteArrayOutputStream();
	try {
	    IOUtils.copy(zipInputStream, publicKeyStream);
	    String publicKeyString = publicKeyStream.toString();
	    return RSAUtilities.getPublicKeyFromString(publicKeyString);
	} finally {
	    publicKeyStream.close();
	}
    }

    private SecretKey extractSecretKey(ZipInputStream zipInputStream,
	    PublicKey publicKey) throws IOException {
	publicKey.getAlgorithm();
	ZipEntry key2Entry = zipInputStream.getNextEntry();
	String key2EntryName = key2Entry.getName();
	if (!KEY2_ENTRY_NAME.equals(key2EntryName)) {
	    throw new IllegalLicenseFileException(
		    "This license file is not valid!");
	}
	ByteArrayOutputStream secretKeyStream = new ByteArrayOutputStream();
	try {
	    IOUtils.copy(zipInputStream, secretKeyStream);
	    byte[] encryptedSecretKey = secretKeyStream.toByteArray();
	    byte[] decryptedSecretKey = RSAUtilities.decrypt(publicKey,
		    encryptedSecretKey);
	    return new SecretKeySpec(decryptedSecretKey, "AES");
	} catch (InvalidKeyException e) {
	    throw new IllegalArgumentException("Could not extract key.", e);
	} catch (IllegalBlockSizeException e) {
	    throw new IllegalArgumentException("Could not extract key.", e);
	} finally {
	    secretKeyStream.close();
	}
    }

    private License extractLicenseContent(ZipInputStream zipInputStream,
	    SecretKey secretKey) throws IOException {
	ZipEntry licenseContentEntry = zipInputStream.getNextEntry();
	String licenseContentEntryName = licenseContentEntry.getName();
	if (!LICENSE_CONTENT_ENTRY_NAME.equals(licenseContentEntryName)) {
	    throw new IllegalLicenseFileException(
		    "This license file is not valid!");
	}
	ByteArrayOutputStream propertiesStream = new ByteArrayOutputStream();
	try {
	    IOUtils.copy(zipInputStream, propertiesStream);
	    byte[] encryptedProperties = propertiesStream.toByteArray();
	    byte[] decryptedProperties = AESUtilities.decrypt(secretKey,
		    encryptedProperties);
	    ByteArrayInputStream propertiesInputStream = new ByteArrayInputStream(
		    decryptedProperties);
	    Properties properties = new Properties();
	    properties.load(propertiesInputStream);
	    return propertiesToLicense(properties);
	} catch (InvalidKeyException e) {
	    throw new IllegalArgumentException("Could not extract license.", e);
	} catch (IllegalBlockSizeException e) {
	    throw new IllegalArgumentException("Could not extract license.", e);
	} finally {
	    propertiesStream.close();
	}
    }

    public void writeLicense(License license, PrivateKey privateKey)
	    throws IOException, InvalidKeyException {
	FileOutputStream fileOutputStream = new FileOutputStream(file);
	try {
	    OutputStreamScrambler outputStreamScrambler = new OutputStreamScrambler(
		    pat, fileOutputStream);
	    try {
		ZipOutputStream zipOutputStream = new ZipOutputStream(
			outputStreamScrambler);
		try {
		    write(zipOutputStream, license, privateKey);
		} finally {
		    zipOutputStream.close();
		}
	    } finally {
		outputStreamScrambler.close();
	    }
	} finally {
	    fileOutputStream.close();
	}
    }

    private void write(ZipOutputStream zipOutputStream, License license,
	    PrivateKey privateKey) throws IOException, InvalidKeyException {
	packagePublicKey(zipOutputStream, license);
	packageLicense(zipOutputStream, license, privateKey);
    }

    private void packagePublicKey(ZipOutputStream zipOutputStream,
	    License license) throws IOException {
	ZipEntry keyEntry = new ZipEntry(KEY_ENTRY_NAME);
	zipOutputStream.putNextEntry(keyEntry);
	PublicKey publicKey = license.getPublicKey();
	String publicKeyString = RSAUtilities
		.convertPublicKeyToString(publicKey);
	ByteArrayInputStream publicKeyStream = new ByteArrayInputStream(
		publicKeyString.getBytes());
	try {
	    IOUtils.copy(publicKeyStream, zipOutputStream);
	} finally {
	    publicKeyStream.close();
	}
    }

    private void packageLicense(ZipOutputStream zipOutputStream,
	    License license, PrivateKey privateKey) throws IOException,
	    InvalidKeyException {
	SecretKey aesKey = AESUtilities.generateSecretKey(256);
	storeSecretKey(zipOutputStream, privateKey, aesKey);
	storeLicenseContent(zipOutputStream, license, aesKey);
    }

    private void storeSecretKey(ZipOutputStream zipOutputStream,
	    PrivateKey privateKey, SecretKey aesKey) throws IOException,
	    InvalidKeyException {
	try {
	    ZipEntry key2ContentEntry = new ZipEntry(KEY2_ENTRY_NAME);
	    zipOutputStream.putNextEntry(key2ContentEntry);
	    byte[] encodedAESKey = aesKey.getEncoded();
	    byte[] encryptedAESKey = RSAUtilities.encrypt(privateKey,
		    encodedAESKey);
	    ByteArrayInputStream encryptedAESKeyStream = new ByteArrayInputStream(
		    encryptedAESKey);
	    IOUtils.copy(encryptedAESKeyStream, zipOutputStream);
	} catch (IllegalBlockSizeException e) {
	    throw new RuntimeException("Could not perform encryption.", e);
	}
    }

    private void storeLicenseContent(ZipOutputStream zipOutputStream,
	    License license, SecretKey secretKey) throws IOException {
	try {
	    ZipEntry licenseContentEntry = new ZipEntry(
		    LICENSE_CONTENT_ENTRY_NAME);
	    zipOutputStream.putNextEntry(licenseContentEntry);
	    Properties properties = licenseToProperties(license);
	    ByteArrayOutputStream propertiesStream = new ByteArrayOutputStream();
	    properties.store(propertiesStream, license.getProduct().toString()
		    + " license for " + license.getLicensee().toString()
		    + " issued from " + license.getLicenser().toString());
	    byte[] encryptedProperties = AESUtilities.encrypt(secretKey,
		    propertiesStream.toByteArray());
	    ByteArrayInputStream encryptedPropertiesStream = new ByteArrayInputStream(
		    encryptedProperties);
	    IOUtils.copy(encryptedPropertiesStream, zipOutputStream);
	} catch (InvalidKeyException e) {
	    throw new IllegalLicenseFileException(
		    "Could not encrypt properties!", e);
	} catch (IllegalBlockSizeException e) {
	    throw new IllegalLicenseFileException(
		    "Could not encrypt properties!", e);
	}
	;
    }

    private Properties licenseToProperties(License license) {
	Properties properties = new Properties();
	properties.put("product", license.getProduct().toString());
	properties.put("licenser", license.getLicenser().toString());
	properties.put("licensee", license.getLicensee().toString());
	properties.put("date.issue",
		String.valueOf(license.getIssueDate().getTime()));
	properties.put("date.expiration",
		String.valueOf(license.getExpirationDate().getTime()));
	for (LicensedClass licensedClass : license.getLicensedClasses()) {
	    properties.put("class."
		    + licensedClass.getLicensedClass().getName(), "licensed");
	}
	PublicKey publicKey = license.getPublicKey();
	String publicKeyString = RSAUtilities
		.convertPublicKeyToString(publicKey);
	properties.put("key", publicKeyString);
	return properties;
    }

    private License propertiesToLicense(Properties properties) {
	Product product = Product.fromString(properties.getProperty("product"));
	Licenser licenser = Licenser.fromString(properties
		.getProperty("licenser"));
	Licensee licensee = Licensee.fromString(properties
		.getProperty("licensee"));
	Date issueDate = new Date(Long.valueOf(properties
		.getProperty("date.issue")));
	Date expirationDate = new Date(Long.valueOf(properties
		.getProperty("date.expiration")));
	Set<LicensedClass> licensedClasses = new HashSet<LicensedClass>();
	for (Object key : properties.keySet()) {
	    String keyString = key.toString();
	    if (keyString.startsWith("class.")) {
		String className = keyString.substring("class.".length());
		try {
		    licensedClasses.add(new LicensedClass(Class
			    .forName(className)));
		} catch (ClassNotFoundException e) {
		    logger.warn("Licensed class '" + className
			    + "' is not present.");
		}
	    }
	}
	String publicKeyString = properties.getProperty("key");
	PublicKey publicKey = RSAUtilities
		.getPublicKeyFromString(publicKeyString);
	return new License(publicKey, product, issueDate, expirationDate,
		licenser, licensee, licensedClasses);
    }
}
