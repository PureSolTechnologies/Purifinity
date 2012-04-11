package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashId;

public class FileStoreImpl implements FileStore {

    private static final String CONTENT_FILE = "content.txt";

    private final File fileStoreDirectory;

    public FileStoreImpl() {
	fileStoreDirectory = new File(AnalysisStoreImpl.getStorageDirectory(),
		"files");
    }

    @Override
    public AnalyzedFile loadFile(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean storeFile(HashId hashId, InputStream conent)
	    throws DirectoryStoreException {
	try {
	    File targetDirectory = getFileStoreDirectory(fileStoreDirectory,
		    hashId);
	    if (!targetDirectory.exists()) {
		if (!targetDirectory.mkdirs()) {
		    throw new IOException("Could not create directory '"
			    + targetDirectory + "'");
		}
	    }
	    FileOutputStream target = new FileOutputStream(new File(
		    targetDirectory, CONTENT_FILE));
	    try {
		FileUtilities.copy(conent, target);
	    } finally {
		target.close();
	    }
	    return false;
	} catch (IOException e) {
	    throw new DirectoryStoreException(
		    "Could not store file with hash '" + hashId + "'", e);
	}
    }

    @Override
    public FileAnalysis loadAnalysis(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void storeAnalysis(HashId hashId, FileAnalysis fileAnalysis) {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean isAvailable(HashId hashId) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public InputStream loadContent(HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    static File getFileStoreDirectory(File fileStoreDirectory, HashId hashId) {
	String hash = hashId.getHash();
	String subDir1 = hash.substring(0, 2);
	String subDir2 = hash.substring(2, 4);
	String subDir3 = hash.substring(4);
	return new File(
		new File(new File(fileStoreDirectory, subDir1), subDir2),
		subDir3);
    }
}
