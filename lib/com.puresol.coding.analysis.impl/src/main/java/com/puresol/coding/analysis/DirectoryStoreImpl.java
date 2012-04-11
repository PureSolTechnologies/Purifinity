package com.puresol.coding.analysis;

import java.io.File;

import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.utils.HashId;

public class DirectoryStoreImpl implements DirectoryStore {

    private final File directoryStoreDirectory;

    public DirectoryStoreImpl() {
	directoryStoreDirectory = new File(
		AnalysisStoreImpl.getStorageDirectory(), "dirs");
    }

    @Override
    public boolean createDirectory(HashId hashId)
	    throws DirectoryStoreException {
	File directory = getDirectoryStoreDirectory(directoryStoreDirectory,
		hashId);
	if (!directory.exists()) {
	    if (!directory.mkdirs()) {
		throw new DirectoryStoreException(
			"Could not create directory with hash '" + hashId + "'");
	    }
	    return true;
	}
	return false;
    }

    static File getDirectoryStoreDirectory(File directoryStoreDirectory,
	    HashId hashId) {
	String hash = hashId.getHash();
	String subDir1 = hash.substring(0, 2);
	String subDir2 = hash.substring(2, 4);
	String subDir3 = hash.substring(4);
	return new File(new File(new File(directoryStoreDirectory, subDir1),
		subDir2), subDir3);
    }

}
