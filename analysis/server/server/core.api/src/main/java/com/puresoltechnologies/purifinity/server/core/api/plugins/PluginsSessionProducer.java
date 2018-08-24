package com.puresoltechnologies.purifinity.server.core.api.plugins;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.datastax.driver.core.Session;

@Singleton
public class PluginsSessionProducer {

    @Inject
    private Logger logger;

    @Inject
    private DuctileDB cluster;

    @Produces
    @Singleton
    @PluginsKeyspace
    public Session getPluginsSession() {
	logger.info("Creating Cassandra Plugins Session...");
	Session session = cluster.connect(PluginsKeyspace.NAME);
	logger.info("Cassandra Plugins Session created...");
	return session;
    }

    public void closePluginsKeyspaceSession(@Disposes @PluginsKeyspace Session session) {
	session.close();
    }

}
