package com.puresol.document.convert.gui;

import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

import com.puresol.document.Document;

public class GUIConverterTest {

	@Test
	public void testInstance() {
		assertNotNull(new GUIConverter(new Document("Test Document")));
	}

	@Test
	public void testInitValues() {
		Document document = new Document("Test Document");
		GUIConverter converter = new GUIConverter(document);
		assertSame(document, converter.getDocument());
	}

	@Test
	public void testToString() {
		GUIConverter converter = new GUIConverter(new Document("Test Document"));
		JPanel panel = converter.toPanel();
		assertNotNull(panel);
	}

}
