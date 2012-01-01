package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartTest {

	@Test
	public void testInstance() {
		assertNotNull(new Part(null, "test_part", "Test Part"));
	}

	@Test
	public void testInitialValues() {
		Part doc = new Part(null, "test_part", "Test Part");
		assertEquals("test_part", doc.getName());
		assertEquals("Test Part", doc.getTitle());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Part part = new Part(doc, "test_part", "Test Part");
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}

}
