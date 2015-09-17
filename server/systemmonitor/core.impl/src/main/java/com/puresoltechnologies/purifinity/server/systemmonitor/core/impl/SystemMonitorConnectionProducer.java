package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;

@Singleton
public class SystemMonitorConnectionProducer {

    @Inject
    private Logger logger;

    @Produces
    @SystemMonitor
    public Connection getAnalysisSession() {
	try {
	    logger.info("Creating HBase SystemMonitor Connection...");
	    Connection connection = HBaseHelper.connect();
	    logger.info("HBase SystemMonitor Connection opend.");
	    return connection;
	} catch (SQLException e) {
	    throw new RuntimeException("Could not connect to HBase via Phoenix.", e);
	}
    }

    public void closeAnalysisKeyspaceSession(@Disposes @SystemMonitor Connection connection) {
	try {
	    connection.close();
	} catch (SQLException e) {
	    logger.warn("Could not close connection to Phoenix/HBase.", e);
	}
    }

}
