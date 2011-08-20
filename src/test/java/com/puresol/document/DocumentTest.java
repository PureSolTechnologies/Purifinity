package com.puresol.document;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class DocumentTest {

	@Test
	public void testInstance() {
		assertNotNull(new Document("Test Document"));
	}

	@Test
	public void testInitialValues() {
		Document doc = new Document("Test Document");
		assertEquals("Test Document", doc.getName());
		assertEquals("", doc.getAuthor());
		assertEquals("", doc.getVersion());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

	@Test
	public void testSettersAndGetters() {
		Document doc = new Document("Test Document");
		doc.setAuthor("Rick-Rainer Ludwig");
		doc.setVersion("1.2.3");
		Date date = new Date(1234567890123l);
		doc.setCreationDate(date);
		assertEquals("Rick-Rainer Ludwig", doc.getAuthor());
		assertEquals("1.2.3", doc.getVersion());
		assertSame(date, doc.getCreationDate());
	}

	@Test
	public void testAddChild() {
		Document doc = new Document("Test Document");
		doc.setAuthor("Rick-Rainer Ludwig");
		doc.setVersion("1.2.3");
		Date date = new Date(1234567890123l);
		doc.setCreationDate(date);
		new Part(doc, "test_part", "Test Part");
		assertEquals("Rick-Rainer Ludwig", doc.getAuthor());
		assertEquals("1.2.3", doc.getVersion());
		assertSame(date, doc.getCreationDate());
		assertNull(doc.getParent());
		assertTrue(doc.hasChildren());
		assertEquals(1, doc.getChildren().size());
	}
}
