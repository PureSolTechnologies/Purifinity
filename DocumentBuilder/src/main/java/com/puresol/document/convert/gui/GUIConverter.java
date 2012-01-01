package com.puresol.document.convert.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.puresol.document.Chapter;
import com.puresol.document.Chart;
import com.puresol.document.Document;
import com.puresol.document.DocumentPart;
import com.puresol.document.Image;
import com.puresol.document.Paragraph;
import com.puresol.document.Part;
import com.puresol.document.Section;
import com.puresol.document.SubSection;
import com.puresol.document.SubSubSection;
import com.puresol.document.Table;

public class GUIConverter {

	public static List<JPanel> convertChildren(List<DocumentPart> children) {
		List<JPanel> panels = new ArrayList<JPanel>();
		for (DocumentPart child : children) {
			if (child instanceof Part) {
				panels.add(GUIPart.convert((Part) child));
			} else if (child instanceof Chapter) {
				panels.add(GUIChapter.convert((Chapter) child));
			} else if (child instanceof Section) {
				panels.add(GUISection.convert((Section) child));
			} else if (child instanceof SubSection) {
				panels.add(GUISubSection.convert((SubSection) child));
			} else if (child instanceof SubSubSection) {
				panels.add(GUISubSubSection.convert((SubSubSection) child));
			} else if (child instanceof Paragraph) {
				panels.add(GUIParagraph.convert((Paragraph) child));
			} else if (child instanceof Table) {
				panels.add(GUITable.convert((Table) child));
			} else if (child instanceof Chart) {
				panels.add(GUIChart.convert((Chart) child));
			} else if (child instanceof Image) {
				panels.add(GUIImage.convert((Image) child));
			} else {
				throw new RuntimeException("Document part of type "
						+ child.getClass() + " is unknown in "
						+ GUIConverter.class + "!");
			}
		}
		return panels;
	}

	private final Document document;

	public GUIConverter(Document document) {
		super();
		this.document = document;
	}

	public Document getDocument() {
		return document;
	}

	public JPanel toPanel() {
		return GUIDocument.convert(document);
	}
}
