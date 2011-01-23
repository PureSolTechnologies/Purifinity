package com.puresol.document.convert.html;

import com.puresol.document.SubSubSection;

public class HTMLSubSubSection {

	public static StringBuffer convert(SubSubSection subsubsection) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h5>" + subsubsection.getName() + "</h5>\n");
		HTMLConverter.convertChildren(buffer, subsubsection.getChildren());
		return buffer;
	}

}
