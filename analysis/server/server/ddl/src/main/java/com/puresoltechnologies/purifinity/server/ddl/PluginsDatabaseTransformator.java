package com.puresoltechnologies.purifinity.server.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.transformation.phoenix.PhoenixTransformationSequence;
import com.puresoltechnologies.genesis.transformation.phoenix.PhoenixTransformationStep;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class PluginsDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(PluginsDatabaseTransformator.class);

    private static final String PLUGINS_TABLE = "plugins_database.plugins";
    private static final String ANALYZERS_TABLE = "plugins_database.analyzers";
    private static final String EVALUATORS_TABLE = "plugins_database.evaluators";
    private static final String EVALUATOR_PARAMETERS_TABLE = "plugins_database.evaluator_parameters";
    private static final String REPOSITORY_TYPES_TABLE = "plugins_repository_types";
    private static final String REPOSITORY_TYPE_PARAMETERS_TABLE = "plugins_database.repository_type_parameters";

    public static final String HBASE_HOST = "localhost";

    @Override
    public String getComponentName() {
	return "PluginsDatabase";
    }

    @Override
    public Set<String> getDependencies() {
	return Collections.emptySet();
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_4_0());
	return sequences;
    }

    private TransformationSequence migrateVersion0_4_0() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 4, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata, HBASE_HOST);

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + PLUGINS_TABLE + " ("//
			+ "id varchar not null, "//
			+ "version varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "name varchar, "//
			+ "description varchar, "//
			+ "vendor varchar, "//
			+ "vendor_url varchar, "//
			+ "path_to_ui varchar, " //
			+ "CONSTRAINT " + PLUGINS_TABLE.replaceAll("\\.", "_") + "_PK PRIMARY KEY(id, version))",
		"Keeps information about installed plugins."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + ANALYZERS_TABLE + " ("//
			+ "id varchar not null, "//
			+ "version varchar not null, "//
			+ "changed timestamp, " //
			+ "changed_by varchar, " //
			+ "name varchar, "//
			+ "plugin_id varchar, " //
			+ "plugin_version varchar, " //
			+ "jndi_name varchar, "//
			+ "description varchar, " //
			+ "service_url varchar, " //
			+ "configuration_url varchar, " //
			+ "project_url varchar, "//
			+ "run_url varchar," //
			+ "CONSTRAINT " + ANALYZERS_TABLE.replaceAll("\\.", "_") + "_PK PRIMARY KEY(id, version))",
		"Keeps information about installed analyzers."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATORS_TABLE + " ("//
			+ "id varchar not null, "//
			+ "changed timestamp, " //
			+ "changed_by varchar, " //
			+ "name varchar, "//
			+ "type varchar, "//
			+ "plugin_id varchar, " //
			+ "plugin_version varchar, " //
			+ "jndi_name varchar, "//
			+ "description varchar, "//
			+ "service_url varchar, " //
			+ "configuration_url varchar, "//
			+ "project_url varchar, "//
			+ "run_url varchar, " //
			+ "quality_characteristics varchar array, " //
			+ " dependencies varchar array, "//
			+ "CONSTRAINT " + EVALUATORS_TABLE.replaceAll("\\.", "_") + "_PK PRIMARY KEY(id))",
		"Keeps information about the installed evaluators."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATOR_PARAMETERS_TABLE + " ("//
			+ "evaluator_id varchar not null, "//
			+ "name varchar not null, "//
			+ "changed timestamp, " //
			+ "changed_by varchar, "//
			+ "unit varchar, " //
			+ "description varchar, "//
			+ "level_of_measurement varchar, " //
			+ "type varchar, " //
			+ "numeric boolean, " //
			+ "CONSTRAINT " + EVALUATOR_PARAMETERS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(evaluator_id, name))",
		"Keeps information about the provided parameters of evaluators."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + REPOSITORY_TYPES_TABLE + " ("//
			+ "class_name varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "name varchar, " //
			+ "plugin_id varchar, " //
			+ "plugin_version varchar, "//
			+ "description varchar, " //
			+ "CONSTRAINT " + REPOSITORY_TYPES_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(class_name))",
		"Keeps information about the installed evaluators."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + REPOSITORY_TYPE_PARAMETERS_TABLE + " ("//
			+ "class_name varchar not null, " //
			+ "name varchar not null, " //
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "unit varchar, " //
			+ "description varchar, "//
			+ "level_of_measurement varchar, " //
			+ "type varchar, " //
			+ "numeric boolean, " //
			+ "CONSTRAINT " + REPOSITORY_TYPE_PARAMETERS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(class_name, name))",
		"Keeps information about the installed evaluators."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DriverManager.getConnection("jdbc:phoenix:" + HBASE_HOST);) {
	    try (Statement statement = connection.createStatement();) {
		statement.execute("DROP TABLE IF EXISTS " + PLUGINS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + ANALYZERS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATORS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATOR_PARAMETERS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + REPOSITORY_TYPES_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + REPOSITORY_TYPE_PARAMETERS_TABLE);
		connection.commit();
	    } catch (SQLException e) {
		try {
		    connection.rollback();
		} catch (SQLException e1) {
		    logger.warn("Cannot rollback.", e);
		}
		throw new RuntimeException("Could not drop component tables.", e);
	    }
	} catch (SQLException e2) {
	    throw new RuntimeException("Could not open Phoenix connection to HBase.", e2);
	}
    }
}
