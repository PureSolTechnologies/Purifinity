package com.puresoltechnologies.purifinity.framework.store.db;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;

/**
 * Manages the actual connection to Titan. The methods connect and disconnect
 * need to be called by a lifecycle component like the OSGi container of JavaEE
 * container to establish the actual connection.
 * 
 * @author Rick-Rainer Ludwig
 */
public class TitanConnection {

	private static TitanGraph graph = null;

	public static void connect() throws TitanConnectionException {
		if (graph != null) {
			throw new TitanConnectionException(
					"Titan graph database was already connected.");
		}
		Configuration conf = getConfigurationForCassandraBackend();
		graph = TitanFactory.open(conf);
	}

	private static Configuration getConfigurationForCassandraBackend() {
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.backend", "cassandra");
		conf.setProperty("storage.hostname", CassandraConnection.getHost());
		return conf;
	}

	public static void disconnect() throws TitanConnectionException {
		if (graph == null) {
			throw new TitanConnectionException(
					"Titan graph database has not been connected, yet.");
		}
		graph.shutdown();
		graph = null;
	}

	public static boolean isConnected() {
		return graph != null;
	}

	public static TitanGraph getGraph() {
		return graph;
	}
}
