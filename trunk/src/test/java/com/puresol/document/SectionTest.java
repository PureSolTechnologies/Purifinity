package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new Section(null, "Test Section"));
	}

	@Test
	public void testInitialValues() {
		Section doc = new Section(null, "Test Section");
		assertEquals("Test Section", doc.getName());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
