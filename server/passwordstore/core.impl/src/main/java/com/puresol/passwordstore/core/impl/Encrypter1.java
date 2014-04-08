package com.puresol.passwordstore.core.impl;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter1 {

	private static final int DEFAULT_SALT_LENGTH = 16;

	private final static Charset charset = Charset.forName("US-ASCII");

	private static final byte[] staticSalt = "{Peace, Love and Unity!}@allPlaces4Ever"
			.getBytes(charset);

	public static String encrypt(String password) {
		byte[] randomSalt = EncryptionUtilities
				.generateRandomBytes(DEFAULT_SALT_LENGTH);
		byte[] passwordBytes = password.getBytes(charset);

		return encrypt(randomSalt, passwordBytes);
	}

	public static boolean check(String password, String encryptedPassword) {
		String[] splits = encryptedPassword.split("\\$");
		if (splits.length != 2) {
			throw new IllegalArgumentException("Encrypted password is invalid!");
		}
		if ((splits[0].length() % 2 != 0) || (splits[1].length() % 2 != 0)) {
			throw new IllegalArgumentException("Encrypted password is invalid!");
		}
		byte[] randomSalt = EncryptionUtilities.convertStringToBytes(splits[0]);
		byte[] passwordBytes = password.getBytes(charset);

		String newEncryptedPassword = encrypt(randomSalt, passwordBytes);
		return newEncryptedPassword.equals(encryptedPassword);
	}

	private static String encrypt(byte[] randomSalt, byte[] password) {
		byte[] passwordHash = new byte[64];
		initializeAndSaltPasswordHash(randomSalt, staticSalt, password,
				passwordHash);
		passwordHash = iterate(randomSalt, staticSalt, passwordHash);
		return EncryptionUtilities.convertBytesToHexString(randomSalt) + "$"
				+ EncryptionUtilities.convertBytesToHexString(passwordHash);
	}

	private static byte[] iterate(byte[] randomSalt, byte[] staticSalt,
			byte[] passwordHash) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			int randomSaltLen = randomSalt.length;
			int staticSaltLen = staticSalt.length;
			int passwordHashLen = passwordHash.length;
			for (int i = 0; i < 1000; i++) {
				byte[] saltedPasswordHash = new byte[passwordHashLen
						+ staticSaltLen + randomSaltLen];
				for (int j = 0; j < staticSaltLen; j++) {
					saltedPasswordHash[j] = staticSalt[j];
				}
				for (int j = 0; j < passwordHashLen; j++) {
					saltedPasswordHash[staticSaltLen + j] = passwordHash[j];
				}
				for (int j = 0; j < randomSaltLen; j++) {
					saltedPasswordHash[staticSaltLen + passwordHashLen + j] = randomSalt[j];
				}
				passwordHash = digest.digest(saltedPasswordHash);
			}
			return passwordHash;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(
					"Expected SHA-512 algorithm is not provided!", e);
		}
	}

	private static void initializeAndSaltPasswordHash(byte[] randomSalt,
			byte[] staticSalt, byte[] password, byte[] passwordHash) {
		int randomSaltLen = randomSalt.length;
		int staticSaltLen = staticSalt.length;
		int passwordLen = password.length;
		int totalLen = passwordLen + randomSaltLen + staticSaltLen;

		for (int i = 0; i < 64; i++) {
			int pos = i % totalLen;
			if (pos < passwordLen) {
				passwordHash[i] = password[pos];
			} else if (pos < passwordLen + randomSaltLen) {
				passwordHash[i] = randomSalt[pos - passwordLen];
			} else {
				passwordHash[i] = staticSalt[pos - passwordLen - randomSaltLen];
			}
		}
	}
}
