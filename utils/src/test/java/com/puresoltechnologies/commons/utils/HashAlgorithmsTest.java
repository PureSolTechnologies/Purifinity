package com.puresoltechnologies.commons.utils;

import static org.junit.Assert.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.puresoltechnologies.commons.utils.HashAlgorithm;

public class HashAlgorithmsTest {

	@Test
	public void testForAvailability() throws NoSuchAlgorithmException {
		HashAlgorithm[] algorithms = HashAlgorithm.class.getEnumConstants();
		for (HashAlgorithm algorithm : algorithms) {
			assertNotNull(MessageDigest.getInstance(algorithm
					.getAlgorithmName()));
		}
	}

}
