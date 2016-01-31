package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;

@Singleton
public class PasswordStoreConnectionProducer {

    @Inject
    private Logger logger;

    @Produces
    @Singleton
    @PasswordStoreConnection
    public Connection getAnalysisSession() {
	try {
	    logger.info("Creating HBase PasswordStore Connection...");
	    Connection connection = HBaseHelper.connect();
	    logger.info("HBase PasswordStore Connection opened.");
	    return connection;
	} catch (SQLException e) {
	    throw new RuntimeException("Could not connect to HBase via Phoenix.", e);
	}
    }

    public void closeAnalysisKeyspaceSession(@Disposes @PasswordStoreConnection Connection connection) {
	try {
	    connection.close();
	} catch (SQLException e) {
	    logger.warn("Could not close connection to Phoenix/HBase.", e);
	}
    }

}
