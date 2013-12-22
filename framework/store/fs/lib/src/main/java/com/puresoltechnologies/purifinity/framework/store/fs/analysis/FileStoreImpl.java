package com.puresoltechnologies.purifinity.framework.store.fs.analysis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.DigestInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.impl.source.SourceCodeImpl;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;

public final class FileStoreImpl implements FileStore {

	private static final String RAW_FILE = "raw";
	private static final String CODE_ANALYSIS_FILE = "code_analysis.persist";

	private static final File fileStoreDirectory = new File(
			AnalysisStoreImpl.getStorageDirectory(), "files");

	@Override
	public HashId storeRawFile(InputStream rawStream) throws FileStoreException {
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			try {
				DigestInputStream digestInputStream = new DigestInputStream(
						rawStream, HashUtilities.getDefaultMessageDigest());
				try {
					IOUtils.copy(digestInputStream, buffer);
					byte[] hashBytes = digestInputStream.getMessageDigest()
							.digest();
					String hashString = StringUtils
							.convertByteArrayToString(hashBytes);
					HashId hashId = new HashId(
							HashUtilities.getDefaultMessageDigestAlgorithm(),
							hashString);
					File targetDirectory = getFileDirectory(hashId);
					File targetFile = new File(targetDirectory, RAW_FILE);
					if (!targetFile.exists()) {
						checkAndCreateDirectory(targetDirectory);
						ByteArrayInputStream inputBuffer = new ByteArrayInputStream(
								buffer.toByteArray());
						try {
							FileUtils.copyInputStreamToFile(inputBuffer,
									targetFile);
						} finally {
							inputBuffer.close();
						}
					}
					return hashId;
				} finally {
					digestInputStream.close();
				}
			} finally {
				buffer.close();
			}
		} catch (IOException e) {
			throw new FileStoreException("Could not store raw file.", e);
		}
	}

	private void checkAndCreateDirectory(File targetDirectory)
			throws IOException {
		if (!targetDirectory.exists()) {
			if (!targetDirectory.mkdirs()) {
				throw new IOException("Could not create directory '"
						+ targetDirectory + "'");
			}
		}
	}

	@Override
	public InputStream readRawFile(HashId hashId) throws FileStoreException {
		try {
			File fileDirectory = getFileDirectory(hashId);
			File rawFile = new File(fileDirectory, RAW_FILE);
			return new FileInputStream(rawFile);
		} catch (FileNotFoundException e) {
			throw new FileStoreException("Could not find file with hash id '"
					+ hashId + "'.", e);
		}
	}

	@Override
	public CodeAnalysis loadAnalysis(HashId hashId) throws FileStoreException {
		try {
			File fileDirectory = getFileDirectory(hashId);
			File parserTreeFile = new File(fileDirectory, CODE_ANALYSIS_FILE);
			ObjectInputStream inStream = new ObjectInputStream(
					new FileInputStream(parserTreeFile));
			try {
				return (CodeAnalysis) inStream.readObject();
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
	public final void storeAnalysis(HashId hashId, CodeAnalysis fileAnalysis)
			throws FileStoreException {
		try {
			File fileDirectory = getFileDirectory(hashId);
			File parserTreeFile = new File(fileDirectory, CODE_ANALYSIS_FILE);
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
	public final SourceCode readSourceCode(HashId hashId)
			throws FileStoreException {
		try {
			InputStream inputStream = readRawFile(hashId);
			try {
				return SourceCodeImpl.read(inputStream,
						new UnspecifiedSourceCodeLocation());
			} finally {
				inputStream.close();
			}
		} catch (IOException e) {
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
		File parserTreeFile = new File(fileDirectory, CODE_ANALYSIS_FILE);
		return parserTreeFile.exists();
	}
}
