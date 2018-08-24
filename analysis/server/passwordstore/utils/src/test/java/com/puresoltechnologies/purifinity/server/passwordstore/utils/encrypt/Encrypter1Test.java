package com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Encrypter1Test {

	@Test
	public void testEncrypt() {
		String password = "test";
		String encrypted = Encrypter1.encrypt(password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
	}

	@Test
	public void testCheck() {
		String password = "testPasswordWithSomeLength...";
		String encrypted = Encrypter1.encrypt(password);
		System.out.println("Plain password: " + password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
		System.out.println("Encrypted password: " + encrypted);
		assertTrue(Encrypter1.check(password, encrypted));
	}

	@Test
	public void testCheckWithWrongPassword() {
		String password = "testPasswordWithSomeLength...";
		String encrypted = Encrypter1.encrypt(password);
		System.out.println("Plain password: " + password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
		System.out.println("Encrypted password: " + encrypted);
		assertFalse(Encrypter1.check(password + "Wrong!", encrypted));
	}

	@Test
	public void testCheckWithWrongSalt() {
		String password = "testPasswordWithSomeLength...";
		String encrypted = Encrypter1.encrypt(password);
		System.out.println("Plain password: " + password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
		System.out.println("Encrypted password: " + encrypted);
		assertFalse(Encrypter1.check(password, encrypted.substring(2)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckWithWrongHexSalt() {
		String password = "testPasswordWithSomeLength...";
		String encrypted = Encrypter1.encrypt(password);
		System.out.println("Plain password: " + password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
		System.out.println("Encrypted password: " + encrypted);
		assertFalse(Encrypter1.check(password, encrypted.substring(1)));
	}

	@Test
	public void testCheckWithWrongHash() {
		String password = "testPasswordWithSomeLength...";
		String encrypted = Encrypter1.encrypt(password);
		System.out.println("Plain password: " + password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
		System.out.println("Encrypted password: " + encrypted);
		assertFalse(Encrypter1.check(password,
				encrypted.substring(0, encrypted.length() - 2)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckWithWrongHexHash() {
		String password = "testPasswordWithSomeLength...";
		String encrypted = Encrypter1.encrypt(password);
		System.out.println("Plain password: " + password);
		assertNotNull(encrypted);
		assertFalse(encrypted.isEmpty());
		System.out.println("Encrypted password: " + encrypted);
		assertFalse(Encrypter1.check(password,
				encrypted.substring(0, encrypted.length() - 1)));
	}

}
