package com.puresol.document.convert.html;

import java.io.IOException;

import com.puresol.document.SubSubSection;

public class HTMLSubSubSection {

	public static StringBuffer convert(HTMLConverter converter,
			SubSubSection subsubsection) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h5>" + subsubsection.getTitle() + "</h5>\n");
		converter.convertChildren(buffer, subsubsection.getChildren());
		return buffer;
	}

}
