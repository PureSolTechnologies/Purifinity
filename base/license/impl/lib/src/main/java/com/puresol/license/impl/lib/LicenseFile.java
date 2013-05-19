package com.puresol.license.impl.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import org.apache.commons.io.IOUtils;

import com.puresol.license.api.License;
import com.puresol.license.api.LicensedClass;
import com.puresol.license.api.exception.IllegalLicenseFileException;
import com.puresol.utils.crypt.AESUtilities;
import com.puresol.utils.crypt.RSAUtilities;

public class LicenseFile {

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
			// InputStreamScrambler inputStreamScrambler = new
			// InputStreamScrambler(
			// p.getBytes(), fileInputStream);
			// try {
			ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
			try {
				return read(zipInputStream);
			} finally {
				zipInputStream.close();
			}
			// } finally {
			// inputStreamScrambler.close();
			// }
		} finally {
			fileInputStream.close();
		}
	}

	private License read(ZipInputStream zipInputStream) throws IOException {
		PublicKey publicKey = extractPublicKey(zipInputStream);
		publicKey.getAlgorithm();
		ZipEntry key2Entry = zipInputStream.getNextEntry();
		String key2EntryName = key2Entry.getName();
		if (!KEY2_ENTRY_NAME.equals(key2EntryName)) {
			throw new IllegalLicenseFileException(
					"This license file is not valid!");
		}
		ZipEntry licenseContentEntry = zipInputStream.getNextEntry();
		String licenseContentEntryName = licenseContentEntry.getName();
		if (!LICENSE_CONTENT_ENTRY_NAME.equals(licenseContentEntryName)) {
			throw new IllegalLicenseFileException(
					"This license file is not valid!");
		}
		return null;
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
		PublicKey publicKey = null;
		try {
			IOUtils.copy(zipInputStream, publicKeyStream);
			String publicKeyString = publicKeyStream.toString();
			publicKey = RSAUtilities.getPublicKeyFromString(publicKeyString);
		} finally {
			publicKeyStream.close();
		}
		return publicKey;
	}

	public void writeLicense(License license, PrivateKey privateKey)
			throws IOException, InvalidKeyException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try {
			// OutputStreamScrambler outputStreamScrambler = new
			// OutputStreamScrambler(
			// p.getBytes(), fileOutputStream);
			// try {
			ZipOutputStream zipOutputStream = new ZipOutputStream(
					fileOutputStream);
			try {
				write(zipOutputStream, license, privateKey);
			} finally {
				zipOutputStream.close();
			}
			// } finally {
			// outputStreamScrambler.close();
			// }
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
}
