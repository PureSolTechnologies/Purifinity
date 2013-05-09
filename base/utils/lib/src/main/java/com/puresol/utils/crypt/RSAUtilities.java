package com.puresol.utils.crypt;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * This class contains utilities and simplifying helper functions for RSA
 * encryption.
 * 
 * For saving of keys have a look to http://www.jcraft.com/jsch/ for later
 * implementation!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RSAUtilities {

	private static final String RSA_ALGORITHM_NAME = "RSA";

	/**
	 * This method generates a key pair for RSA encryption.
	 * 
	 * @param size
	 *            is the size in bits of the to be generated key.
	 * @return
	 */
	public static KeyPair generateKeyPair(int size) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance(RSA_ALGORITHM_NAME);
			keyPairGenerator.initialize(size);
			return keyPairGenerator.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not generate RSA key pair!", e);
		}
	}

	/**
	 * This class encrypts a byte array of data.
	 * 
	 * @param publicKey
	 *            is the public key to be used for encryption.
	 * @param plainData
	 *            is the data to be encrypted.
	 * @return A data byte array is returned containing the encrypted data.
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[] plainData)
			throws IllegalBlockSizeException, InvalidKeyException {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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
	 * @param publicKey
	 *            is the public key to be used for encryption.
	 * @param plainData
	 *            is the data to be encrypted.
	 * @return A data byte array is returned containing the encrypted data.
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static byte[] decrypt(PrivateKey privateKey, byte[] encryptedData)
			throws IllegalBlockSizeException, InvalidKeyException {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NAME);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
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
