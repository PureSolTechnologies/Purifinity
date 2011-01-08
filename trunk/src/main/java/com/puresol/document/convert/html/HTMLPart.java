package com.puresol.document.convert.html;

import com.puresol.document.Part;

public class HTMLPart {

	public static StringBuffer convert(Part part) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h1>" + part.getName() + "</h1>\n");
		HTMLConverter.convertChildren(buffer, part.getChildren());
		return buffer;
	}

}
