package com.puresoltechnologies.commons.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provides some utilities for the analysis store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HashUtilities {

	private static final HashAlgorithm DEFAULT_MESSAGE_DIGEST_ALGORITHM = HashAlgorithm.SHA256;

	public static HashAlgorithm getDefaultMessageDigestAlgorithm() {
		return DEFAULT_MESSAGE_DIGEST_ALGORITHM;
	}

	public static MessageDigest getDefaultMessageDigest() {
		try {
			return MessageDigest.getInstance(DEFAULT_MESSAGE_DIGEST_ALGORITHM
					.getAlgorithmName());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Default message digest algorithm '"
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
	public static HashId createHashId(File file) throws IOException {
		try (FileInputStream stream = new FileInputStream(file)) {
			return createHashId(stream);
		}
	}

	private static HashId createHashId(InputStream stream) throws IOException {
		try (DigestInputStream digestInputStream = new DigestInputStream(
				stream, HashUtilities.getDefaultMessageDigest())) {
			byte buffer[] = new byte[256];
			while (digestInputStream.read(buffer) >= 0)
				;
			byte[] hashBytes = digestInputStream.getMessageDigest().digest();
			String hashString = convertByteArrayToString(hashBytes);
			return new HashId(HashUtilities.getDefaultMessageDigestAlgorithm(),
					hashString);
		}
	}

	/**
	 * This method converts a byte array into a string converting each byte into
	 * a 2-digit hex representation and appending them all together.
	 * 
	 * @param byteArray
	 *            is the array of bytes to be converted.
	 * @return A {@link String} is returned representing the byte array.
	 */
	private static String convertByteArrayToString(byte[] byteArray) {
		if (byteArray == null) {
			throw new IllegalArgumentException("Byte array must not be null!");
		}
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			int digit = 0xFF & byteArray[i];
			String hexDigits = Integer.toHexString(digit);
			if (hexDigits.length() < 2) {
				hexString.append("0");
			}
			hexString.append(hexDigits);
		}
		return hexString.toString();
	}
}
