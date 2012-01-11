package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubSectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new SubSection(null, "test_subsection", "Test Subsection"));
	}

	@Test
	public void testInitialValues() {
		SubSection doc = new SubSection(null, "test_subsection",
				"Test Subsection");
		assertEquals("test_subsection", doc.getName());
		assertEquals("Test Subsection", doc.getTitle());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
