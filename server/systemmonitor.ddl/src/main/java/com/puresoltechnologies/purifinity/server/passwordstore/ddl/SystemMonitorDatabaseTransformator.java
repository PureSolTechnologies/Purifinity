package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraCQLTransformationStep;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;
import com.puresoltechnologies.versioning.VersionRange;

public class SystemMonitorDatabaseTransformator implements
		ComponentTransformator {

	public static final String SYSTEM_MONITOR_KEYSPACE_NAME = "system_monitor";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	private static final String EVENTS_TABLE_NAME = "events";
	private static final String METRICS_TABLE_NAME = "metrics";

	@Override
	public String getComponentName() {
		return "SystemMonitor";
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
		VersionRange versionRange = new VersionRange(targetVersion, true, null,
				false);
		SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
				startVersion, versionRange);
		CassandraTransformationSequence sequence = new CassandraTransformationSequence(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT, metadata);
		sequence.appendTransformation(CassandraStandardMigrations
				.createKeyspace(
						sequence,
						SYSTEM_MONITOR_KEYSPACE_NAME,
						"Rick-Rainer Ludwig",
						"This keyspace keeps the system status information and event logs.",
						ReplicationStrategy.SIMPLE_STRATEGY, 1));
		return sequence;
	}

	private TransformationSequence migrateVersion0_3_0() {
		Version startVersion = new Version(0, 3, 0, "pre");
		Version targetVersion = new Version(0, 3, 0);
		VersionRange versionRange = new VersionRange(targetVersion, true, null,
				false);
		SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
				startVersion, versionRange);
		CassandraTransformationSequence sequence = new CassandraTransformationSequence(
				CASSANDRA_HOST, CASSANDRA_CQL_PORT,
				SYSTEM_MONITOR_KEYSPACE_NAME, metadata);

		String description = "This is the table for the event log.";
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence,
				"Rick-Rainer Ludwig",
				"CREATE TABLE "
						+ EVENTS_TABLE_NAME //
						+ " (time timestamp, " //
						+ "component ascii," //
						+ "event_id bigint," //
						+ "server ascii," //
						+ "type ascii, " //
						+ "severity ascii, "
						+ "message text, "//
						+ "user varchar, "
						+ "user_id bigint," //
						+ "client ascii, " //
						+ "exception_message ascii, "
						+ "exception_stacktrace ascii, "//
						+ "PRIMARY KEY (server, time, severity, type, component, event_id, message))"
						+ "WITH comment='" + description + "';", description));

		description = "This is the table for metrics and KPIs.";
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence, "Rick-Raienr Ludwig", "CREATE TABLE "
						+ METRICS_TABLE_NAME //
						+ " (time timestamp, " //
						+ "server ascii," //
						+ "name varchar," //
						+ "unit varchar, " //
						+ "type ascii, "
						+ "description text, "//
						+ "decimal_value decimal, "//
						+ "integer_value varint, "//
						+ "level_of_measurement ascii, "
						+ "PRIMARY KEY (server, time, name))"
						+ "WITH comment='" + description + "';", description));
		return sequence;
	}

	@Override
	public void dropAll() {
		try (Cluster cluster = CassandraUtils.connectCluster()) {
			try (Session session = cluster.connect()) {
				session.execute("DROP KEYSPACE " + SYSTEM_MONITOR_KEYSPACE_NAME);
			}
		}
	}
}
