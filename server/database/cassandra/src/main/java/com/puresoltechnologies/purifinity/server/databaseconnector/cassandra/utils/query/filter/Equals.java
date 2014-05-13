package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query.filter;

public class Equals<T> extends Condition<T> {

	public Equals(String column, T value) {
		super(column, "=", value);
	}
}
