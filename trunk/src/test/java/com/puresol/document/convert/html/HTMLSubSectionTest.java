package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.document.SubSection;

public class HTMLSubSectionTest {

	@Test
	public void testToString() throws IOException {
		StringBuffer buffer = HTMLSubSection.convert(new HTMLConverter(null,
				new File("")), new SubSection(null, "Test Subsection"));
		assertEquals("<h4>Test Subsection</h4>\n", buffer.toString());
	}
}
