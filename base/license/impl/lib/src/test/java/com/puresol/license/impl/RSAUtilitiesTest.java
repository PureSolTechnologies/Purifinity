package com.puresol.license.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Test;

import com.puresol.license.impl.crypt.RSAUtilities;

public class RSAUtilitiesTest {

	@Test(expected = InvalidParameterException.class)
	public void testGenerateKeyPair511Bit() {
		KeyPair keyPair = RSAUtilities.generateKeyPair(511);
		assertNotNull(keyPair);
	}

	@Test
	public void testGenerateKeyPair512Bit() {
		KeyPair keyPair = RSAUtilities.generateKeyPair(512);
		assertNotNull(keyPair);
	}

	@Test
	public void testEncryptAndDecrypt() throws Exception {
		KeyPair keyPair = RSAUtilities.generateKeyPair(512);

		String message = "This is the secret message! ;-)";
		System.out.println(message);

		PublicKey publicKey = keyPair.getPublic();

		byte[] encryptedMessage = RSAUtilities.encrypt(publicKey,
				message.getBytes());
		System.out.println(new String(encryptedMessage));

		PrivateKey privateKey = keyPair.getPrivate();
		byte[] decryptedMessage = RSAUtilities.decrypt(privateKey,
				encryptedMessage);
		String decryptedMessageString = new String(decryptedMessage);
		System.out.println(decryptedMessageString);
		assertEquals(message, decryptedMessageString);
	}

}
