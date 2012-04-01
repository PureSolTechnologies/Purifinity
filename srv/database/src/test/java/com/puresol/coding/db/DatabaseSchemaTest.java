package com.puresol.coding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class DatabaseSchemaTest {

    @Test
    public void testAnalysisStoreToFileStoreFK() throws Exception {
	Class.forName("org.postgresql.Driver");
	Connection connection = DriverManager.getConnection(
		"jdbc:postgresql://localhost/CODEANALYSIS", "liquibase",
		"liquibase");
	try {
	    Statement statement = connection.createStatement();
	    try {
		// statement
		// .execute("INSERT INTO AnalysisStore (Key, Language, Time, RunTime, ParserTree) VALUES (1,'Java', '2012-01-02 12:00:01', 1234, 'ParserTree' )");
		// TODO add analysis without file in file store and expect
		// exception!
	    } finally {
		statement.close();
	    }
	} finally {
	    connection.close();
	}
    }
}
