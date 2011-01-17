package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChartTest {

	@Test
	public void testInstance() {
		assertNotNull(new Chart(null, "Test Chart", null));
	}

	@Test
	public void testInitialValues() {
		Chart chart = new Chart(null, "Test Chart", null);
		assertEquals("Test Chart", chart.getName());
		assertNull(chart.getParent());
		assertFalse(chart.hasChildren());
		assertEquals(0, chart.getChildren().size());
		assertEquals(null, chart.getChart());
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Chart part = new Chart(doc, "Test Chart", null);
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}

	@Test
	public void testSetCaption() {
		Chart part = new Chart(null, "Test Chart", null);
		part.setCaption("TestCaption");
		assertEquals("TestCaption", part.getCaption());
	}
}
