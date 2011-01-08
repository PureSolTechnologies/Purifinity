package com.puresol.document.convert.html;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.document.Table;

public class HTMLTableTest {

	@Test
	public void testToString() {
		Table table = new Table(null, "Test Table", "Col1", "Col2");
		table.addRow(1, 2);
		StringBuffer buffer = HTMLTable.convert(table);
		System.out.println(buffer);
		assertEquals("<table>\n" + "<tr>\n" + "<th>Col1</th>\n"
				+ "<th>Col2</th>\n" + "</tr>\n" + "<tr>\n" + "<td>1</td>\n"
				+ "<td>2</td>\n" + "</tr>\n" + "</table>\n", buffer.toString());
	}

	@Test(expected = RuntimeException.class)
	public void testPresentChildren() {
		Table table = new Table(null, "Test Paragraph", "1", "2");
		new Table(table, "Test Paragraph 2");
		HTMLTable.convert(table);
	}

}
