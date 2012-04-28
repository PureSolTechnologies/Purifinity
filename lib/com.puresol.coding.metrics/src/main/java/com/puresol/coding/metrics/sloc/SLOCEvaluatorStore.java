package com.puresol.coding.metrics.sloc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.analysis.DirectoryStoreImpl;
import com.puresol.coding.analysis.FileStoreImpl;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.utils.HashId;

public class SLOCEvaluatorStore implements
	EvaluatorStore<SLOCFileResult, SLOCDirectoryResult, SLOCProjectResult> {

    @Override
    public void storeFileResults(HashId hashId, SLOCFileResult results) {
	File directory = FileStoreImpl.getFileDirectory(hashId);
	String fileName = SLOCFileResult.class.getName();
	File file = new File(directory, fileName);
	persist(results, file);
    }

    @Override
    public void storeDirectoryResults(HashId hashId, SLOCDirectoryResult results) {
	File directory = DirectoryStoreImpl.getDirectoryStoreDirectory(hashId);
	String fileName = SLOCDirectoryResult.class.getName();
	File file = new File(directory, fileName);
	persist(results, file);
    }

    @Override
    public void storeProjectResults(AnalysisRun analysisRun,
	    SLOCProjectResult results) {
	File directory = AnalysisRunImpl.getStorageDirectory(analysisRun);
	String fileName = SLOCProjectResult.class.getName();
	File file = new File(directory, fileName);
	persist(results, file);
    }

    @Override
    public SLOCFileResult readFileResults(HashId hashId) {
	File directory = FileStoreImpl.getFileDirectory(hashId);
	String fileName = SLOCFileResult.class.getName();
	File file = new File(directory, fileName);
	return restore(file, SLOCFileResult.class);
    }

    @Override
    public SLOCDirectoryResult readDirectoryResults(HashId hashId) {
	File directory = DirectoryStoreImpl.getDirectoryStoreDirectory(hashId);
	String fileName = SLOCDirectoryResult.class.getName();
	File file = new File(directory, fileName);
	return restore(file, SLOCDirectoryResult.class);
    }

    @Override
    public SLOCProjectResult readProjectResults(AnalysisRun analysisRun) {
	File directory = AnalysisRunImpl.getStorageDirectory(analysisRun);
	String fileName = SLOCProjectResult.class.getName();
	File file = new File(directory, fileName);
	return restore(file, SLOCProjectResult.class);
    }

    private static void persist(Object results, File file) {
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

    private static <T> T restore(File file, Class<T> clazz) {
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
