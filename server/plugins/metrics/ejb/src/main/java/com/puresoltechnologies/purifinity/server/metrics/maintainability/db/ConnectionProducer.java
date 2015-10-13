package com.puresoltechnologies.purifinity.server.metrics.maintainability.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;

@Singleton
public class ConnectionProducer {

    @Inject
    private Logger logger;

    @Produces
    @Singleton
    @MaintainabilityIndexEvaluatorStoreConnection
    public Connection getAnalysisSession() {
	try {
	    logger.info("Creating Cassandra McCabe Metric Evaluator Session...");
	    Connection connection = HBaseHelper.connect();
	    logger.info("Cassandra McCabe Metric Evaluator Session created.");
	    return connection;
	} catch (SQLException e) {
	    throw new RuntimeException("Could not connect to HBase via Phoenix.", e);
	}
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @MaintainabilityIndexEvaluatorStoreConnection Connection connection) {
	try {
	    connection.close();
	} catch (SQLException e) {
	    logger.warn("Could not close connection to Phoenix/HBase.", e);
	}
    }
}
