package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Chapter;

public class HTMLChapterTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLChapter.convert(new Chapter(null,
				"Test Chapter"));
		assertEquals("<h2>Test Chapter</h2>\n", buffer.toString());
	}
}
