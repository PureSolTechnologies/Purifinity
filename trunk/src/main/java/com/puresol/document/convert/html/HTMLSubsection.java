package com.puresol.document.convert.html;

import com.puresol.document.Subsection;

public class HTMLSubsection {

	public static StringBuffer convert(Subsection subsection) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h4>" + subsection.getName() + "</h4>\n");
		HTMLConverter.convertChildren(buffer, subsection.getChildren());
		return buffer;
	}

}
