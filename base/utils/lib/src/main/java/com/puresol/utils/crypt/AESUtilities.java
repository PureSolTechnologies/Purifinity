package com.puresol.utils.crypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtilities {

	private static final String AES_ALGORITHM_NAME = "AES";

	public static SecretKey generateSecretKey(int size) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(size); // 192/256
			return kgen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not create secret key for AES.",
					e);
		}
	}

	public static SecretKey generateSecretKey(String password, byte[] salt) {
		try {
			SecretKeyFactory factory = SecretKeyFactory
					.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536,
					256);
			SecretKey tmp = factory.generateSecret(spec);
			return new SecretKeySpec(tmp.getEncoded(), "AES");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not create secret key for AES.",
					e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("Could not create secret key for AES.",
					e);
		}
	}

	/**
	 * This class encrypts a byte array of data.
	 * 
	 * @param secretKey
	 *            is the secret key to be used for encryption.
	 * @param plainData
	 *            is the data to be encrypted.
	 * @return A data byte array is returned containing the encrypted data.
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static byte[] encrypt(SecretKey secretKey, byte[] plainData)
			throws InvalidKeyException, IllegalBlockSizeException {
		try {
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(plainData);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not encrypt data with RSA!", e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("Could not encrypt data with RSA!", e);
		} catch (BadPaddingException e) {
			throw new RuntimeException("Could not encrypt data with RSA!", e);
		}
	}

	/**
	 * This class de crypts an encrypted byte array of data.
	 * 
	 * @param secretKey
	 *            is the secret key to be used for encryption.
	 * @param plainData
	 *            is the data to be encrypted.
	 * @return A data byte array is returned containing the encrypted data.
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static byte[] decrypt(SecretKey secretKey, byte[] encryptedData)
			throws IllegalBlockSizeException, InvalidKeyException {
		try {
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM_NAME);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(encryptedData);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not decrypt data with RSA!", e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("Could not decrypt data with RSA!", e);
		} catch (BadPaddingException e) {
			throw new RuntimeException("Could not decrypt data with RSA!", e);
		}
	}

}
