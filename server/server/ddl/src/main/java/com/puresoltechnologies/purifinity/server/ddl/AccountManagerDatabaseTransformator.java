package com.puresoltechnologies.purifinity.server.ddl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.commons.cassandra.ReplicationStrategy;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraStandardMigrations;
import com.puresoltechnologies.genesis.transformation.cassandra.CassandraTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.genesis.transformation.titan.AbstractTitanTransformationStep;
import com.puresoltechnologies.genesis.transformation.titan.TitanTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.Roles;
import com.puresoltechnologies.versioning.Version;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.TitanVertex;

public class AccountManagerDatabaseTransformator implements
	ComponentTransformator {

    public static final String ACCOUNT_MANAGER_KEYSPACE_NAME = "account_manager";
    public static final String CASSANDRA_HOST = "localhost";
    public static final int CASSANDRA_CQL_PORT = 9042;

    @Override
    public String getComponentName() {
	return "AccountManager";
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_3_0_pre());
	sequences.add(migrateVersion0_3_0_titan());
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
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
		startVersion, versionRange);
	CassandraTransformationSequence sequence = new CassandraTransformationSequence(
		CASSANDRA_HOST, CASSANDRA_CQL_PORT, metadata);

	sequence.appendTransformation(CassandraStandardMigrations
		.createKeyspace(
			sequence,
			ACCOUNT_MANAGER_KEYSPACE_NAME,
			"Rick-Rainer Ludwig",
			"This keyspace keeps the account information for all users and groups.",
			ReplicationStrategy.SIMPLE_STRATEGY, 1));

	return sequence;
    }

    /**
     * This pre version is used to create the keyspace.
     * 
     * @return
     */
    private TransformationSequence migrateVersion0_3_0_titan() {
	Version startVersion = new Version(0, 3, 0, "pre");
	Version targetVersion = new Version(0, 3, 0, "titan");
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
		startVersion, versionRange);
	TitanTransformationSequence sequence = new TitanTransformationSequence(
		CASSANDRA_HOST, metadata);

	sequence.appendTransformation(new AbstractTitanTransformationStep(
		sequence, "Rick-Rainer Ludwig", "Creates the standard users.") {

	    @Override
	    public void transform() throws TransformationException {
		TitanGraph titanGraph = getTitanGraph();
		titanGraph.buildTransaction();
		try {
		    TitanVertex userVertex = titanGraph.addVertex();
		    userVertex.setProperty("_xo_discriminator_user", "user");
		    userVertex.setProperty("user_email",
			    "ludwig@puresol-technologies.com");
		    userVertex.setProperty("user_name", "Rick-Rainer Ludwig");
		    userVertex.setProperty("time.creation", new Date());

		    userVertex = titanGraph.addVertex();
		    userVertex.setProperty("_xo_discriminator_user", "user");
		    userVertex.setProperty("user_email",
			    "administrator@puresol-technologies.com");
		    userVertex
			    .setProperty("user_name", "Default Administrator");
		    userVertex.setProperty("time.creation", new Date());

		    userVertex = titanGraph.addVertex();
		    userVertex.setProperty("_xo_discriminator_user", "user");
		    userVertex.setProperty("user_email",
			    "engineer@puresol-technologies.com");
		    userVertex.setProperty("user_name", "Default Engineer");
		    userVertex.setProperty("time.creation", new Date());

		    userVertex = titanGraph.addVertex();
		    userVertex.setProperty("_xo_discriminator_user", "user");
		    userVertex.setProperty("user_email",
			    "user@puresol-technologies.com");
		    userVertex.setProperty("user_name", "Default User");
		    userVertex.setProperty("time.creation", new Date());

		    titanGraph.commit();
		} catch (Exception e) {
		    titanGraph.rollback();
		    throw new TransformationException(
			    "Could not create default user in Titan database.",
			    e);
		}
	    }
	});

	sequence.appendTransformation(new AbstractTitanTransformationStep(
		sequence, "Rick-Rainer Ludwig", "Creates the standard roles.") {

	    @Override
	    public void transform() throws TransformationException {
		TitanGraph titanGraph = getTitanGraph();
		titanGraph.buildTransaction();
		try {
		    for (Roles role : Roles.values()) {
			TitanVertex administratorRoleVertex = titanGraph
				.addVertex();
			administratorRoleVertex.setProperty(
				"_xo_discriminator_role", "role");
			administratorRoleVertex.setProperty("role_id",
				role.getId());
			administratorRoleVertex.setProperty("role_name",
				role.getName());
			administratorRoleVertex.setProperty("time.creation",
				new Date());
		    }
		    titanGraph.commit();
		} catch (Exception e) {
		    titanGraph.rollback();
		    throw new TransformationException(
			    "Could not create default roles in Titan database.",
			    e);
		}
	    }
	});

	sequence.appendTransformation(new AbstractTitanTransformationStep(
		sequence, "Rick-Rainer Ludwig", "Creates the standard roles.") {

	    @Override
	    public void transform() throws TransformationException {
		TitanGraph titanGraph = getTitanGraph();
		titanGraph.buildTransaction();
		try {
		    for (Roles role : Roles.values()) {
			TitanVertex administratorRoleVertex = titanGraph
				.addVertex();
			administratorRoleVertex.setProperty(
				"_xo_discriminator_role", "role");
			administratorRoleVertex.setProperty("role_id",
				role.getId());
			administratorRoleVertex.setProperty("role_name",
				role.getName());
			administratorRoleVertex.setProperty("time.creation",
				new Date());
		    }
		    titanGraph.commit();
		} catch (Exception e) {
		    titanGraph.rollback();
		    throw new TransformationException(
			    "Could not create default roles in Titan database.",
			    e);
		}
	    }
	});

	return sequence;
    }

    private TransformationSequence migrateVersion0_3_0() {
	Version startVersion = new Version(0, 3, 0, "titan");
	Version targetVersion = new Version(0, 3, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(
		targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(),
		startVersion, versionRange);
	CassandraTransformationSequence sequence = new CassandraTransformationSequence(
		CASSANDRA_HOST, CASSANDRA_CQL_PORT,
		ACCOUNT_MANAGER_KEYSPACE_NAME, metadata);

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Cluster cluster = CassandraUtils.connectCluster()) {
	    try (Session session = cluster.connect()) {
		session.execute("DROP KEYSPACE IF EXISTS "
			+ ACCOUNT_MANAGER_KEYSPACE_NAME);
	    }
	}
    }
}
