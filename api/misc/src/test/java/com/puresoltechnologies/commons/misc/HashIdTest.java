package com.puresoltechnologies.commons.misc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HashIdTest {

	@Test
	public void testInstantiation() {
		HashId hashId = new HashId(HashAlgorithm.SHA384, "1234567890");
		assertEquals(HashAlgorithm.SHA384, hashId.getAlgorithm());
		assertEquals("1234567890", hashId.getHash());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiationNullAlgorithm() {
		new HashId(null, "1234567890");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiationNullHash() {
		new HashId(HashAlgorithm.SHA256, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInstantiationEmptyHash() {
		new HashId(HashAlgorithm.SHA256, "");
	}

}
