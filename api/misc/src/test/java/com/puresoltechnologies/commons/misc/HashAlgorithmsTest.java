package com.puresoltechnologies.commons.misc;

import static org.junit.Assert.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class HashAlgorithmsTest {

	@Test
	public void testForAvailability() throws NoSuchAlgorithmException {
		HashAlgorithm[] algorithms = HashAlgorithm.class.getEnumConstants();
		for (HashAlgorithm algorithm : algorithms) {
			assertNotNull(MessageDigest.getInstance(algorithm
					.getAlgorithmName()));
		}
	}

	@Test
	public void testFormName() throws NoSuchAlgorithmException {
		for (HashAlgorithm algorithm : HashAlgorithm.values()) {
			assertNotNull(HashAlgorithm.fromAlgorithmName(algorithm
					.getAlgorithmName()));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalFormName() throws NoSuchAlgorithmException {
		HashAlgorithm.fromAlgorithmName("UnkownAlgorithm");
	}

}
