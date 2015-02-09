package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraCQLTransformationStep;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordState;
import com.puresoltechnologies.versioning.Version;

public class PasswordStoreDatabaseTransformator implements
	ComponentTransformator {

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
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
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
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
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

	sequence.appendTransformation(new AddUserStep(sequence,
		new EmailAddress("user@puresol-technologies.com"), "password",
		PasswordState.ACTIVE, "Rick-Rainer Ludwig",
		"Create default user account."));

	sequence.appendTransformation(new AddUserStep(sequence,
		new EmailAddress("engineer@puresol-technologies.com"),
		"password", PasswordState.ACTIVE, "Rick-Rainer Ludwig",
		"Create default engineer account."));

	sequence.appendTransformation(new AddUserStep(sequence,
		new EmailAddress("administrator@puresol-technologies.com"),
		"password", PasswordState.ACTIVE, "Rick-Rainer Ludwig",
		"Create default administrator account."));

	sequence.appendTransformation(new AddUserStep(sequence,
		new EmailAddress("ludwig@puresol-technologies.com"),
		"password", PasswordState.ACTIVE, "Rick-Rainer Ludwig",
		"Creates first user account."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Cluster cluster = CassandraUtils.connectCluster()) {
	    try (Session session = cluster.connect()) {
		session.execute("DROP KEYSPACE IF EXISTS "
			+ PASSWORD_STORE_KEYSPACE_NAME);
	    }
	}
    }
}
