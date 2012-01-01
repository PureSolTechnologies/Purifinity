package com.puresol.document.convert.html;

import java.io.IOException;

import com.puresol.document.SubSection;

public class HTMLSubSection {

	public static StringBuffer convert(HTMLConverter converter,
			SubSection subsection) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h4>" + subsection.getTitle() + "</h4>\n");
		converter.convertChildren(buffer, subsection.getChildren());
		return buffer;
	}

}
