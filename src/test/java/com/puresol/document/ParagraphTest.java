package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParagraphTest {

	@Test
	public void testInstance() {
		assertNotNull(new Paragraph(null, "Test Paragraph"));
	}

	@Test
	public void testInitialValues() {
		Paragraph doc = new Paragraph(null, "Test Paragraph");
		assertEquals("Test Paragraph", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Paragraph part = new Paragraph(doc, "Test Paragraph");
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}

}
