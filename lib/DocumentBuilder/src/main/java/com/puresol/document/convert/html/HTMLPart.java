package com.puresol.document.convert.html;

import java.io.IOException;

import com.puresol.document.Part;

public class HTMLPart {

	public static StringBuffer convert(HTMLConverter converter, Part part)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h1>" + part.getTitle() + "</h1>\n");
		converter.convertChildren(buffer, part.getChildren());
		return buffer;
	}

}
