package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.SubSection;

public class HTMLSubSectionTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLSubSection.convert(new SubSection(null,
				"Test Subsection"));
		assertEquals("<h4>Test Subsection</h4>\n", buffer.toString());
	}
}
