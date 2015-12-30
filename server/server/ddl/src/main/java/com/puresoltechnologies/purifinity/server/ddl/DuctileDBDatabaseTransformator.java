package com.puresoltechnologies.purifinity.server.ddl;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.ductiledb.AbstractDuctileDBTransformationStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class DuctileDBDatabaseTransformator implements ComponentTransformator {

    public static final String DUCTILE_DB_HOST = "localhost";

    @Override
    public String getComponentName() {
	return "DuctileDBGraphDatabase";
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
	DuctileDBTransformationSequence sequence = new DuctileDBTransformationSequence(DUCTILE_DB_HOST, metadata);

	sequence.appendTransformation(new AbstractDuctileDBTransformationStep(sequence, "Rick-Rainer Ludwig",
		"Create DuctileDB Keyspace", "Create DuctileDB Keyspace") {
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
