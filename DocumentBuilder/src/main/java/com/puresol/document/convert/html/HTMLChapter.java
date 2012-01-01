package com.puresol.document.convert.html;

import java.io.IOException;

import com.puresol.document.Chapter;

public class HTMLChapter {

	public static StringBuffer convert(HTMLConverter converter, Chapter chapter)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h2>" + chapter.getTitle() + "</h2>\n");
		converter.convertChildren(buffer, chapter.getChildren());
		return buffer;
	}

}
