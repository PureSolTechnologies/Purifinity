package com.puresol.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import com.puresol.database.postgresql.utils.PostgreSQLHelper;
import com.puresol.database.test.DatabaseHelper;

public class PasswordStoreDatabaseTestHelper {

	/**
	 * This method creates a new connection to the password store database.
	 * 
	 * @return A connection is returned.
	 * @throws SQLException
	 *             is thrown in cases of SQL errors.
	 */
	public static Connection createConnection() throws SQLException {
		Connection connection = PostgreSQLHelper.createConnection(
				"jdbc:postgresql:passwordstore", "liquibase", "liquibase");
		assertNotNull("Could not open database connection!", connection);
		return connection;
	}

	public static final void cleanup() throws SQLException, IOException {
		Connection connection = createConnection();
		try {
			URL passwordStoreCleanUpScript = PasswordStoreDatabaseTestHelper.class
					.getResource("/sql/PasswordStoreCleanup.sql");
			assertNotNull("Could not find PasswordStore clean up script!",
					passwordStoreCleanUpScript);
			DatabaseHelper.executeSQL(connection, passwordStoreCleanUpScript);
		} finally {
			connection.close();
		}
	}
}
