package com.puresol.document.convert.html;

import java.util.List;

import com.puresol.document.Table;

public class HTMLTable {

	public static StringBuffer convert(Table table) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table>\n");

		buffer.append("<tr>\n");
		for (String colName : table.getColumnNames()) {
			buffer.append("<th>" + colName + "</th>\n");
		}
		buffer.append("</tr>\n");
		for (List<Object> row : table.getRows()) {
			buffer.append("<tr>\n");
			for (Object value : row) {
				buffer.append("<td>" + value + "</td>\n");
			}
			buffer.append("</tr>\n");
		}
		buffer.append("</table>\n");
		if (table.getChildren().size() > 0) {
			throw new RuntimeException("No children are allowed in a table!");
		}
		return buffer;
	}

}
