package com.puresol.purifinity.license.test;

import java.io.File;

import com.puresol.purifinity.license.creator.store.FileSystemLicenseStore;

public class TestFileSystemLicenseStore extends FileSystemLicenseStore {

	@Override
	public File getStorageDirectory() {
		return new File("target", "licenses");
	}
}
