package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.commons.TransformationMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraCQLTransformationStep;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.TransformationStep;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;
import com.puresoltechnologies.versioning.Version;
import com.puresoltechnologies.versioning.VersionRange;

public class PasswordStoreDatabaseTransformator implements
		ComponentTransformator {

	private static final Logger logger = LoggerFactory
			.getLogger(PasswordStoreDatabaseTransformator.class);

	public static final String TARGET_NAME = "PasswordStore";

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	@Override
	public String getComponentName() {
		return TARGET_NAME;
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
				.createKeyspace(sequence, PASSWORD_STORE_KEYSPACE_NAME,
						"Rick-Rainer Ludwig",
						"This keyspace keeps the user passwords.",
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
				PASSWORD_STORE_KEYSPACE_NAME, metadata);

		String description = "This table contains the authentication data and the state of the account.";
		sequence.appendTransformation(new CassandraCQLTransformationStep(
				sequence, "Rick-Rainer Ludwig", "CREATE TABLE "
						+ PASSWORD_TABLE_NAME//
						+ " (created timestamp, " //
						+ "last_modified timestamp, " //
						+ "email varchar," // 0
						+ "password ascii, " //
						+ "state ascii, "
						+ "activation_key ascii, "//
						+ "PRIMARY KEY (email))" + "WITH comment='"
						+ description + "';", description));

		sequence.appendTransformation(CassandraStandardMigrations.createIndex(
				sequence, "Rick-Rainer Ludwig", "Secondary index on state.",
				PASSWORD_TABLE_NAME, "state"));

		sequence.appendTransformation(new TransformationStep() {
			@Override
			public void transform() throws TransformationException {
				logger.info("Add first administrator account.");
				Session session = sequence.getSession();
				try {
					PreparedStatement preparedStatement = session
							.prepare("INSERT INTO "
									+ PASSWORD_TABLE_NAME//
									+ " (created, " //
									+ "last_modified, " //
									+ "email," //
									+ "password, " //
									+ "state, "
									+ "activation_key) VALUES (?,?,?,?,?,?) ");
					Date now = new Date();
					PasswordData passwordData = new PasswordData(1, Encrypter1
							.encrypt("password"));
					BoundStatement boundStatement = preparedStatement.bind(now,
							now, "ludwig@puresol-technologies.com",
							passwordData.toString(), "ACTIVE", "");
					session.execute(boundStatement);
				} finally {
					session.close();
				}
			}

			@Override
			public TransformationMetadata getMetadata() {
				return new TransformationMetadata(metadata,
						"Rick-Rainer Ludwig", "create admin account.",
						"Creates the first administrators account.");
			}
		});

		return sequence;
	}

	@Override
	public void dropAll() {
		try (Cluster cluster = CassandraUtils.connectCluster()) {
			try (Session session = cluster.connect()) {
				session.execute("DROP KEYSPACE " + PASSWORD_STORE_KEYSPACE_NAME);
			}
		}
	}
}
