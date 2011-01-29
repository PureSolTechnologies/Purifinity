package com.puresol.document.convert.html;

import java.util.List;

import com.puresol.document.Chapter;
import com.puresol.document.Document;
import com.puresol.document.DocumentPart;
import com.puresol.document.Paragraph;
import com.puresol.document.Part;
import com.puresol.document.Section;
import com.puresol.document.SubSection;
import com.puresol.document.SubSubSection;
import com.puresol.document.Table;

public class HTMLConverter {

	public static void convertChildren(StringBuffer buffer,
			List<DocumentPart> children) {
		for (DocumentPart child : children) {
			if (child instanceof Part) {
				buffer.append(HTMLPart.convert((Part) child));
			} else if (child instanceof Chapter) {
				buffer.append(HTMLChapter.convert((Chapter) child));
			} else if (child instanceof Section) {
				buffer.append(HTMLSection.convert((Section) child));
			} else if (child instanceof SubSection) {
				buffer.append(HTMLSubSection.convert((SubSection) child));
			} else if (child instanceof SubSubSection) {
				buffer.append(HTMLSubSubSection.convert((SubSubSection) child));
			} else if (child instanceof Paragraph) {
				buffer.append(HTMLParagraph.convert((Paragraph) child));
			} else if (child instanceof Table) {
				buffer.append(HTMLTable.convert((Table) child));
			} else {
				throw new RuntimeException("Document part of type "
						+ child.getClass() + " is unknown in "
						+ HTMLConverter.class + "!");
			}
		}
	}

	private final Document document;
	private final StringBuffer buffer = new StringBuffer();

	public HTMLConverter(Document document) {
		super();
		this.document = document;
	}

	public Document getDocument() {
		return document;
	}

	public String toHTML(boolean htmlFrame) {
		buffer.append(HTMLDocument.convert(document, htmlFrame));
		return buffer.toString();
	}

}
