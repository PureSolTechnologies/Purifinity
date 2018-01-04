package com.puresoltechnologies.purifinity.server.database.cassandra.utils.query.filter;

public class And extends AbstractCassandraFilter {

	private final CassandraFilter[] filters;

	public And(CassandraFilter... filters) {
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
				builder.append(" AND ");
			}
			builder.append('(');
			builder.append(filter.getWhereClause());
			builder.append(')');
		}
		return builder.toString();
	}

}
