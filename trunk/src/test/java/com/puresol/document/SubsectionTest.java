package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubsectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new Subsection(null, "Test Subsection"));
	}

	@Test
	public void testInitialValues() {
		Subsection doc = new Subsection(null, "Test Subsection");
		assertEquals("Test Subsection", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
