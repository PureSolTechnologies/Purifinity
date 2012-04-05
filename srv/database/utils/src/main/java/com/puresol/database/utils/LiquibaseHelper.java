package com.puresol.database.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;

public class LiquibaseHelper {

    public static void dropAndUpdate() throws SQLException, LiquibaseException {
	Connection connection = createConnection();
	try {
	    Liquibase liquibase = createLiquibaseConnection(connection);
	    liquibase.validate();
	    liquibase.dropAll();
	    liquibase.update("");
	} finally {
	    connection.close();
	}
    }

    public static void drop() throws SQLException, LiquibaseException {
	Connection connection = createConnection();
	try {
	    Liquibase liquibase = createLiquibaseConnection(connection);
	    liquibase.dropAll();
	} finally {
	    connection.close();
	}
    }

    private static Liquibase createLiquibaseConnection(Connection connection)
	    throws DatabaseException, LiquibaseException {
	JdbcConnection jdbcConnection = new JdbcConnection(connection);
	Database database = DatabaseFactory.getInstance()
		.findCorrectDatabaseImplementation(jdbcConnection);

	Liquibase liquibase = new Liquibase(
		LiquibaseChangelogAccessor.LIQUIBASE_CHANGELOG_RESOURCE,
		new LiquibaseChangelogAccessor(), database);
	return liquibase;
    }

    private static Connection createConnection() throws SQLException {
	Connection connection = DriverManager.getConnection(
		"jdbc:postgresql://localhost/CODEANALYSIS", "liquibase",
		"liquibase");
	return connection;
    }
}
