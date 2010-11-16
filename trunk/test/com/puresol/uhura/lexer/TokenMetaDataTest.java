package com.puresol.uhura.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenMetaDataTest {

	@Test
	public void testInstance() {
		assertNotNull(new TokenMetaData("test.java", 1, 2, 3, 4));
	}

	@Test
	public void testInitValues() {
		TokenMetaData metaData = new TokenMetaData("test.java", 1, 2, 3, 4);
		assertEquals("test.java", metaData.getSourceName());
		assertEquals(1, metaData.getId());
		assertEquals(2, metaData.getPos());
		assertEquals(3, metaData.getLine());
		assertEquals(4, metaData.getLineNum());
	}

	@Test
	public void testClone() {
		TokenMetaData metaData = new TokenMetaData("test.java", 1, 2, 3, 4);
		TokenMetaData cloned = metaData.clone();
		assertNotSame(metaData, cloned);
		assertEquals(metaData, cloned);
	}
}
