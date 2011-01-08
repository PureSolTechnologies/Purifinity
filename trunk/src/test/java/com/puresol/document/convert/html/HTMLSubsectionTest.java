package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Subsection;

public class HTMLSubsectionTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLSubsection.convert(new Subsection(null,
				"Test Subsection"));
		assertEquals("<h4>Test Subsection</h4>\n", buffer.toString());
	}
}
