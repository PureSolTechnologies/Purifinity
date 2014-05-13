package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query.filter;

public class Or<T> extends AbstractCassandraFilter {

	private final CassandraFilter[] filters;

	public Or(CassandraFilter... filters) {
		super();
		this.filters = filters;
	}

	public CassandraFilter[] getFilters() {
		return filters;
	}

	@Override
	public String getWhereClause() {
		StringBuilder builder = new StringBuilder();
		for (CassandraFilter filter : filters) {
			if (builder.length() > 0) {
				builder.append(" OR ");
			}
			builder.append('(');
			builder.append(filter.getWhereClause());
			builder.append(')');
		}
		return builder.toString();
	}

}
