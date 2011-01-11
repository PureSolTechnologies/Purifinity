package com.puresol.document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This object keeps the information about a single table element within the
 * document.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Table extends AbstractDocumentPart {

	private static final long serialVersionUID = -3314964484840281581L;

	private final List<String> columns;

	private final List<List<Object>> rows = new ArrayList<List<Object>>();

	public Table(AbstractDocumentPart parent, String name, List<String> columns) {
		super(parent, name);
		this.columns = columns;
	}

	public Table(AbstractDocumentPart parent, String name, String... columns) {
		super(parent, name);
		this.columns = new ArrayList<String>();
		Collections.addAll(this.columns, columns);
	}

	public int getColumnNum() {
		return columns.size();
	}

	public List<String> getColumnNames() {
		return columns;
	}

	public void addRow(List<Object> row) {
		if (row.size() != columns.size()) {
			throw new IllegalStateException();
		}
		rows.add(row);
	}

	public void addRow(Object... values) {
		if (values.length != columns.size()) {
			throw new IllegalStateException();
		}
		List<Object> row = new ArrayList<Object>();
		Collections.addAll(row, values);
		rows.add(row);
	}

	public int getRowNum() {
		return rows.size();
	}

	public List<Object> getRow(int id) {
		return rows.get(id);
	}

	public List<List<Object>> getRows() {
		return rows;
	}
}
