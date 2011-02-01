package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.document.Chapter;

public class HTMLChapterTest {

	@Test
	public void testToString() throws IOException {
		StringBuffer buffer = HTMLChapter.convert(new HTMLConverter(null,
				new File("")), new Chapter(null, "Test Chapter"));
		assertEquals("<h2>Test Chapter</h2>\n", buffer.toString());
	}
}
