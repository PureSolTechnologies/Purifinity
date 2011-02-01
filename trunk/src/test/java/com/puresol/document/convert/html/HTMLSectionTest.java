package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.document.Section;

public class HTMLSectionTest {

	@Test
	public void testToString() throws IOException {
		StringBuffer buffer = HTMLSection.convert(new HTMLConverter(null,
				new File("")), new Section(null, "Test Section"));
		assertEquals("<h3>Test Section</h3>\n", buffer.toString());
	}
}
