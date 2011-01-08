package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Paragraph;

public class HTMLParagraphTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLParagraph.convert(new Paragraph(null,
				"Test Paragraph"));
		assertEquals("<p>Test Paragraph</p>\n", buffer.toString());
	}

	@Test(expected = RuntimeException.class)
	public void testPresentChildren() {
		Paragraph paragraph = new Paragraph(null, "Test Paragraph");
		new Paragraph(paragraph, "Test Paragraph 2");
		HTMLParagraph.convert(paragraph);
	}
}
