package com.puresol.coding.richclient.application.analysis;

import java.io.File;

import com.puresol.coding.analysis.api.ModuleStore;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.utils.HashId;

public class DirectoryStoreImpl implements ModuleStore {

	private static final File directoryStoreDirectory = new File(
			AnalysisStoreImpl.getStorageDirectory(), "dirs");

	@Override
	public boolean createPackage(HashId hashId) throws ModuleStoreException {
		File directory = getDirectoryStoreDirectory(hashId);
		if (!isAvailable(hashId)) {
			if (!directory.mkdirs()) {
				throw new ModuleStoreException(
						"Could not create directory with hash '" + hashId + "'");
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isAvailable(HashId hashId) {
		File directory = getDirectoryStoreDirectory(hashId);
		return directory.exists();
	}

	public static File getDirectoryStoreDirectory(HashId hashId) {
		String hash = hashId.getHash();
		String subDir1 = hash.substring(0, 2);
		String subDir2 = hash.substring(2, 4);
		String subDir3 = hash.substring(4);
		return new File(new File(new File(directoryStoreDirectory, subDir1),
				subDir2), subDir3);
	}

}
