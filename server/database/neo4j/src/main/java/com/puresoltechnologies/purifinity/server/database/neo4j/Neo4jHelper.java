package com.puresoltechnologies.purifinity.server.database.neo4j;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;

public class Neo4jHelper {

    private static final Object HBASE_HOST = "localhost";

    public static TitanGraph connect() {
	Configuration conf = getConfigurationForHBaseBackend();
	return TitanFactory.open(conf);
    }

    private static Configuration getConfigurationForHBaseBackend() {
	Configuration conf = new BaseConfiguration();
	conf.setProperty("storage.backend", "hbase");
	conf.setProperty("storage.hostname", HBASE_HOST);
	return conf;
    }

}
