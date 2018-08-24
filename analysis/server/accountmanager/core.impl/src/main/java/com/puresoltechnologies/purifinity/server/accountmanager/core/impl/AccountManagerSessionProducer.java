package com.puresoltechnologies.purifinity.server.accountmanager.core.impl;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Session;

@Singleton
public class AccountManagerSessionProducer {

    @Inject
    private Logger logger;

    @Produces
    @Singleton
    @AccountManagerKeyspace
    public Session getAnalysisSession() {
	logger.info("Creating Cassandra PasswordStore Session...");
	Session session = cluster.connect(AccountManagerKeyspace.NAME);
	logger.info("Cassandra PasswordStore Session created.");
	return session;
    }

    public void closeAnalysisKeyspaceSession(@Disposes @AccountManagerKeyspace Session session) {
	session.close();
    }

}
