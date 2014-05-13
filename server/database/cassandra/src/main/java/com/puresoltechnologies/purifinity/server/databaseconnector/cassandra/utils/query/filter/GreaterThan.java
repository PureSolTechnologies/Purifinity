package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query.filter;

public class GreaterThan<T> extends Condition<T> {

	public GreaterThan(String column, T value) {
		super(column, ">", value);
	}
}
