package com.puresoltechnologies.purifinity.server.database.cassandra;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Singleton
public class SessionProducer {

    @Inject
    private Logger logger;

    @Inject
    private Cluster cluster;

    @Produces
    @Singleton
    @EvaluationStoreKeyspace
    public Session getEvaluationSession() {
	logger.info("Creating Cassandra Evaluation Session...");
	Session session = cluster.connect(EvaluationStoreKeyspace.NAME);
	logger.info("Cassandra Evaluation Session created...");
	return session;
    }

    public void closeEvaluationKeyspaceSession(@Disposes @EvaluationStoreKeyspace Session session) {
	session.close();
    }
}
