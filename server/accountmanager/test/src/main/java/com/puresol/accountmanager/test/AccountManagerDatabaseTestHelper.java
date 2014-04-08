package com.puresol.accountmanager.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import com.puresol.database.postgresql.utils.PostgreSQLHelper;
import com.puresol.database.test.DatabaseHelper;

public class AccountManagerDatabaseTestHelper {

	/**
	 * This method creates a new connection to the password store database.
	 * 
	 * @return A connection is returned.
	 * @throws SQLException
	 *             is thrown in cases of SQL errors.
	 */
	public static Connection createConnection() throws SQLException {
		Connection connection = PostgreSQLHelper.createConnection(
				"jdbc:postgresql:accountmanager", "liquibase", "liquibase");
		assertNotNull("Could not open database connection!", connection);
		return connection;
	}

	public static final void cleanup() throws SQLException, IOException {
		Connection connection = createConnection();
		try {
			URL accountManagerCleanUpScript = AccountManagerDatabaseTestHelper.class
					.getResource("/sql/AccountManagerCleanup.sql");
			assertNotNull("Could not find AccountManager clean up script!",
					accountManagerCleanUpScript);
			DatabaseHelper.executeSQL(connection, accountManagerCleanUpScript);
		} finally {
			connection.close();
		}
	}
}
