package com.puresoltechnologies.purifinity.framework.database.cassandra.utils.query.filter;

public class GreaterThan<T> extends Condition<T> {

	public GreaterThan(String column, T value) {
		super(column, ">", value);
	}
}
