package com.puresol.document.convert.html;

import com.puresol.document.Section;

public class HTMLSection {

	public static StringBuffer convert(Section section) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h3>" + section.getName() + "</h3>\n");
		HTMLConverter.convertChildren(buffer, section.getChildren());
		return buffer;
	}

}
