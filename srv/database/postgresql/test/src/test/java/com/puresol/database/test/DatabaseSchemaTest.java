package com.puresol.database.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import com.puresol.database.utils.LiquibaseHelper;

public class DatabaseSchemaTest {

    @BeforeClass
    public static void initialize() throws Exception {
	Class.forName("org.postgresql.Driver");
    }

    private Connection connection;

    @Before
    public void setup() throws Exception {
	LiquibaseHelper.dropAndUpdate();
	connection = DriverManager.getConnection(
		"jdbc:postgresql://localhost/CODEANALYSIS", "liquibase",
		"liquibase");
    }

    @After
    public void tearDown() throws Exception {
	if (connection != null) {
	    connection.close();
	}
    }

    @Test(expected = PSQLException.class)
    public void testAnalysisStoreToFileStoreFK() throws Exception {
	try {
	    Statement statement = connection.createStatement();
	    try {
		statement
			.execute("INSERT INTO \"AnalysisStore\" (\"ParserTreeKey\") VALUES ('1234')");
		connection.commit();
	    } finally {
		statement.close();
	    }
	} finally {
	    connection.close();
	}
    }

    @Test
    public void testAnalysisStoreToFileStoreFK2() throws Exception {
	try {
	    Statement statement = connection.createStatement();
	    try {
		statement
			.execute("INSERT INTO \"FileStore\" (\"FileKey\") VALUES ('123')");
		statement
			.execute("INSERT INTO \"AnalysisStore\" (\"ParserTreeKey\") VALUES ('123')");
		connection.commit();
	    } finally {
		statement.close();
	    }
	} finally {
	    connection.close();
	}
    }
}
