package com.puresoltechnologies.purifinity.server.database.neo4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.thinkaurelius.titan.core.TitanGraph;

@Singleton
public class Neo4jProducer {

    @Inject
    private Logger logger;

    private TitanGraph graph;

    @PostConstruct
    private void initialize() {
	logger.info("Connect to Titan...");
	graph = Neo4jHelper.connect();
	logger.info("Titan connected.");
    }

    @PreDestroy
    private void destroy() {
	logger.info("Shutting down Titan...");
	graph.shutdown();
	logger.info("Titan shut down.");
    }

    @Produces
    public TitanGraph getCluster() {
	return graph;
    }
}
