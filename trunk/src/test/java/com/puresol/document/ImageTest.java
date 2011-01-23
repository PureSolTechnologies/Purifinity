package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImageTest {

	@Test
	public void testInstance() {
		assertNotNull(new Image(null, "Test Image", null));
	}

	@Test
	public void testInitialValues() {
		Image chart = new Image(null, "Test Image", null);
		assertEquals("Test Image", chart.getName());
		assertNull(chart.getParent());
		assertFalse(chart.hasChildren());
		assertEquals(0, chart.getChildren().size());
		assertEquals(null, chart.getImageRenderer());
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Image part = new Image(doc, "Test Image", null);
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}

	@Test
	public void testSetCaption() {
		Image part = new Image(null, "Test Image", null);
		part.setCaption("TestCaption");
		assertEquals("TestCaption", part.getCaption());
	}
}
