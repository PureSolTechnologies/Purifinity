package com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.filter;

import java.util.List;

public class In<T> extends AbstractCassandraFilter {

	private final String column;
	private final List<T> values;

	public In(String column, String sign, List<T> values) {
		super();
		this.column = column;
		this.values = values;
	}

	public String getColumn() {
		return column;
	}

	public List<T> getValues() {
		return values;
	}

	@Override
	public String getWhereClause() {
		StringBuilder builder = new StringBuilder();
		builder.append(getColumn());
		builder.append(" IN (");
		boolean isString = false;
		boolean first = true;
		for (T value : values) {
			if (first) {
				isString = String.class.equals(value.getClass());
				first = false;
			} else {
				builder.append(", ");
			}
			if (isString) {
				builder.append('\'');
				builder.append(value);
				builder.append('\'');
			} else {
				builder.append(value);
			}
		}
		builder.append(')');
		return builder.toString();
	}

}
