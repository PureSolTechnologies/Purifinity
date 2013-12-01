package com.puresoltechnologies.purifinity.license.test;

import java.io.File;

import com.puresoltechnologies.purifinity.license.creator.store.FileSystemLicenseStore;

public class TestFileSystemLicenseStore extends FileSystemLicenseStore {

	@Override
	public File getStorageDirectory() {
		return new File("target", "licenses");
	}
}
