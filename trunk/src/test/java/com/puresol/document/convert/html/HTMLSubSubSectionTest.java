package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.SubSubSection;

public class HTMLSubSubSectionTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLSubSubSection.convert(new SubSubSection(null,
				"Test Subsubsection"));
		assertEquals("<h5>Test Subsubsection</h5>\n", buffer.toString());
	}
}
