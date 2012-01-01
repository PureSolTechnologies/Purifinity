package com.puresol.document.convert.html;

import java.io.IOException;

import com.puresol.document.Document;

public class HTMLDocument {

	public static StringBuffer convert(HTMLConverter converter,
			Document document, boolean htmlFrame) throws IOException {
		StringBuffer buffer = new StringBuffer();
		if (htmlFrame) {
			buffer.append("<html>\n");
			buffer.append("<body>\n");
		}
		buffer.append("<h1>" + document.getName() + "</h1>\n");
		if (!document.getAuthor().isEmpty()) {
			buffer.append("<b>" + document.getAuthor() + "</b><br/>\n");
		}
		if (!document.getVersion().isEmpty()) {
			buffer.append("<i>" + document.getVersion() + "</i><br/>\n");
		}
		converter.convertChildren(buffer, document.getChildren());
		buffer.append("<hr/>\n");
		buffer.append("time of creation: " + document.getCreationDate() + "\n");
		if (htmlFrame) {
			buffer.append("</body>\n");
			buffer.append("</html>\n");
		}
		return buffer;
	}

}
