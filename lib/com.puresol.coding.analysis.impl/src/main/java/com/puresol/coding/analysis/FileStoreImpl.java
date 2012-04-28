package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.HashId;

public final class FileStoreImpl implements FileStore {

    private static final String CONTENT_FILE = "content.txt";
    private static final String PARSER_TREE_FILE = "parser_tree.persist";

    private static final File fileStoreDirectory = new File(
	    AnalysisStoreImpl.getStorageDirectory(), "files");

    @Override
    public boolean storeFile(HashId hashId, InputStream conent)
	    throws FileStoreException {
	try {
	    File targetDirectory = getFileDirectory(hashId);
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
	    throw new FileStoreException("Could not store file with hash '"
		    + hashId + "'", e);
	}
    }

    @Override
    public FileAnalysis loadAnalysis(HashId hashId) throws FileStoreException {
	try {
	    File fileDirectory = getFileDirectory(hashId);
	    File parserTreeFile = new File(fileDirectory, PARSER_TREE_FILE);
	    ObjectInputStream inStream = new ObjectInputStream(
		    new FileInputStream(parserTreeFile));
	    try {
		return (FileAnalysis) inStream.readObject();
	    } finally {
		inStream.close();
	    }
	} catch (ClassNotFoundException e) {
	    throw new FileStoreException(
		    "Could not load analysis for file with hash '" + hashId
			    + "'", e);
	} catch (IOException e) {
	    throw new FileStoreException(
		    "Could not load analysis for file with hash '" + hashId
			    + "'", e);
	}
    }

    @Override
    public final void storeAnalysis(HashId hashId, FileAnalysis fileAnalysis)
	    throws FileStoreException {
	try {
	    File fileDirectory = getFileDirectory(hashId);
	    File parserTreeFile = new File(fileDirectory, PARSER_TREE_FILE);
	    ObjectOutputStream outStream = new ObjectOutputStream(
		    new FileOutputStream(parserTreeFile));
	    try {
		outStream.writeObject(fileAnalysis);
	    } finally {
		outStream.close();
	    }
	} catch (IOException e) {
	    throw new FileStoreException(
		    "Could not store analysis for file with hash '" + hashId
			    + "'", e);
	}
    }

    @Override
    public final boolean isAvailable(HashId hashId) {
	return getFileDirectory(hashId).exists();
    }

    @Override
    public final InputStream loadContent(HashId hashId)
	    throws FileStoreException {
	try {
	    File file = FileStoreImpl.getFileDirectory(hashId);
	    return new FileInputStream(new File(file, CONTENT_FILE));
	} catch (FileNotFoundException e) {
	    throw new FileStoreException("Could not load file with id '"
		    + hashId.toString() + "'!", e);
	}
    }

    public static File getFileDirectory(HashId hashId) {
	String hash = hashId.getHash();
	String subDir1 = hash.substring(0, 2);
	String subDir2 = hash.substring(2, 4);
	String subDir3 = hash.substring(4);
	return new File(
		new File(new File(fileStoreDirectory, subDir1), subDir2),
		subDir3);
    }

    @Override
    public final boolean wasAnalyzed(HashId hashId) {
	File fileDirectory = getFileDirectory(hashId);
	File parserTreeFile = new File(fileDirectory, PARSER_TREE_FILE);
	return parserTreeFile.exists();
    }
}
