package com.puresoltechnologies.purifinity.store.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;

/**
 * This class provides some utilities for the analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StoreUtilities {

	private static final HashAlgorithm DEFAULT_MESSAGE_DIGEST_ALGORITHM = HashAlgorithm.SHA256;

	public static HashAlgorithm getDefaultMessageDigestAlgorithm() {
		return DEFAULT_MESSAGE_DIGEST_ALGORITHM;
	}

	public static MessageDigest getDefaultMessageDigest()
			throws FileStoreException {
		try {
			return MessageDigest.getInstance(DEFAULT_MESSAGE_DIGEST_ALGORITHM
					.getAlgorithmName());
		} catch (NoSuchAlgorithmException e) {
			throw new FileStoreException("Default message digest algorithm '"
					+ DEFAULT_MESSAGE_DIGEST_ALGORITHM.getAlgorithmName()
					+ "' is not available.");
		}
	}

	/**
	 * This method creates a {@link HashId}
	 * 
	 * @param file
	 *            the file to be read to calculate the {@link HashId}.
	 * @return
	 * @throws FileStoreException
	 */
	public static HashId createHashId(File file) throws FileStoreException {
		try {
			FileInputStream stream = new FileInputStream(file);
			try {
				return createHashId(stream);
			} finally {
				stream.close();
			}
		} catch (IOException e) {
			throw new FileStoreException(
					"Could not caluclate HashId for file '" + file + "'.", e);
		}
	}

	public static HashId createHashId(InputStream stream)
			throws FileStoreException {
		try {
			DigestInputStream digestInputStream = new DigestInputStream(stream,
					StoreUtilities.getDefaultMessageDigest());
			try {
				byte buffer[] = new byte[256];
				while (digestInputStream.read(buffer) >= 0)
					;
				byte[] hashBytes = digestInputStream.getMessageDigest()
						.digest();
				String hashString = StringUtils
						.convertByteArrayToString(hashBytes);
				return new HashId(
						StoreUtilities.getDefaultMessageDigestAlgorithm(),
						hashString);
			} finally {
				digestInputStream.close();
			}
		} catch (IOException e) {
			throw new FileStoreException("Could not store raw file.", e);
		}

	}
}
