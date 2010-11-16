package com.puresol.uhura.ast;

import static org.junit.Assert.*;

import org.junit.Test;

public class ASTMetaDataTest {

	@Test
	public void testInstance() {
		assertNotNull(new ASTMetaData("test.java", 1, 2));
	}

	@Test
	public void testInitValues() {
		ASTMetaData metaData = new ASTMetaData("test.java", 1, 2);
		assertEquals("test.java", metaData.getSourceName());
		assertEquals(1, metaData.getLine());
		assertEquals(2, metaData.getLineNum());
	}

	@Test
	public void testEquals() {
		assertEquals(new ASTMetaData("test.java", 1, 1), new ASTMetaData(
				"test.java", 1, 1));
	}

	@Test
	public void testToString() {
		ASTMetaData metaData = new ASTMetaData("test.java", 1, 1);
		assertEquals("test.java: 1", metaData.toString());

		metaData = new ASTMetaData("test.java", 1, 2);
		assertEquals("test.java: 1 - 2", metaData.toString());
	}
}
