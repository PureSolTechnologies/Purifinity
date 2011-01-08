package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Subsubsection;

public class HTMLSubsubsectionTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLSubsubsection.convert(new Subsubsection(null,
				"Test Subsubsection"));
		assertEquals("<h5>Test Subsubsection</h5>\n", buffer.toString());
	}
}
