package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query.filter.CassandraFilter;

public class CassandraQuery {

	private final Session session;

	public CassandraQuery(Session session) {
		super();
		this.session = session;
	}

	public ResultSet query(String tableName, CassandraFilter filter,
			String... columns) {
		return query(tableName, filter, false, columns);
	}

	public ResultSet query(String tableName, CassandraFilter filter,
			boolean allowFiltering, String... columns) {
		StringBuilder builder = new StringBuilder("SELECT ");
		boolean first = true;
		for (String column : columns) {
			if (first) {
				first = false;
			} else {
				builder.append(", ");
			}
			builder.append(column);
		}
		builder.append(" FROM ");
		builder.append(tableName);
		if (filter != null) {
			builder.append(" WHERE ");
			builder.append(filter.getWhereClause());
		}
		if (allowFiltering) {
			builder.append(" ALLOW FILTERING");
		}
		builder.append(';');
		ResultSet resultSet = session.execute(builder.toString());
		return resultSet;
	}
}
