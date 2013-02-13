package com.puresol.coding.evaluation.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.analysis.api.ModuleStore;
import com.puresol.coding.analysis.api.ModuleStoreFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.utils.HashId;

public abstract class AbstractEvaluatorStore implements EvaluatorStore {

	protected abstract File getFileResultsFile(HashId hashId);

	protected final File getFileResultsFile(HashId hashId, Class<?> clazz) {
		CodeStore store = CodeStoreFactory.getFactory().getInstance();
		File directory = store.getFileDirectory(hashId);
		String fileName = clazz.getName();
		File file = new File(directory, fileName);
		return file;
	}

	protected abstract File getDirectoryResultsFile(HashId hashId);

	protected final File getDirectoryResultsFile(HashId hashId, Class<?> clazz) {
		ModuleStore store = ModuleStoreFactory.getFactory().getInstance();
		File directory = store.getDirectoryStoreDirectory(hashId);
		String fileName = clazz.getName();
		File file = new File(directory, fileName);
		return file;
	}

	protected abstract File getProjectResultsFile(AnalysisRun analysisRun);

	protected final File getProjectResultsFile(AnalysisRun analysisRun,
			Class<?> clazz) {
		File directory = AnalysisRunImpl.getStorageDirectory(analysisRun);
		String fileName = clazz.getName();
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
