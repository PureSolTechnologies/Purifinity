package com.puresol.document.convert.gui;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import com.puresol.document.SubSubSection;

public class GUISubSubSectionTest {

	@Test
	public void testConvert() {
		JPanel chapter = GUISubSubSection.convert(new SubSubSection(null,
				"test_subsubsection", "Test SubSubSection"));
		assertNotNull(chapter);
		assertTrue(chapter.getComponents().length > 0);
	}
}
