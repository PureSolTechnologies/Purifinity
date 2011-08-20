package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubSubSectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new SubSubSection(null, "test_subsubsection",
				"Test Subsubsection"));
	}

	@Test
	public void testInitialValues() {
		SubSubSection doc = new SubSubSection(null, "test_subsubsection",
				"Test Subsubsection");
		assertEquals("test_subsubsection", doc.getName());
		assertEquals("Test Subsubsection", doc.getTitle());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
