package com.puresoltechnologies.purifinity.framework.store.db;

import java.util.Date;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.LabelMaker;
import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TypeMaker.UniquenessConsistency;
import com.tinkerpop.blueprints.Edge;
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

	/*
	 * ANALYSIS PROJECTS
	 */
	public static final String ANALYSIS_PROJECT_UUID_PROPERTY = "analysis.project.uuid";
	public static final String ANALYSIS_PROJECT_NAME_PROPERTY = "analysis.project.name";
	public static final String ANALYSIS_PROJECT_DESCRIPTION_PROPERTY = "analysis.project.description";
	/*
	 * ANALYSIS RUNS
	 */
	public static final String ANALYSIS_RUN_UUID_PROPERTY = "analysis.run.uuid";
	public static final String ANALYSIS_RUN_START_TIME_PROPERTY = "analysis.run.time.start";
	public static final String ANALYSIS_RUN_DURATION_PROPERTY = "analysis.run.time.duration";
	public static final String ANALYSIS_RUN_DESCRIPTION_PROPERTY = "analysis.run.description";
	public static final String ANALYZED_FILE_TREE_LABEL = "analyzedFileTree";
	public static final String HAS_ANALYSIS_RUN_LABEL = "hasAnalysisRun";
	public static final String HAS_ANALYSIS_LABEL = "hasAnalysis";
	/*
	 * FILE TREE
	 */
	public static final String TREE_ELEMENT_HASH = "tree.element.hashid";
	public static final String TREE_ELEMENT_IS_FILE = "tree.element.isFile";
	public static final String TREE_ELEMENT_CONTAINS_FILES = "tree.element.contains.files";
	public static final String TREE_ELEMENT_CONTAINS_DIRECTORIES = "tree.element.contains.directories";
	public static final String TREE_ELEMENT_CONTAINS_FILES_RECURSIVE = "tree.element.contains.files.recursive";
	public static final String TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE = "tree.element.contains.directories.recursive";
	public static final String TREE_ELEMENT_SIZE = "tree.element.size";
	public static final String TREE_ELEMENT_SIZE_RECURSIVE = "tree.element.size.recursive";
	public static final String CONTAINS_FILE_LABEL = "containsFile";
	public static final String CONTAINS_DIRECTORY_LABEL = "containsDirectory";
	/*
	 * ANALYSIS
	 */
	public static final String ANALYSIS_NAME_PROPERTY = "analysis.name";
	public static final String ANALYSIS_VERSION_PROPERTY = "analysis.version";
	public static final String ANALYSIS_START_TIME_PROPERTY = "analysis.time.start";
	public static final String ANALYSIS_DURATION_PROPERTY = "analysis.time.duration";

	public static final String ANALYSIS_LANGUAGE_NAME_PROPERTY = "analysis.language.name";
	public static final String ANALYSIS_LANGUAGE_VERSION_PROPERTY = "analysis.language.version";
	public static final String ANALYSIS_SUCCESSFUL_PROPERTY = "analysis.successful";
	public static final String ANALYSIS_MESSAGE_PROPERTY = "analysis.message";
	/*
	 * GENERAL PURPOSE
	 */
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
			keyMaker.indexed(Edge.class);
			keyMaker.unique();
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_RUN_START_TIME_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_RUN_START_TIME_PROPERTY);
			keyMaker.dataType(Date.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.indexed(Edge.class);
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_RUN_DURATION_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_RUN_DURATION_PROPERTY);
			keyMaker.dataType(Long.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_RUN_DESCRIPTION_PROPERTY) == null) {
			KeyMaker keyMaker = graph
					.makeKey(ANALYSIS_RUN_DESCRIPTION_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}

		if (graph.getType(TREE_ELEMENT_IS_FILE) == null) {
			KeyMaker keyMaker = graph.makeKey(TREE_ELEMENT_IS_FILE);
			keyMaker.dataType(Boolean.class);
			keyMaker.make();
		}

		if (graph.getType(TREE_ELEMENT_CONTAINS_FILES) == null) {
			KeyMaker keyMaker = graph.makeKey(TREE_ELEMENT_CONTAINS_FILES);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}
		if (graph.getType(TREE_ELEMENT_CONTAINS_DIRECTORIES) == null) {
			KeyMaker keyMaker = graph
					.makeKey(TREE_ELEMENT_CONTAINS_DIRECTORIES);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}
		if (graph.getType(TREE_ELEMENT_CONTAINS_FILES_RECURSIVE) == null) {
			KeyMaker keyMaker = graph
					.makeKey(TREE_ELEMENT_CONTAINS_FILES_RECURSIVE);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}
		if (graph.getType(TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE) == null) {
			KeyMaker keyMaker = graph
					.makeKey(TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}
		if (graph.getType(TREE_ELEMENT_SIZE) == null) {
			KeyMaker keyMaker = graph.makeKey(TREE_ELEMENT_SIZE);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}
		if (graph.getType(TREE_ELEMENT_SIZE_RECURSIVE) == null) {
			KeyMaker keyMaker = graph.makeKey(TREE_ELEMENT_SIZE_RECURSIVE);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}

		if (graph.getType(TREE_ELEMENT_HASH) == null) {
			KeyMaker keyMaker = graph.makeKey(TREE_ELEMENT_HASH);
			keyMaker.dataType(String.class);
			keyMaker.indexed(Vertex.class);
			keyMaker.indexed(Edge.class);
			keyMaker.unique();
			keyMaker.make();
		}

		if (graph.getType(ANALYSIS_NAME_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_NAME_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_VERSION_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_VERSION_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_START_TIME_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_START_TIME_PROPERTY);
			keyMaker.dataType(Date.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_DURATION_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_DURATION_PROPERTY);
			keyMaker.dataType(Long.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_LANGUAGE_NAME_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_LANGUAGE_NAME_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_LANGUAGE_VERSION_PROPERTY) == null) {
			KeyMaker keyMaker = graph
					.makeKey(ANALYSIS_LANGUAGE_VERSION_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_SUCCESSFUL_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_SUCCESSFUL_PROPERTY);
			keyMaker.dataType(Boolean.class);
			keyMaker.make();
		}
		if (graph.getType(ANALYSIS_MESSAGE_PROPERTY) == null) {
			KeyMaker keyMaker = graph.makeKey(ANALYSIS_MESSAGE_PROPERTY);
			keyMaker.dataType(String.class);
			keyMaker.make();
		}

		if (graph.getType(HAS_ANALYSIS_RUN_LABEL) == null) {
			LabelMaker makeLabel = graph.makeLabel(HAS_ANALYSIS_RUN_LABEL);
			makeLabel.oneToMany(UniquenessConsistency.LOCK);
			makeLabel.signature(graph.getType(ANALYSIS_RUN_UUID_PROPERTY),
					graph.getType(ANALYSIS_RUN_START_TIME_PROPERTY));
			makeLabel.make();
		}
		if (graph.getType(ANALYZED_FILE_TREE_LABEL) == null) {
			LabelMaker makeLabel = graph.makeLabel(ANALYZED_FILE_TREE_LABEL);
			makeLabel.oneToOne();
			makeLabel.signature(graph.getType(TREE_ELEMENT_HASH),
					graph.getType(TREE_ELEMENT_IS_FILE));
			makeLabel.make();
		}
		if (graph.getType(CONTAINS_FILE_LABEL) == null) {
			LabelMaker makeLabel = graph.makeLabel(CONTAINS_FILE_LABEL);
			makeLabel.manyToMany();
			makeLabel.signature(graph.getType(TREE_ELEMENT_HASH),
					graph.getType(TREE_ELEMENT_IS_FILE));
			makeLabel.make();
		}
		if (graph.getType(CONTAINS_DIRECTORY_LABEL) == null) {
			LabelMaker makeLabel = graph.makeLabel(CONTAINS_DIRECTORY_LABEL);
			makeLabel.manyToMany();
			makeLabel.signature(graph.getType(TREE_ELEMENT_HASH),
					graph.getType(TREE_ELEMENT_IS_FILE));
			makeLabel.make();
		}
		if (graph.getType(HAS_ANALYSIS_LABEL) == null) {
			LabelMaker makeLabel = graph.makeLabel(HAS_ANALYSIS_LABEL);
			makeLabel.oneToMany(UniquenessConsistency.LOCK);
			makeLabel.make();
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
