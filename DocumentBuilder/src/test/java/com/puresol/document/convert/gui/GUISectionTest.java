package com.puresol.document.convert.gui;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import com.puresol.document.Section;

public class GUISectionTest {

	@Test
	public void testConvert() {
		JPanel chapter = GUISection.convert(new Section(null, "test_section",
				"Test Section"));
		assertNotNull(chapter);
		assertTrue(chapter.getComponents().length > 0);
	}
}
