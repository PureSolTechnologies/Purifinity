package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartTest {

	@Test
	public void testInstance() {
		assertNotNull(new Part(null, "Test Part"));
	}

	@Test
	public void testInitialValues() {
		Part doc = new Part(null, "Test Part");
		assertEquals("Test Part", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Part part = new Part(doc, "Test Part");
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}

}
