package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubSubSectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new SubSubSection(null, "Test Subsubsection"));
	}

	@Test
	public void testInitialValues() {
		SubSubSection doc = new SubSubSection(null, "Test Subsubsection");
		assertEquals("Test Subsubsection", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
