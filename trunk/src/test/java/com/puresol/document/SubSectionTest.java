package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubSectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new SubSection(null, "Test Subsection"));
	}

	@Test
	public void testInitialValues() {
		SubSection doc = new SubSection(null, "Test Subsection");
		assertEquals("Test Subsection", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
