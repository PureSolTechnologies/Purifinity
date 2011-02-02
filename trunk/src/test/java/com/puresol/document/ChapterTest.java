package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChapterTest {

	@Test
	public void testInstance() {
		assertNotNull(new Chapter(null, "test_chapter", "Test Chapter"));
	}

	@Test
	public void testInitialValues() {
		Chapter doc = new Chapter(null, "test_chapter", "Test Chapter");
		assertEquals("test_chapter", doc.getName());
		assertEquals("Test Chapter", doc.getTitle());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
