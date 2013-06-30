package com.puresol.purifinity.coding.store.fs.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.puresol.purifinity.coding.analysis.api.DirectoryStore;
import com.puresol.purifinity.coding.analysis.api.DirectoryStoreException;
import com.puresol.purifinity.utils.HashId;

public class DirectoryStoreImpl implements DirectoryStore {

    private static final String FILES_FILE = "files.persist";
    private static final String DIRECTORIES_FILE = "directories.persist";

    private static final File directoryStoreDirectory = new File(
	    AnalysisStoreImpl.getStorageDirectory(), "dirs");

    public static File getDirectoryStoreDirectory(HashId hashId) {
	String hash = hashId.getHash();
	String subDir1 = hash.substring(0, 2);
	String subDir2 = hash.substring(2, 4);
	String subDir3 = hash.substring(4);
	return new File(new File(new File(directoryStoreDirectory, subDir1),
		subDir2), subDir3);
    }

    @Override
    public boolean createPackage(HashId hashId, List<HashId> files,
	    List<HashId> directories) throws DirectoryStoreException {
	File directory = getDirectoryStoreDirectory(hashId);
	if (isAvailable(hashId)) {
	    return false;
	}
	if (!directory.mkdirs()) {
	    throw new DirectoryStoreException(
		    "Could not create directory with hash '" + hashId + "'");
	}
	store(files, new File(getDirectoryStoreDirectory(hashId), FILES_FILE));
	store(directories, new File(getDirectoryStoreDirectory(hashId),
		DIRECTORIES_FILE));
	return true;
    }

    @Override
    public boolean isAvailable(HashId hashId) {
	File directory = getDirectoryStoreDirectory(hashId);
	return directory.exists();
    }

    @Override
    public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
	return restore(new File(getDirectoryStoreDirectory(hashId), FILES_FILE));
    }

    @Override
    public List<HashId> getDirectories(HashId hashId)
	    throws DirectoryStoreException {
	return restore(new File(getDirectoryStoreDirectory(hashId),
		DIRECTORIES_FILE));
    }

    private void store(List<HashId> files, File file)
	    throws DirectoryStoreException {
	try {
	    FileOutputStream outputStream = new FileOutputStream(file);
	    try {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
			outputStream);
		try {
		    objectOutputStream.writeObject(files);
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		outputStream.close();
	    }
	} catch (IOException e) {
	    throw new DirectoryStoreException(
		    "Could not store directory contents to '" + file + "'.", e);
	}
    }

    private List<HashId> restore(File file) throws DirectoryStoreException {
	try {
	    FileInputStream outputStream = new FileInputStream(file);
	    try {
		ObjectInputStream objectOutputStream = new ObjectInputStream(
			outputStream);
		try {
		    @SuppressWarnings("unchecked")
		    List<HashId> files = (List<HashId>) objectOutputStream
			    .readObject();
		    return files;
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		outputStream.close();
	    }
	} catch (ClassNotFoundException e) {
	    throw new DirectoryStoreException(
		    "Could not read directory contents to '" + file + "'.", e);
	} catch (IOException e) {
	    throw new DirectoryStoreException(
		    "Could not read directory contents to '" + file + "'.", e);
	}
    }
}
