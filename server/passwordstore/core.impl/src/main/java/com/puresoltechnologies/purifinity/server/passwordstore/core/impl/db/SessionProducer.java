package com.puresoltechnologies.purifinity.server.passwordstore.core.impl.db;

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
    @PasswordStoreKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra PasswordStore Session...");
	Session session = cluster.connect(PasswordStoreKeyspace.NAME);
	logger.info("Cassandra PasswordStore Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(
	    @Disposes @PasswordStoreKeyspace Session session) {
	session.close();
    }
}
