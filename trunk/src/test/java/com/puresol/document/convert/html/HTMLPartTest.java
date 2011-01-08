package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Part;

public class HTMLPartTest {

	@Test
	public void testToString() {
		StringBuffer buffer = HTMLPart.convert(new Part(null, "Test Part"));
		assertEquals("<h1>Test Part</h1>\n", buffer.toString());
	}
}
