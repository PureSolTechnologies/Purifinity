package com.puresoltechnologies.purifinity.server.database.hbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * This class provides a simple cache for prepared statements for HBase
 * connections via Phoenix.
 * 
 * @author Rick-Rainer Ludwig
 */
public class HBasePreparedStatements {

    private ThreadLocal<Map<Connection, Map<String, PreparedStatement>>> connections = ThreadLocal
	    .withInitial(() -> new WeakHashMap<>());

    /**
     * This method is used to check and return or create prepared statemens for
     * HBase/Phoenix.
     * 
     * @param connection
     *            is the opened connection to be used for connection.
     * @param sqlQuery
     *            is the SQL code to be used to create the prepared statement.
     * @return A {@link PreparedStatement} is returned.
     * @throws SQLException
     *             is thrown in case of illegal SQL.
     */
    public PreparedStatement getPreparedStatement(Connection connection, String sqlQuery) throws SQLException {
	Map<String, PreparedStatement> preparedStatements = connections.get().get(connection);
	if (preparedStatements == null) {
	    preparedStatements = new HashMap<>();
	    connections.get().put(connection, preparedStatements);
	}
	PreparedStatement preparedStatement = preparedStatements.get(sqlQuery);
	if (preparedStatement == null) {
	    preparedStatement = connection.prepareStatement(sqlQuery);
	    preparedStatements.put(sqlQuery, preparedStatement);
	}
	return preparedStatement;
	// return connection.prepareStatement(sqlQuery);
    }

}
