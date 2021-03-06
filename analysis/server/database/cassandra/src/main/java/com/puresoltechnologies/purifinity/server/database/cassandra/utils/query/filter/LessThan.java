package com.puresoltechnologies.purifinity.server.database.cassandra.utils.query.filter;

public class LessThan<T> extends Condition<T> {

	public LessThan(String column, T value) {
		super(column, "<", value);
	}
}
