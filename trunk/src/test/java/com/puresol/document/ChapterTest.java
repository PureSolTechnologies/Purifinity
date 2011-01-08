package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChapterTest {

	@Test
	public void testInstance() {
		assertNotNull(new Chapter(null, "Test Chapter"));
	}

	@Test
	public void testInitialValues() {
		Chapter doc = new Chapter(null, "Test Chapter");
		assertEquals("Test Chapter", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
