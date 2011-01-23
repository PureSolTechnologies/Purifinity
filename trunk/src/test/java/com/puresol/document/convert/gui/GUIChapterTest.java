package com.puresol.document.convert.gui;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import com.puresol.document.Chapter;

public class GUIChapterTest {

	@Test
	public void testConvert() {
		JPanel chapter = GUIChapter.convert(new Chapter(null, "Test Chapter"));
		assertNotNull(chapter);
		assertTrue(chapter.getComponents().length > 0);
	}
}
