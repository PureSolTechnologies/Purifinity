package com.puresoltechnologies.purifinity.server.ui;

import java.io.Serializable;

/**
 * This is a simple column model for Primefaces data table to get dynamic column
 * numbers working.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ColumnModel implements Serializable {

	private static final long serialVersionUID = -9117697277407122061L;

	private final String columnName;
	private final String value;

	public ColumnModel(String columnName, String value) {
		super();
		this.columnName = columnName;
		this.value = value;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getValue() {
		return value;
	}

}
