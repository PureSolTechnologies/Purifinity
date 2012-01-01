package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChartTest {

	@Test
	public void testInstance() {
		assertNotNull(new Chart(null, "TestChart", "Test Chart", null));
	}

	@Test
	public void testInitialValues() {
		Chart chart = new Chart(null, "TestChart", "Test Chart", null);
		assertEquals("TestChart", chart.getName());
		assertEquals("Test Chart", chart.getCaption());
		assertNull(chart.getParent());
		assertFalse(chart.hasChildren());
		assertEquals(0, chart.getChildren().size());
		assertEquals(null, chart.getChartRenderer());
	}

	@Test
	public void testAddToDocument() {
		Document doc = new Document("Test Document");
		Chart part = new Chart(doc, "TestChart", "Test Chart", null);
		assertSame(doc, part.getParent());
		assertFalse(part.hasChildren());
		assertEquals(0, part.getChildren().size());
	}
}
