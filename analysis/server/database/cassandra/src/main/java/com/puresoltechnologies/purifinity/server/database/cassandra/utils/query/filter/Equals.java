package com.puresoltechnologies.purifinity.server.database.cassandra.utils.query.filter;

public class Equals<T> extends Condition<T> {

	public Equals(String column, T value) {
		super(column, "=", value);
	}
}
