package com.puresoltechnologies.purifinity.server.database.titan;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.LabelMaker;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TypeMaker.UniquenessConsistency;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

@Singleton
public class TitanGraphProducer {

    @Inject
    private Logger logger;

    private TitanGraph graph;

    @PostConstruct
    private void initialize() {
	logger.info("Connect to Titan...");
	graph = TitanGraphHelper.connect();
	logger.info("Titan connected.");
	checkLabelAndKeySettings();
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

    private void checkLabelAndKeySettings() {

	if (graph.getType(TitanElementNames.VERTEX_TYPE) == null) {
	    KeyMaker keyMaker = graph.makeKey(TitanElementNames.VERTEX_TYPE);
	    keyMaker.dataType(String.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.CREATION_TIME_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.CREATION_TIME_PROPERTY);
	    keyMaker.dataType(Date.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY);
	    /*
	     * UUID is not possible, because it does not have a default
	     * constructor. We use String here.
	     */
	    keyMaker.dataType(String.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.unique();
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.ANALYSIS_PROJECT_NAME_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_PROJECT_NAME_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.unique();
	    keyMaker.make();
	}

	if (graph
		.getType(TitanElementNames.ANALYSIS_PROJECT_DESCRIPTION_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_PROJECT_DESCRIPTION_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY);
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

	if (graph.getType(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY);
	    keyMaker.dataType(Date.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.indexed(Edge.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_RUN_DURATION_PROPERTY);
	    keyMaker.dataType(Long.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.TREE_ELEMENT_IS_FILE) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_IS_FILE);
	    keyMaker.dataType(Boolean.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}
	if (graph
		.getType(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES_RECURSIVE) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES_RECURSIVE);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}
	if (graph
		.getType(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.TREE_ELEMENT_SIZE) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_SIZE);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.TREE_ELEMENT_HASH) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.TREE_ELEMENT_HASH);
	    keyMaker.dataType(String.class);
	    keyMaker.indexed(Vertex.class);
	    keyMaker.indexed(Edge.class);
	    keyMaker.unique();
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.ANALYSIS_NAME_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_NAME_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_VERSION_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_VERSION_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_START_TIME_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_START_TIME_PROPERTY);
	    keyMaker.dataType(Date.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_DURATION_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_DURATION_PROPERTY);
	    keyMaker.dataType(Long.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_LANGUAGE_NAME_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_LANGUAGE_NAME_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_LANGUAGE_VERSION_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_LANGUAGE_VERSION_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_SUCCESSFUL_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_SUCCESSFUL_PROPERTY);
	    keyMaker.dataType(Boolean.class);
	    keyMaker.make();
	}
	if (graph.getType(TitanElementNames.ANALYSIS_MESSAGE_PROPERTY) == null) {
	    KeyMaker keyMaker = graph
		    .makeKey(TitanElementNames.ANALYSIS_MESSAGE_PROPERTY);
	    keyMaker.dataType(String.class);
	    keyMaker.make();
	}

	if (graph.getType(TitanElementNames.HAS_ANALYSIS_RUN_LABEL) == null) {
	    LabelMaker makeLabel = graph
		    .makeLabel(TitanElementNames.HAS_ANALYSIS_RUN_LABEL);
	    makeLabel.oneToMany(UniquenessConsistency.LOCK);
	    makeLabel
		    .signature(
			    graph.getType(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY),
			    graph.getType(TitanElementNames.ANALYSIS_RUN_START_TIME_PROPERTY));
	    makeLabel.make();
	}
	if (graph.getType(TitanElementNames.ANALYZED_FILE_TREE_LABEL) == null) {
	    LabelMaker makeLabel = graph
		    .makeLabel(TitanElementNames.ANALYZED_FILE_TREE_LABEL);
	    makeLabel.manyToOne();
	    makeLabel.signature(
		    graph.getType(TitanElementNames.TREE_ELEMENT_HASH),
		    graph.getType(TitanElementNames.TREE_ELEMENT_IS_FILE));
	    makeLabel.make();
	}
	if (graph.getType(TitanElementNames.CONTAINS_FILE_LABEL) == null) {
	    LabelMaker makeLabel = graph
		    .makeLabel(TitanElementNames.CONTAINS_FILE_LABEL);
	    makeLabel.manyToMany();
	    makeLabel.signature(
		    graph.getType(TitanElementNames.TREE_ELEMENT_HASH),
		    graph.getType(TitanElementNames.TREE_ELEMENT_IS_FILE));
	    makeLabel.make();
	}
	if (graph.getType(TitanElementNames.CONTAINS_DIRECTORY_LABEL) == null) {
	    LabelMaker makeLabel = graph
		    .makeLabel(TitanElementNames.CONTAINS_DIRECTORY_LABEL);
	    makeLabel.manyToMany();
	    makeLabel.signature(
		    graph.getType(TitanElementNames.TREE_ELEMENT_HASH),
		    graph.getType(TitanElementNames.TREE_ELEMENT_IS_FILE));
	    makeLabel.make();
	}
	graph.commit();
    }
}
