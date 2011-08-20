package com.puresol.document.convert.html;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Test;

import com.puresol.document.Document;

public class HTMLConverterTest {

	@Test
	public void testInstance() {
		assertNotNull(new HTMLConverter(new Document("Test Document"),
				new File("test")));
	}

	@Test
	public void testInitValues() {
		Document document = new Document("Test Document");
		HTMLConverter converter = new HTMLConverter(document, new File("test"));
		assertSame(document, converter.getDocument());
	}
}
