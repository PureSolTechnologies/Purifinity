package com.puresol.coding.richclient.application.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.HashId;

public final class FileStoreImpl implements CodeStore {

	private static final String CONTENT_FILE = "content.txt";
	private static final String PARSER_TREE_FILE = "parser_tree.persist";

	private static final File fileStoreDirectory = new File(
			AnalysisStoreImpl.getStorageDirectory(), "files");

	@Override
	public boolean storeCode(SourceCode sourceCode) throws CodeStoreException {
		try {
			File targetDirectory = getFileDirectory(sourceCode.getHashId());
			if (!targetDirectory.exists()) {
				if (!targetDirectory.mkdirs()) {
					throw new IOException("Could not create directory '"
							+ targetDirectory + "'");
				}
			}
			File targetFile = new File(targetDirectory, CONTENT_FILE);
			PersistenceUtils.store(targetFile, sourceCode);
			return true;
		} catch (IOException e) {
			throw new CodeStoreException("Could not store file with hash '"
					+ sourceCode.getHashId() + "'", e);
		}
	}

	@Override
	public CodeAnalysis loadAnalysis(HashId hashId) throws CodeStoreException {
		try {
			File fileDirectory = getFileDirectory(hashId);
			File parserTreeFile = new File(fileDirectory, PARSER_TREE_FILE);
			ObjectInputStream inStream = new ObjectInputStream(
					new FileInputStream(parserTreeFile));
			try {
				return (CodeAnalysis) inStream.readObject();
			} finally {
				inStream.close();
			}
		} catch (ClassNotFoundException e) {
			throw new CodeStoreException(
					"Could not load analysis for file with hash '" + hashId
							+ "'", e);
		} catch (IOException e) {
			throw new CodeStoreException(
					"Could not load analysis for file with hash '" + hashId
							+ "'", e);
		}
	}

	@Override
	public final void storeAnalysis(HashId hashId, CodeAnalysis fileAnalysis)
			throws CodeStoreException {
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
			throw new CodeStoreException(
					"Could not store analysis for file with hash '" + hashId
							+ "'", e);
		}
	}

	@Override
	public final boolean isAvailable(HashId hashId) {
		return getFileDirectory(hashId).exists();
	}

	@Override
	public final SourceCode loadContent(HashId hashId)
			throws CodeStoreException {
		try {
			File file = FileStoreImpl.getFileDirectory(hashId);
			SourceCode sourceCode = PersistenceUtils.restore(file);
			return sourceCode;
		} catch (IOException e) {
			throw new CodeStoreException("Could not load file with id '"
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
