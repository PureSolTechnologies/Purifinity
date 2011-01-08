package com.puresol.document.convert.html;

import com.puresol.document.Chapter;

public class HTMLChapter {

	public static StringBuffer convert(Chapter chapter) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h2>" + chapter.getName() + "</h2>\n");
		HTMLConverter.convertChildren(buffer, chapter.getChildren());
		return buffer;
	}

}
