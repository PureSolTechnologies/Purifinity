package com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.filter;

public class GreaterOrEqual<T> extends Condition<T> {

	public GreaterOrEqual(String column, T value) {
		super(column, ">=", value);
	}
}
