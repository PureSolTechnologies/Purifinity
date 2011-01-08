package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

import com.puresol.document.Chapter;
import com.puresol.document.Document;
import com.puresol.document.Paragraph;
import com.puresol.document.Part;
import com.puresol.document.Section;
import com.puresol.document.Subsection;
import com.puresol.document.Subsubsection;
import com.puresol.document.Table;

public class HTMLConverterTest {

	@Test
	public void testInstance() {
		assertNotNull(new HTMLConverter(new Document("Test Document")));
	}

	@Test
	public void testInitValues() {
		Document document = new Document("Test Document");
		HTMLConverter converter = new HTMLConverter(document);
		assertSame(document, converter.getDocument());
	}

	@Test
	public void testToString() {
		HTMLConverter converter = new HTMLConverter(new Document(
				"Test Document"));
		Pattern pattern = Pattern.compile("^<html>\\n" + "<body>\\n"
				+ "<h1>Test Document</h1>\\n" + "<hr/>\\n"
				+ "time of creation: ... ... .. ..:..:.. ... ....\\n"
				+ "</body>\\n" + "</html>\\n");
		assertTrue(pattern.matcher(converter.toString()).find());
	}

	@Test
	public void testComplexDocument() {
		Document document = new Document("Document");
		document.setAuthor("Rick-Rainer Ludwig");
		document.setVersion("1.2.3");
		Part part1 = new Part(document, "Part 1");
		Chapter chapter1 = new Chapter(part1, "Chapter 1.1");
		Section section1 = new Section(chapter1, "Section 1.1.1");
		Subsection subsection1 = new Subsection(section1, "Subsection 1.1.1.1");
		Subsubsection subsubsection1 = new Subsubsection(subsection1,
				"Subsection 1.1.1.1");
		new Paragraph(subsubsection1, "This is a sample paragraph...");
		Table table = new Table(subsubsection1, "Table 1", "Column1", "Column2");
		table.addRow("Cell 1,1", "Cell 1,2");
		table.addRow("Cell 2,1", "Cell 2,2");
		new Section(chapter1, "Section 1.1.2");
		new Chapter(part1, "Chapter 1.2");
		new Part(document, "Part 2");
		new Chapter(part1, "Chapter 2.1");
		HTMLConverter converter = new HTMLConverter(document);
		System.out.println(converter.toString());
		Pattern pattern = Pattern.compile("^<html>\\n" + "<body>\\n"
				+ "<h1>Document</h1>\\n" + "<b>Rick-Rainer Ludwig</b><br/>\\n"
				+ "<i>1.2.3</i><br/>\\n" + "<h1>Part 1</h1>\\n"
				+ "<h2>Chapter 1.1</h2>\\n" + "<h3>Section 1.1.1</h3>\\n"
				+ "<h4>Subsection 1.1.1.1</h4>\\n"
				+ "<h5>Subsection 1.1.1.1</h5>\\n"
				+ "<p>This is a sample paragraph...</p>\\n" + "<table>\\n"
				+ "<tr>\\n" + "<th>Column1</th>\\n" + "<th>Column2</th>\\n"
				+ "</tr>\\n" + "<tr>\\n" + "<td>Cell 1,1</td>\\n"
				+ "<td>Cell 1,2</td>\\n" + "</tr>\\n" + "<tr>\\n"
				+ "<td>Cell 2,1</td>\\n" + "<td>Cell 2,2</td>\\n" + "</tr>\\n"
				+ "</table>\\n" + "<h3>Section 1.1.2</h3>\\n"
				+ "<h2>Chapter 1.2</h2>\\n" + "<h2>Chapter 2.1</h2>\\n"
				+ "<h1>Part 2</h1>\\n" + "<hr/>\\n"
				+ "time of creation: ... ... .. ..:..:.. ... ....\\n"
				+ "</body>\\n" + "</html>\\n");
		assertTrue(pattern.matcher(converter.toString()).find());
	}
}
