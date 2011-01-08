package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Section;

public class HTMLSectionTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLSection.convert(new Section(null,
				"Test Section"));
		assertEquals("<h3>Test Section</h3>\n", buffer.toString());
	}
}
