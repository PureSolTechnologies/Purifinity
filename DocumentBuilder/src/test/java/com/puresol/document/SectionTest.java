package com.puresol.document;

import static org.junit.Assert.*;

import org.junit.Test;

public class SectionTest {

	@Test
	public void testInstance() {
		assertNotNull(new Section(null, "test_section", "Test Section"));
	}

	@Test
	public void testInitialValues() {
		Section doc = new Section(null, "test_section", "Test Section");
		assertEquals("test_section", doc.getName());
		assertEquals("Test Section", doc.getTitle());
		assertNull(doc.getParent());
		assertFalse(doc.hasChildren());
		assertEquals(0, doc.getChildren().size());
	}

}
