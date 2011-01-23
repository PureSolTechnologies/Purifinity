package com.puresol.document.convert.gui;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import com.puresol.document.SubSection;

public class GUISubSectionTest {

	@Test
	public void testConvert() {
		JPanel chapter = GUISubSection.convert(new SubSection(null,
				"Test SubSection"));
		assertNotNull(chapter);
		assertTrue(chapter.getComponents().length > 0);
	}
}
