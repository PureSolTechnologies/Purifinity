package com.puresol.document.convert.html;

import java.util.List;

import com.puresol.document.Chapter;
import com.puresol.document.Document;
import com.puresol.document.DocumentPart;
import com.puresol.document.Paragraph;
import com.puresol.document.Part;
import com.puresol.document.Section;
import com.puresol.document.Subsection;
import com.puresol.document.Subsubsection;
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
			} else if (child instanceof Subsection) {
				buffer.append(HTMLSubsection.convert((Subsection) child));
			} else if (child instanceof Subsubsection) {
				buffer.append(HTMLSubsubsection.convert((Subsubsection) child));
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

	@Override
	public String toString() {
		buffer.append(HTMLDocument.convert(document));
		return buffer.toString();
	}

}
