package com.puresol.document.convert.html;

import com.puresol.document.SubSection;

public class HTMLSubSection {

	public static StringBuffer convert(SubSection subsection) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h4>" + subsection.getName() + "</h4>\n");
		HTMLConverter.convertChildren(buffer, subsection.getChildren());
		return buffer;
	}

}
