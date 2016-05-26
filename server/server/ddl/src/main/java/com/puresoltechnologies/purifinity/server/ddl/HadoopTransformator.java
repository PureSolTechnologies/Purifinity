package com.puresoltechnologies.purifinity.server.ddl;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.transformation.hadoop.HadoopCreateDirectoryStep;
import com.puresoltechnologies.genesis.transformation.hadoop.HadoopTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class HadoopTransformator implements ComponentTransformator {

    public static final String COMPONENT_NAME = "Hadoop";

    @Override
    public String getComponentName() {
	return COMPONENT_NAME;
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

	HadoopTransformationSequence sequence = new HadoopTransformationSequence(new File("/opt/hadoop"), metadata);

	sequence.appendTransformation(new HadoopCreateDirectoryStep(sequence, new Path("/apps/Purifinity"),
		new FsPermission("755"), "Rick-Rainer Ludwig", "Creating Purifinity's home directory."));

	sequence.appendTransformation(new HadoopCreateDirectoryStep(sequence, new Path("/apps/Purifinity/files"),
		new FsPermission("755"), "Rick-Rainer Ludwig", "Creating Purifinity's raw file data directory."));

	return sequence;
    }

    @Override
    public void dropAll() {
	// TODO Auto-generated method stub

    }

}
