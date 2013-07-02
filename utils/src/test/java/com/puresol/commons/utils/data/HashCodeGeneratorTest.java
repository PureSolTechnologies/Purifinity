package com.puresol.commons.utils.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.puresol.commons.utils.data.HashCodeGenerator;

public class HashCodeGeneratorTest {

	public static final String SAMPLE_STRING = "Hi, this is a sample string with some meaningless characters at its end... ;-) 12h!@#&^@#$qhdgqwj~`'}{;<>Z?";

	@Test
	public void checkAvailabilityOfMessageDigest()
			throws NoSuchAlgorithmException {
		String[] cryptoImpls = HashCodeGenerator
				.getCryptoImpls("MessageDigest");
		for (String cipher : cryptoImpls) {
			System.out.println(cipher);
		}
	}

	@Test
	public void checkAvailabilityOfMessageDigestMD5()
			throws NoSuchAlgorithmException {
		assertNotNull(MessageDigest.getInstance("MD5"));
	}

	@Test
	public void checkAvailabilityOfMessageDigestSHA()
			throws NoSuchAlgorithmException {
		assertNotNull(MessageDigest.getInstance("SHA"));
		assertNotNull(MessageDigest.getInstance("SHA1"));
		assertNotNull(MessageDigest.getInstance("SHA-1"));
	}

	@Test
	public void checkAvailabilityOfMessageDigestSHA256()
			throws NoSuchAlgorithmException {
		assertNotNull(MessageDigest.getInstance("SHA-256"));
	}

	@Test
	public void checkAvailabilityOfMessageDigestSHA384()
			throws NoSuchAlgorithmException {
		assertNotNull(MessageDigest.getInstance("SHA-384"));
	}

	@Test
	public void checkAvailabilityOfMessageDigestSHA512()
			throws NoSuchAlgorithmException {
		assertNotNull(MessageDigest.getInstance("SHA-512"));
	}

	@Test
	public void checkMD5() {
		String encoder = HashCodeGenerator.getMD5(SAMPLE_STRING);
		assertEquals("9c5a925bd4bab4207ba38d7c41bcbb64", encoder);
	}

	@Test
	public void checkSHA() {
		String encoder = HashCodeGenerator.getSHA(SAMPLE_STRING);
		assertEquals("bde5997ac914196a11392bf9e070f45ae5dd9b40", encoder);
	}

	@Test
	public void checkSHA256() {
		String encoder = HashCodeGenerator.getSHA256(SAMPLE_STRING);
		assertEquals(
				"7f24c25a0a1efc5f2240c6a6c400538c6760681b6522212373afc7c0c6030676",
				encoder);
	}

	@Test
	public void checkSHA384() {
		String encoder = HashCodeGenerator.getSHA384(SAMPLE_STRING);
		assertEquals(
				"e6890fd85b7d151cc6ba3e809c12a0dcff2c991295499d84383b39acb772a6e1e86a5de7aef79da2d5ce4f79a5fb5b76",
				encoder);
	}

	@Test
	public void checkSHA512() {
		String encoder = HashCodeGenerator.getSHA512(SAMPLE_STRING);
		assertEquals(
				"4679e1f1114bda58f5792c060a40715a4fe73e5ce989fab0238209c111ddae78742851a2cb77734f6d6bb8e57c95f506d4309c69efe8ba2a73b510aa1ba35773",
				encoder);
	}
}
