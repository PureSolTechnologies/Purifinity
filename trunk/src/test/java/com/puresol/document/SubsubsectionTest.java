package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubsubsectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new Subsubsection(null, "Test Subsubsection"));
	}

	@Test
	public void testInitialValues() {
		Subsubsection doc = new Subsubsection(null, "Test Subsubsection");
		assertEquals("Test Subsubsection", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
