package com.puresol.document.convert.html;

import com.puresol.document.Paragraph;

public class HTMLParagraph {

	public static StringBuffer convert(Paragraph paragraph) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<p>" + paragraph.getName() + "</p>\n");
		if (paragraph.getChildren().size() > 0) {
			throw new RuntimeException(
					"No children are allowed in a paragraph!");
		}
		return buffer;
	}

}
