package com.puresoltechnologies.purifinity.server.systemmonitor.core.impl;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Singleton
public class SystemMonitorSessionProducer {

    @Inject
    private Logger logger;

    @Inject
    private Cluster cluster;

    @Produces
    @Singleton
    @SystemMonitorKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra SystemMonitor Session...");
	Session session = cluster.connect(SystemMonitorKeyspace.NAME);
	logger.info("Cassandra SystemMonitor Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @SystemMonitorKeyspace Session session) {
	session.close();
    }

}
