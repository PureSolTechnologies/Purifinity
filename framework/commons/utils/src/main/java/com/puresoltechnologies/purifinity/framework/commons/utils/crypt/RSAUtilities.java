package com.puresoltechnologies.purifinity.framework.commons.utils.crypt;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.puresoltechnologies.purifinity.framework.commons.utils.StringUtils;

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
	 * This class encrypts a byte array of data.
	 * 
	 * @param privateKey
	 *            is the public key to be used for encryption.
	 * @param plainData
	 *            is the data to be encrypted.
	 * @return A data byte array is returned containing the encrypted data.
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static byte[] encrypt(PrivateKey privateKey, byte[] plainData)
			throws IllegalBlockSizeException, InvalidKeyException {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
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
	 * @param privateKey
	 *            is the private key to be used for encryption.
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

	/**
	 * This class de crypts an encrypted byte array of data.
	 * 
	 * @param publicKey
	 *            is the private key to be used for encryption.
	 * @param plainData
	 *            is the data to be encrypted.
	 * @return A data byte array is returned containing the encrypted data.
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 */
	public static byte[] decrypt(PublicKey publicKey, byte[] encryptedData)
			throws IllegalBlockSizeException, InvalidKeyException {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NAME);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return cipher.doFinal(encryptedData);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Could not decrypt data with RSA!", e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException("Could not decrypt data with RSA!", e);
		} catch (BadPaddingException e) {
			throw new RuntimeException("Could not decrypt data with RSA!", e);
		}
	}

	/**
	 * This method converts a public key into a {@link String} representation.
	 * 
	 * @param publicKey
	 *            is a {@link PublicKey} object representing the key to be
	 *            converted.
	 * @return A {@link String} is returned.
	 */
	public static String convertPublicKeyToString(PublicKey publicKey) {
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
				publicKey.getEncoded());
		return StringUtils.convertByteArrayToString(publicKeySpec.getEncoded());
	}

	/**
	 * This method converts a private key into a {@link String} representation.
	 * 
	 * @param publicKey
	 *            is a {@link PrivateKey} object representing the key to be
	 *            converted.
	 * @return A {@link String} is returned.
	 */
	public static String convertPrivateKeyToString(PrivateKey privateKey) {
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				privateKey.getEncoded());
		return StringUtils
				.convertByteArrayToString(privateKeySpec.getEncoded());
	}

	/**
	 * This method returns a new {@link KeyPair} which is restored from public
	 * and private key strings. The key string can be retrieved from
	 * {@link #convertPublicKeyToString(PublicKey)} and
	 * {@link #convertPrivateKeyToString(PrivateKey)}.
	 * 
	 * @param publicKey
	 *            is a {@link String} representing the public key.
	 * @param privateKey
	 *            is a {@link String} representing the private key.
	 * @return A new {@link KeyPair} is returned.
	 */
	public static KeyPair getKeyPairFromString(String publicKey,
			String privateKey) {
		return new KeyPair(getPublicKeyFromString(publicKey),
				getPrivateKeyFromString(privateKey));
	}

	public static PrivateKey getPrivateKeyFromString(String privateKey) {
		try {
			byte[] privateEncoded = StringUtils
					.convertStringToByteArray(privateKey);
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
					privateEncoded);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(privateKeySpec);
		} catch (InvalidKeySpecException e) {
			throw new IllegalArgumentException("Could not restore  key pair!",
					e);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Could not restore key pair!", e);
		}
	}

	public static PublicKey getPublicKeyFromString(String publicKey) {
		try {
			byte[] publicEncoded = StringUtils
					.convertStringToByteArray(publicKey);
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
					publicEncoded);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(publicKeySpec);
		} catch (InvalidKeySpecException e) {
			throw new IllegalArgumentException("Could not restore  key pair!",
					e);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Could not restore key pair!", e);
		}
	}
}
