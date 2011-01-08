package com.puresol.document.convert.html;

import com.puresol.document.Subsubsection;

public class HTMLSubsubsection {

	public static StringBuffer convert(Subsubsection subsubsection) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h5>" + subsubsection.getName() + "</h5>\n");
		HTMLConverter.convertChildren(buffer, subsubsection.getChildren());
		return buffer;
	}

}
