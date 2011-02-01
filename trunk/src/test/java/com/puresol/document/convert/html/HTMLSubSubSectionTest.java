package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.document.SubSubSection;

public class HTMLSubSubSectionTest {

	@Test
	public void testToString() throws IOException {
		StringBuffer buffer = HTMLSubSubSection.convert(new HTMLConverter(null,
				new File("")), new SubSubSection(null, "Test Subsubsection"));
		assertEquals("<h5>Test Subsubsection</h5>\n", buffer.toString());
	}
}
