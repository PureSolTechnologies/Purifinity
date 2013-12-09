package com.puresoltechnologies.commons.utils.crypt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.crypto.SecretKey;

import org.junit.Test;

import com.puresoltechnologies.commons.utils.crypt.AESUtilities;

public class AESUtilitiesTest {

	@Test
	public void testGenerateSecreatKeyWithoutPassword128bit() {
		SecretKey secretKey = AESUtilities.generateSecretKey(128);
		assertNotNull(secretKey);
	}

	@Test
	public void testGenerateSecreatKeyWithoutPassword192bit() {
		SecretKey secretKey = AESUtilities.generateSecretKey(192);
		assertNotNull(secretKey);
	}

	@Test
	public void testGenerateSecreatKeyWithoutPassword256bit() {
		SecretKey secretKey = AESUtilities.generateSecretKey(256);
		assertNotNull(secretKey);
	}

	@Test
	public void testGenerateSecreatKeyWithPassword() {
		SecretKey secretKey = AESUtilities.generateSecretKey("password",
				"salt".getBytes());
		assertNotNull(secretKey);
	}

	@Test
	public void testEncryptAndDecrypt() throws Exception {
		SecretKey secretKey = AESUtilities.generateSecretKey(256);

		String message = "This is the secret message! ;-)";
		System.out.println(message);

		byte[] encryptedMessage = AESUtilities.encrypt(secretKey,
				message.getBytes());
		System.out.println(new String(encryptedMessage));

		byte[] decryptedMessage = AESUtilities.decrypt(secretKey,
				encryptedMessage);
		String decryptedMessageString = new String(decryptedMessage);
		System.out.println(decryptedMessageString);
		assertEquals(message, decryptedMessageString);
	}

}
