package com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.filter;

public class LessOrEqual<T> extends Condition<T> {

	public LessOrEqual(String column, T value) {
		super(column, "<=", value);
	}
}
