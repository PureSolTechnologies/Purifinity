package com.puresoltechnologies.purifinity.framework.store.db;

import java.util.Date;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

/**
 * Manages the actual connection to Titan. The methods connect and disconnect
 * need to be called by a lifecycle component like the OSGi container of JavaEE
 * container to establish the actual connection.
 * 
 * @author Rick-Rainer Ludwig
 */
public class TitanConnection {

	private static TitanGraph graph = null;

	public static final String ANALYSIS_PROJECT_UUID_PROPERTY = "analysis.projects.uuid";
	public static final String ANALYSIS_PROJECT_NAME_PROPERTY = "analysis.projects.name";
	public static final String ANALYSIS_PROJECT_DESCRIPTION_PROPERTY = "analysis.projects.description";
	public static final String ANALYSIS_RUN_UUID_PROPERTY = "analysis.run.uuid";
	public static final String CREATION_TIME_PROPERTY = "time.creation";

	public static void connect() throws TitanConnectionException {
		if (graph != null) {
			throw new TitanConnectionException(
					"Titan graph database was already connected.");
		}
		Configuration conf = getConfigurationForCassandraBackend();
		graph = TitanFactory.open(conf);
		checkLabelAndKeySettings();
	}

	private static void checkLabelAndKeySettings() {
		if (graph.getType(CREATION_TIME_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(CREATION_TIME_PROPERTY);
			keyMaker.dataType(Date.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_PROJECT_UUID_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_PROJECT_UUID_PROPERTY);
			/*
			 * UUID is not possible, because it does not have a default
			 * constructor. We use String here.
			 */
			keyMaker.dataType(String.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.unique();
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_PROJECT_NAME_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_PROJECT_NAME_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.unique();
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_PROJECT_DESCRIPTION_PROPERTY) == null) {
			KeyMaker keyMaker = graph
					.makeKey(ANALYSIS_PROJECT_DESCRIPTION_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_RUN_UUID_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_RUN_UUID_PROPERTY);
			/*
			 * UUID is not possible, because it does not have a default
			 * constructor. We use String here.
			 */
			keyMaker.dataType(String.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.unique();
			keyMaker.make();
		}
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
