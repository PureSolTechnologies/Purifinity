package com.puresoltechnologies.purifinity.server.metrics.ddl;

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

public class McCabeMetricEvaluatorDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(McCabeMetricEvaluatorDatabaseTransformator.class);

    private static final String FILE_RESULTS_TABLE = "mccabe_metric.file_results";
    private static final String DIRECTORY_RESULTS_TABLE = "mccabe_metric.directory_results";
    private static final String PROJECT_RESULTS_TABLE = "mccabe_metric.project_results";

    @Override
    public String getComponentName() {
	return "McCabeMetricEvaluator";
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
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata,
		DatabaseTransformatorConstants.HBASE_HOST);

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + FILE_RESULTS_TABLE + " (hashid varchar, " + "evaluator_id varchar, "
			+ "source_code_location varchar, " + "code_range_type varchar, " + "code_range_name varchar, "
			+ "vg integer, " + "CONSTRAINT " + FILE_RESULTS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, evaluator_id, code_range_type, code_range_name))",
		"Keeps directory results for McCabe Metric evaluator."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + DIRECTORY_RESULTS_TABLE + " (hashid varchar, " + "evaluator_id varchar, "
			+ "vg integer, " + "CONSTRAINT " + DIRECTORY_RESULTS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, evaluator_id))",
		"Keeps directory results for McCabe Metric evaluator."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + PROJECT_RESULTS_TABLE + " (project_id varchar, " + "evaluator_id varchar, "
			+ "vg integer, " + "CONSTRAINT " + PROJECT_RESULTS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(project_id, evaluator_id))",
		"Keeps project results for McCabe Metric evaluator."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DriverManager
		.getConnection("jdbc:phoenix:" + DatabaseTransformatorConstants.HBASE_HOST);) {
	    try (Statement statement = connection.createStatement();) {
		statement.execute("DROP TABLE IF EXISTS " + FILE_RESULTS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + DIRECTORY_RESULTS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + PROJECT_RESULTS_TABLE);
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
