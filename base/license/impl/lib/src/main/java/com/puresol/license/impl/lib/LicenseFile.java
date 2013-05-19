package com.puresol.license.impl.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.puresol.license.api.License;
import com.puresol.license.api.exception.IllegalLicenseFileException;

public class LicenseFile {

	private static final String KEY_ENTRY_NAME = "key";
	private static final String LICENSE_CONTENT_ENTRY_NAME = "license.content";
	private static final String p = "PureSol Technologies";

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
		ZipEntry keyEntry = zipInputStream.getNextEntry();
		String keyEntryName = keyEntry.getName();
		if (!KEY_ENTRY_NAME.equals(keyEntryName)) {
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

	public void writeLicense(License license) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		try {
			// OutputStreamScrambler outputStreamScrambler = new
			// OutputStreamScrambler(
			// p.getBytes(), fileOutputStream);
			// try {
			ZipOutputStream zipOutputStream = new ZipOutputStream(
					fileOutputStream);
			try {
				write(zipOutputStream, license);
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

	private void write(ZipOutputStream zipOutputStream, License license)
			throws IOException {
		ZipEntry keyEntry = new ZipEntry(KEY_ENTRY_NAME);
		zipOutputStream.putNextEntry(keyEntry);

		ZipEntry licenseContentEntry = new ZipEntry(LICENSE_CONTENT_ENTRY_NAME);
		zipOutputStream.putNextEntry(licenseContentEntry);
	}

}
