package com.puresol.document.convert.html;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.puresol.document.Chapter;
import com.puresol.document.Chart;
import com.puresol.document.Document;
import com.puresol.document.DocumentPart;
import com.puresol.document.Paragraph;
import com.puresol.document.Part;
import com.puresol.document.Section;
import com.puresol.document.SubSection;
import com.puresol.document.SubSubSection;
import com.puresol.document.Table;

public class HTMLConverter {

	private final Document document;
	private final StringBuffer buffer = new StringBuffer();
	private final File imageFolder;

	public HTMLConverter(Document document, File imageFolder) {
		super();
		this.document = document;
		this.imageFolder = imageFolder;
	}

	public Document getDocument() {
		return document;
	}

	public File getImageFolder() {
		return imageFolder;
	}

	public String toHTML(boolean htmlFrame) throws IOException {
		buffer.append(HTMLDocument.convert(this, document, htmlFrame));
		return buffer.toString();
	}

	public void convertChildren(StringBuffer buffer, List<DocumentPart> children)
			throws IOException {
		for (DocumentPart child : children) {
			if (child instanceof Part) {
				buffer.append(HTMLPart.convert(this, (Part) child));
			} else if (child instanceof Chapter) {
				buffer.append(HTMLChapter.convert(this, (Chapter) child));
			} else if (child instanceof Section) {
				buffer.append(HTMLSection.convert(this, (Section) child));
			} else if (child instanceof SubSection) {
				buffer.append(HTMLSubSection.convert(this, (SubSection) child));
			} else if (child instanceof SubSubSection) {
				buffer.append(HTMLSubSubSection.convert(this,
						(SubSubSection) child));
			} else if (child instanceof Paragraph) {
				buffer.append(HTMLParagraph.convert((Paragraph) child));
			} else if (child instanceof Table) {
				buffer.append(HTMLTable.convert((Table) child));
			} else if (child instanceof Chart) {
				buffer.append(HTMLChart.convert(this, (Chart) child));
			} else {
				throw new RuntimeException("Document part of type "
						+ child.getClass() + " is unknown in "
						+ HTMLConverter.class + "!");
			}
		}
	}
}
