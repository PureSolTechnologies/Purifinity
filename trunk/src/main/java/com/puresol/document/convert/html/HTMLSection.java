package com.puresol.document.convert.html;

import java.io.IOException;

import com.puresol.document.Section;

public class HTMLSection {

	public static StringBuffer convert(HTMLConverter converter, Section section)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h3>" + section.getName() + "</h3>\n");
		converter.convertChildren(buffer, section.getChildren());
		return buffer;
	}

}
