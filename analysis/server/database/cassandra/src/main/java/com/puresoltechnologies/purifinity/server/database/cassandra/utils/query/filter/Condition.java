package com.puresoltechnologies.purifinity.server.database.cassandra.utils.query.filter;

public abstract class Condition<T> extends AbstractCassandraFilter {

	private final String column;
	private final String sign;
	private final T value;

	public Condition(String column, String sign, T value) {
		super();
		this.column = column;
		this.sign = sign;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public T getValue() {
		return value;
	}

	@Override
	public String getWhereClause() {
		StringBuilder builder = new StringBuilder();
		builder.append(getColumn());
		builder.append(sign);
		if (String.class.equals(getValue().getClass())) {
			builder.append("'");
			builder.append(getValue());
			builder.append("'");
		} else {
			builder.append(getValue());
		}
		return builder.toString();
	}

}
