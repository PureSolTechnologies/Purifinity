package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query.filter;

public abstract class AbstractCassandraFilter implements CassandraFilter {

	@Override
	public String toString() {
		return "Cassandra Criterion: " + getWhereClause();
	}

}
