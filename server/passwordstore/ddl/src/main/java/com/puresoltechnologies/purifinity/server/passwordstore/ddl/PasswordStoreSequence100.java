package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.versioning.Version;
import com.puresoltechnologies.commons.versioning.VersionRange;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.commons.TransformationMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraMigration;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.TransformationStep;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordData;
import com.puresoltechnologies.purifinity.server.passwordstore.utils.encrypt.Encrypter1;

public class PasswordStoreSequence100 implements TransformationSequence {

	private static final Logger logger = LoggerFactory
			.getLogger(PasswordStoreSequence100.class);

	public static final String PASSWORD_STORE_KEYSPACE_NAME = "password_store";
	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String PASSWORD_TABLE_NAME = "passwords";

	private Cluster cluster = null;
	private Session session = null;

	@Override
	public void open() {
		cluster = CassandraUtils.connectCluster();
		session = CassandraUtils.connectWithoutKeyspace(cluster);
	}

	@Override
	public void close() {
		session.close();
		cluster.close();
	}

	@Override
	public Version getStartVersion() {
		return new Version(0, 0, 0);
	}

	@Override
	public VersionRange getProvidedVersionRange() {
		Version minVersion = new Version(0, 0, 0);
		Version maxVersion = new Version(1, 0, 0);
		return new VersionRange(minVersion, true, maxVersion, true);
	}

	@Override
	public boolean isHostBased() {
		return false;
	}

	@Override
	public List<TransformationStep> getTransformations() {
		Version v100 = new Version(1, 0, 0);
		List<TransformationStep> transformations = new ArrayList<>();
		transformations.add(CassandraMigration.createKeyspace(session,
				PasswordStoreTransformator.TARGET_NAME,
				PASSWORD_STORE_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				"Keeps the user passwords.",
				ReplicationStrategy.SIMPLE_STRATEGY, 3));

		String description = "This table contains the authentication data and the state of the account.";
		transformations.add(CassandraMigration.createTable(session,
				PasswordStoreTransformator.TARGET_NAME, v100,
				"Rick-Rainer Ludwig", description, "CREATE TABLE "
						+ PASSWORD_TABLE_NAME//
						+ " (created timestamp, " //
						+ "last_modified timestamp, " //
						+ "email varchar," // 0
						+ "password ascii, " //
						+ "state ascii, "
						+ "activation_key ascii, "//
						+ "PRIMARY KEY (email))" + "WITH comment='"
						+ description + "';"));
		transformations.add(CassandraMigration.createIndex(session,
				PASSWORD_STORE_KEYSPACE_NAME, v100, "Rick-Rainer Ludwig",
				"Secondary index on state.", PASSWORD_TABLE_NAME, "state"));
		transformations.add(new TransformationStep() {

			@Override
			public void transform() throws TransformationException {
				logger.info("Add first administrator account.");
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
				return new TransformationMetadata(v100, "Rick-Rainer Ludwig",
						PASSWORD_STORE_KEYSPACE_NAME, "create admin account.",
						"Creates the first administrators account.");
			}
		});
		return transformations;
	}
}
