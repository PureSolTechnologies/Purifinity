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
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;
import com.puresoltechnologies.versioning.Version;

public class AnalysisServiceDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisServiceDatabaseTransformator.class);

    public static final String HBASE_HOST = "localhost";

    @Override
    public String getComponentName() {
	return "AnalysisStore";
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
		"CREATE TABLE " + HBaseElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE + " ("//
			+ "project_id varchar not null, " //
			+ "name varchar, "//
			+ "description varchar, "//
			+ "file_includes varchar array, " //
			+ "file_excludes varchar array, " //
			+ "location_includes varchar array, "//
			+ "location_excludes varchar array, "//
			+ "ignore_hidden boolean, "//
			+ "repository_location varchar, "//
			+ "CONSTRAINT " + HBaseElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(project_id))",
		"Keeps settings of analysis projects."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + HBaseElementNames.ANALYSIS_RUN_SETTINGS_TABLE + " ("//
			+ "project_id varchar not null, "//
			+ "run_id bigint not null, " //
			+ "file_includes varchar array, "//
			+ "file_excludes varchar array, " //
			+ "location_includes varchar array, "//
			+ "location_excludes varchar array, " //
			+ "ignore_hidden boolean, " //
			+ "CONSTRAINT " + HBaseElementNames.ANALYSIS_RUN_SETTINGS_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(project_id, run_id))",
		"Keeps settings of analysis runs."));

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + HBaseElementNames.ANALYSIS_ANALYSES_TABLE + " ("//
			+ "hashid varchar not null, "//
			+ "analyzer_id varchar not null, "//
			+ "analyzer_version varchar not null, "//
			+ "time timestamp, "//
			+ "language varchar, "//
			+ "language_version varchar, "//
			+ "duration bigint, "//
			+ "successful boolean, "//
			+ "analyzer_message varchar," //
			+ "analysis varbinary, "//
			+ "CONSTRAINT " + HBaseElementNames.ANALYSIS_ANALYSES_TABLE.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY(hashid, analyzer_id, analyzer_version))",
		"Keeps analysis information for analyzed and unanalyzed " + "files and their raw data."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DriverManager.getConnection("jdbc:phoenix:" + HBASE_HOST);) {
	    try (Statement statement = connection.createStatement();) {
		statement.execute("DROP TABLE IF EXISTS " + HBaseElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + HBaseElementNames.ANALYSIS_RUN_SETTINGS_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + HBaseElementNames.ANALYSIS_ANALYSES_TABLE);
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
