package com.puresoltechnologies.purifinity.framework.store.db.evaluation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.AnalysisStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.DirectoryStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.FileStoreImpl;

/**
 * This is an abstract implementation of an evaluator store.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractEvaluatorStore implements EvaluatorStore {

	protected abstract Class<? extends MetricFileResults> getFileResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getDirectoryResultClass();

	protected abstract Class<? extends MetricDirectoryResults> getProjectResultClass();

	protected final File getFileResultsFile(HashId hashId) {
		Class<? extends MetricFileResults> fileResultClass = getFileResultClass();
		if (fileResultClass != null) {
			File directory = FileStoreImpl.getFileDirectory(hashId);
			String fileName = fileResultClass.getName();
			return new File(directory, fileName);
		}
		return null;
	}

	protected final File getDirectoryResultsFile(HashId hashId) {
		Class<? extends MetricDirectoryResults> directoryResultClass = getDirectoryResultClass();
		if (directoryResultClass != null) {
			File directory = DirectoryStoreImpl
					.getDirectoryStoreDirectory(hashId);
			String fileName = directoryResultClass.getName();
			return new File(directory, fileName);
		} else {
			return null;
		}
	}

	protected final File getProjectResultsFile(AnalysisRun analysisRun) {
		Class<? extends MetricDirectoryResults> projectResultClass = getProjectResultClass();
		if (projectResultClass != null) {
			File directory = getStorageDirectory(analysisRun);
			String fileName = projectResultClass.getName();
			return new File(directory, fileName);
		}
		return null;
	}

	/**
	 * This method returns the storage directory of the given analysis run.
	 * 
	 * @param analysisRun
	 * @return
	 */
	public static File getStorageDirectory(AnalysisRun analysisRun) {
		File analysisStorageDirectory = AnalysisStoreImpl
				.getStorageDirectory(analysisRun.getInformation()
						.getAnalysisProjectUUID().getInformation().getUUID());
		return new File(analysisStorageDirectory, analysisRun.getInformation()
				.getUUID().toString());
	}

	@Override
	public final boolean hasFileResults(HashId hashId) {
		File fileResultsFile = getFileResultsFile(hashId);
		if (fileResultsFile != null) {
			return fileResultsFile.exists();
		}
		return false;
	}

	@Override
	public final boolean hasDirectoryResults(HashId hashId) {
		File directoryResultsFile = getDirectoryResultsFile(hashId);
		if (directoryResultsFile != null) {
			return directoryResultsFile.exists();
		}
		return false;
	}

	@Override
	public final boolean hasProjectResults(AnalysisRun analysisRun) {
		File projectResultsFile = getProjectResultsFile(analysisRun);
		if (projectResultsFile != null) {
			return projectResultsFile.exists();
		}
		return false;
	}

	private final void persist(Object results, File file) {
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
			throw new RuntimeException("Could not store object in file '"
					+ file + "'.", e);
		}
	}

	@Override
	public final void storeFileResults(HashId hashId, MetricFileResults results) {
		File file = getFileResultsFile(hashId);
		if (file != null) {
			persist(results, file);
		}
	}

	@Override
	public final void storeDirectoryResults(HashId hashId,
			MetricDirectoryResults results) {
		File file = getDirectoryResultsFile(hashId);
		if (file != null) {
			persist(results, file);
		}
	}

	@Override
	public final void storeProjectResults(AnalysisRun analysisRun,
			MetricDirectoryResults results) {
		File file = getProjectResultsFile(analysisRun);
		if (file != null) {
			persist(results, file);
		}
	}

	private <T> T restore(File file, Class<T> clazz) {
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
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException("Could not restore object of type '"
					+ clazz.getName() + "' from file '" + file + "'.", e);
		}
	}

	@Override
	public final MetricFileResults readFileResults(HashId hashId) {
		if (hasFileResults(hashId)) {
			File file = getFileResultsFile(hashId);
			if (file != null) {
				return restore(file, getFileResultClass());
			}
		}
		return null;
	}

	@Override
	public final MetricDirectoryResults readDirectoryResults(HashId hashId) {
		if (hasDirectoryResults(hashId)) {
			File file = getDirectoryResultsFile(hashId);
			if (file != null) {
				return restore(file, getDirectoryResultClass());
			}
		}
		return null;
	}

	@Override
	public final MetricDirectoryResults readProjectResults(
			AnalysisRun analysisRun) {
		if (hasProjectResults(analysisRun)) {
			File file = getProjectResultsFile(analysisRun);
			if (file != null) {
				return restore(file, getProjectResultClass());
			}
		}
		return null;
	}
}
