package com.puresoltechnologies.purifinity.server.ddl;

import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraCQLTransformationStep;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.versioning.Version;

public class AnalysisServiceDatabaseTransformator implements ComponentTransformator {

    public static final String CASSANDRA_HOST = "localhost";
    public static final int CASSANDRA_CQL_PORT = 9042;

    @Override
    public String getComponentName() {
	return "AnalysisStore";
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_3_0_pre());
	sequences.add(migrateVersion0_3_0());
	return sequences;
    }

    /**
     * This pre version is used to create the keyspace.
     * 
     * @return
     */
    private TransformationSequence migrateVersion0_3_0_pre() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 3, 0, "pre");
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	CassandraTransformationSequence sequence = new CassandraTransformationSequence(CASSANDRA_HOST,
		CASSANDRA_CQL_PORT, metadata);

	sequence.appendTransformation(CassandraStandardMigrations.createKeyspace(sequence, AnalysisStoreKeyspace.NAME,
		"Rick-Rainer Ludwig", "Keyspace for analysis information", ReplicationStrategy.SIMPLE_STRATEGY, 1));

	return sequence;
    }

    private TransformationSequence migrateVersion0_3_0() {
	Version startVersion = new Version(0, 3, 0, "pre");
	Version targetVersion = new Version(0, 3, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	CassandraTransformationSequence sequence = new CassandraTransformationSequence(CASSANDRA_HOST,
		CASSANDRA_CQL_PORT, AnalysisStoreKeyspace.NAME, metadata);

	sequence.appendTransformation(new CassandraCQLTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + CassandraElementNames.ANALYSIS_PROJECT_SETTINGS_TABLE + " (project_id ascii, "
			+ "name varchar, " + "description varchar, " + "file_includes list<text>, "
			+ "file_excludes list<text>, " + "location_includes list<text>, "
			+ "location_excludes list<text>, " + "ignore_hidden boolean, "
			+ "repository_location map<text,text>, " + "PRIMARY KEY(project_id));",
		"Keeps settings of analysis projects."));

	sequence.appendTransformation(new CassandraCQLTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + CassandraElementNames.ANALYSIS_RUN_SETTINGS_TABLE
			+ " (project_id ascii, run_id bigint, " + "file_includes list<text>, "
			+ "file_excludes list<text>, " + "location_includes list<text>, "
			+ "location_excludes list<text>, " + "ignore_hidden boolean, "
			+ "PRIMARY KEY(project_id, run_id));",
		"Keeps settings of analysis runs."));

	sequence.appendTransformation(new CassandraCQLTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + CassandraElementNames.ANALYSIS_ANALYSES_TABLE
			+ " (time timestamp, hashid ascii, language varchar, language_version ascii, analyzer_id ascii, analyzer_version ascii, duration bigint, successful boolean, analyzer_message text,"
			+ "analysis blob, PRIMARY KEY(hashid, analyzer_id, analyzer_version));",
		"Keeps analysis information for analyzed and unanalyzed " + "files and their raw data."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Cluster cluster = CassandraUtils.connectCluster()) {
	    try (Session session = cluster.connect()) {
		session.execute("DROP KEYSPACE IF EXISTS " + AnalysisStoreKeyspace.NAME);
	    }
	}
    }
}
