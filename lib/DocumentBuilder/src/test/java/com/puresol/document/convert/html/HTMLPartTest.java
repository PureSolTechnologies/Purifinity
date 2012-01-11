package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.document.Part;

public class HTMLPartTest {

	@Test
	public void testToString() throws IOException {
		StringBuffer buffer = HTMLPart.convert(new HTMLConverter(null,
				new File("")), new Part(null, "test_part", "Test Part"));
		assertEquals("<h1>Test Part</h1>\n", buffer.toString());
	}
}
