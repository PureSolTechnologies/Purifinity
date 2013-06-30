package com.puresol.purifinity.coding.store.fs.evaluation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.store.fs.analysis.AnalysisRunImpl;
import com.puresol.purifinity.coding.store.fs.analysis.DirectoryStoreImpl;
import com.puresol.purifinity.coding.store.fs.analysis.FileStoreImpl;
import com.puresol.purifinity.utils.HashId;

public abstract class AbstractEvaluatorStore implements EvaluatorStore {

    protected abstract Class<?> getFileResultClass();

    protected abstract Class<?> getDirectoryResultClass();

    protected abstract Class<?> getProjectResultClass();

    protected final File getFileResultsFile(HashId hashId) {
	File directory = FileStoreImpl.getFileDirectory(hashId);
	String fileName = getFileResultClass().getName();
	File file = new File(directory, fileName);
	return file;
    }

    protected final File getDirectoryResultsFile(HashId hashId) {
	File directory = DirectoryStoreImpl.getDirectoryStoreDirectory(hashId);
	String fileName = getDirectoryResultClass().getName();
	File file = new File(directory, fileName);
	return file;
    }

    protected final File getProjectResultsFile(AnalysisRun analysisRun) {
	File directory = AnalysisRunImpl.getStorageDirectory(analysisRun);
	String fileName = getProjectResultClass().getName();
	File file = new File(directory, fileName);
	return file;
    }

    @Override
    public final boolean hasFileResults(HashId hashId) {

	return getFileResultsFile(hashId).exists();
    }

    @Override
    public final boolean hasDirectoryResults(HashId hashId) {
	return getDirectoryResultsFile(hashId).exists();
    }

    @Override
    public final boolean hasProjectResults(AnalysisRun analysisRun) {
	return getProjectResultsFile(analysisRun).exists();
    }

    protected final static void persist(Object results, File file) {
	try {
	    FileOutputStream outputStream = new FileOutputStream(file);
	    try {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
			outputStream);
		try {
		    objectOutputStream.writeObject(results);
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		outputStream.close();
	    }
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    protected final static <T> T restore(File file, Class<T> clazz) {

	try {
	    FileInputStream inputStream = new FileInputStream(file);
	    try {
		ObjectInputStream objectOutputStream = new ObjectInputStream(
			inputStream);
		try {
		    @SuppressWarnings("unchecked")
		    T t = (T) objectOutputStream.readObject();
		    return t;
		} finally {
		    objectOutputStream.close();
		}
	    } finally {
		inputStream.close();
	    }
	} catch (IOException e) {
	    throw new RuntimeException(e);
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException(e);
	}
    }
}