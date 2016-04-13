package com.puresoltechnologies.purifinity.server.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

public class EvaluationServiceDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(EvaluationServiceDatabaseTransformator.class);

    private static final String EVALUATION_METRICS_TABLE = "evaluator_store.metrics";

    private static final String EVALUATION_PARAMETERS_TABLE = "evaluator_store.parameters";
    private static final String EVALUATION_FILE_METRICS_TABLE = "evaluator_store.file_metrics";
    private static final String EVALUATION_DIRECTORY_METRICS_TABLE = "evaluator_store.directory_metrics";
    private static final String EVALUATION_PROJECT_METRICS_TABLE = "evaluator_store.project_metrics";
    private static final String EVALUATION_DEFECTS_TABLE = "evaluator_store.defects";
    private static final String EVALUATION_STYLE_ISSUES_TABLE = "evaluator_store.style_issues";

    public static final String HBASE_HOST = "localhost";

    @Override
    public String getComponentName() {
	return "EvaluatorStore";
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_4_0());
	sequences.add(migrateVersion0_5_0());
	return sequences;
    }

    private TransformationSequence migrateVersion0_4_0() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 4, 0);
	Version lastVersion = new Version(0, 5, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, lastVersion);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata, HBASE_HOST);

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",

		"CREATE TABLE " + EVALUATION_PARAMETERS_TABLE + " ("//
			+ "evaluator_id varchar not null, " //
			+ "evaluator_version varchar not null, " //
			+ "parameter_name varchar not null, " //
			+ "plugin_id varchar, " //
			+ "time timestamp, " //
			+ "evaluator_name varchar, " //
			+ "plugin_name varchar, " //
			+ "plugin_version varchar, " //
			+ "vendor varchar, "//
			+ "vendor_url varchar, " //
			+ "plugin_ui_path varchar, " //
			+ "parameter_unit varchar, " //
			+ "parameter_type varchar, " //
			+ "level_of_measurement varchar, " //
			+ "parameter_description varchar, " //
			+ "CONSTRAINT " + EVALUATION_PARAMETERS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(evaluator_id, evaluator_version, parameter_name))",
		"This table contains all available parameters of all evaluators."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",

		"CREATE INDEX evaluator_store_parameters_vendor_idx ON " + EVALUATION_PARAMETERS_TABLE + " (vendor)",
		"This index is used to search for parameters and evaluators of certain vendors."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATION_FILE_METRICS_TABLE + " ("//
			+ "hashid varchar not null, " //
			+ "evaluator_id varchar not null, " //
			+ "parameter_name varchar not null, " //
			+ "code_range_type varchar not null, " //
			+ "code_range_name varchar, " //
			+ "time timestamp, " //
			+ "source_code_location varchar, " //
			+ "evaluator_version varchar, " //
			+ "parameter_unit varchar, " //
			+ "parameter_description varchar, " //
			+ "parameter_type varchar, " //
			+ "level_of_measurement varchar, " //
			+ "metric double, " + "CONSTRAINT " + EVALUATION_FILE_METRICS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, evaluator_id, parameter_name, code_range_type, code_range_name))",
		"Keeps metrics for single files and their code ranges."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATION_DIRECTORY_METRICS_TABLE + " ("//
			+ "hashid varchar not null, " //
			+ "evaluator_id varchar not null, " //
			+ "parameter_name varchar not null, " //
			+ "time timestamp, " //
			+ "evaluator_version varchar, " //
			+ "parameter_unit varchar, " //
			+ "parameter_description varchar, " //
			+ "parameter_type varchar, " //
			+ "level_of_measurement varchar, " //
			+ "metric double, " //
			+ "CONSTRAINT " + EVALUATION_DIRECTORY_METRICS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, evaluator_id, parameter_name))",
		"Keeps metrics for single files and their code ranges."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATION_PROJECT_METRICS_TABLE + " (" //
			+ "project_id varchar not null, " //
			+ "run_id bigint not null, " //
			+ "evaluator_id varchar not null, " //
			+ "parameter_name varchar not null, " //
			+ "time timestamp, " //
			+ "evaluator_version varchar, " //
			+ "parameter_unit varchar, " //
			+ "parameter_description varchar, " //
			+ "parameter_type varchar, " //
			+ "level_of_measurement varchar, " //
			+ "metric double, " //
			+ "CONSTRAINT " + EVALUATION_PROJECT_METRICS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(project_id, run_id, evaluator_id, parameter_name))",
		"Keeps metrics for single files and their code ranges."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATION_METRICS_TABLE + " (" //
			+ "project_id varchar not null, " //
			+ "run_id bigint not null, " //
			+ "evaluator_id varchar not null, " //
			+ "parameter_name varchar not null, " //
			+ "code_range_type varchar not null, " //
			+ "hashid varchar not null, " //
			+ "code_range_name varchar, " //
			+ "time timestamp, " //
			+ "evaluator_version varchar, " //
			+ "internal_directory varchar, " //
			+ "file_name varchar, " //
			+ "source_code_location varchar, " //
			+ "language_name varchar, " //
			+ "language_version varchar, " //
			+ "parameter_unit varchar, " //
			+ "parameter_type varchar, " //
			+ "metric double, " //
			+ "level_of_measurement varchar, " //
			+ "parameter_description varchar, " //
			+ "CONSTRAINT " + EVALUATION_METRICS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(project_id, run_id, evaluator_id, parameter_name, code_range_type, hashid, code_range_name))",
		"Keeps the metrics in a big table for efficient retrieval."));

	return sequence;
    }

    private TransformationSequence migrateVersion0_5_0() {
	Version startVersion = new Version(0, 4, 0);
	Version targetVersion = new Version(0, 5, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata, HBASE_HOST);

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATION_DEFECTS_TABLE + " ("//
			+ "hashid varchar not null, " //
			+ "evaluator_id varchar not null, " //
			+ "defect_id varchar not null, " //
			+ "code_range_type varchar, " //
			+ "code_range_name varchar, " //
			+ "time timestamp, " //
			+ "source_code_location varchar, " //
			+ "evaluator_version varchar, " //
			+ "start_line unsigned_int not null, " //
			+ "start_column unsigned_int not null, " //
			+ "stop_line unsigned_int, " //
			+ "stop_column unsigned_int, " //
			+ "description varchar, " //
			+ "CONSTRAINT " + EVALUATION_DEFECTS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, evaluator_id, defect_id, start_line, start_column))",
		"Keeps defects of source files."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + EVALUATION_STYLE_ISSUES_TABLE + " ("//
			+ "hashid varchar not null, " //
			+ "evaluator_id varchar not null, " //
			+ "style_issue_id varchar not null, " //
			+ "code_range_type varchar, " //
			+ "code_range_name varchar, " //
			+ "time timestamp, " //
			+ "source_code_location varchar, " //
			+ "evaluator_version varchar, " //
			+ "start_line unsigned_int not null, " //
			+ "start_column unsigned_int not null, " //
			+ "stop_line unsigned_int, " //
			+ "stop_column unsigned_int, " //
			+ "description varchar, " //
			+ "CONSTRAINT " + EVALUATION_STYLE_ISSUES_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, evaluator_id, style_issue_id, start_line, start_column))",
		"Keeps style issues of source files."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DriverManager.getConnection("jdbc:phoenix:" + HBASE_HOST);) {
	    try (Statement statement = connection.createStatement();) {
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_METRICS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_PARAMETERS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_FILE_METRICS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_DIRECTORY_METRICS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_PROJECT_METRICS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_DEFECTS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + EVALUATION_STYLE_ISSUES_TABLE);
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
