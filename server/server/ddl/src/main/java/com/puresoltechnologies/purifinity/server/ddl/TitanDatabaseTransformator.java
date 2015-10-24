package com.puresoltechnologies.purifinity.server.ddl;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.genesis.transformation.titan.AbstractTitanTransformationStep;
import com.puresoltechnologies.genesis.transformation.titan.TitanTransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class TitanDatabaseTransformator implements ComponentTransformator {

    public static final String TITAN_HOST = "localhost";

    @Override
    public String getComponentName() {
	return "TitanGraphDatabase";
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_3_0());
	return sequences;
    }

    private TransformationSequence migrateVersion0_3_0() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 3, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	TitanTransformationSequence sequence = new TitanTransformationSequence(TITAN_HOST, metadata);

	sequence.appendTransformation(new AbstractTitanTransformationStep(sequence, "Rick-Rainer Ludwig",
		"Create Titan Keyspace", "Create Titan Keyspace") {
	    @Override
	    public void transform() throws TransformationException {
		// intentionally left blank
	    }
	});

	return sequence;
    }

    @Override
    public void dropAll() {
	// FIXME
	// try (Cluster cluster = CassandraUtils.connectCluster()) {
	// try (Session session = cluster.connect()) {
	// session.execute("DROP KEYSPACE IF EXISTS titan");
	// }
	// }
    }

}
