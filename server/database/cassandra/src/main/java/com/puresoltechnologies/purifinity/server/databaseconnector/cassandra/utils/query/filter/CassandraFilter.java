package com.puresoltechnologies.purifinity.server.databaseconnector.cassandra.utils.query.filter;

/**
 * This interface represents a filter for Cassandra querys.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CassandraFilter {

	/**
	 * This method returns a clause for Cassandra (without the WHERE keyword!).
	 * 
	 * @return A {@link String} is returned containing the WHERE clause.
	 */
	public String getWhereClause();

}
