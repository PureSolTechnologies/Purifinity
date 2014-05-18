package com.puresoltechnologies.purifinity.server.database.titan;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;

public class TitanGraphHelper {

	private static final Object CASSANDRA_HOST = "localhost";

	public static TitanGraph connect() {
		Configuration conf = getConfigurationForCassandraBackend();
		return TitanFactory.open(conf);
	}

	private static Configuration getConfigurationForCassandraBackend() {
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.backend", "cassandra");
		conf.setProperty("storage.hostname", CASSANDRA_HOST);
		return conf;
	}

}
