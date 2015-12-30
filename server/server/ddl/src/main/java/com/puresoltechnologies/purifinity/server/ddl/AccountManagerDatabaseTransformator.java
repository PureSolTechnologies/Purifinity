package com.puresoltechnologies.purifinity.server.ddl;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.ductiledb.AbstractDuctileDBTransformationStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.genesis.transformation.phoenix.PhoenixTransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.versioning.Version;

public class AccountManagerDatabaseTransformator implements ComponentTransformator {

    public static final String HBASE_HOST = "localhost";

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
	sequences.add(migrateVersion0_4_0_titan());
	sequences.add(migrateVersion0_4_0());
	return sequences;
    }

    /**
     * This pre version is used to create the keyspace.
     * 
     * @return
     */
    private TransformationSequence migrateVersion0_4_0_titan() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 4, 0, "titan");
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	DuctileDBTransformationSequence sequence = new DuctileDBTransformationSequence(HBASE_HOST, metadata);

	sequence.appendTransformation(new AbstractDuctileDBTransformationStep(sequence, "Rick-Rainer Ludwig",
		"Create indizes for Account manager.",
		"All indizes are added which improve performance and stability of account manager.") {

	    @Override
	    public void transform() throws TransformationException {
		// DuctileDBGraph titanGraph = getDuctileDBGraph();
		// try {
		// TitanManagement managementSystem =
		// titanGraph.getManagementSystem();
		// try {
		// managementSystem.makeEdgeLabel("belongsTo").multiplicity(Multiplicity.MANY2ONE).make();
		// PropertyKey roleIdProperty = managementSystem
		// .makePropertyKey(TitanElementNames.ROLE_ID_PROPERTY).dataType(String.class)
		// .cardinality(Cardinality.SINGLE).make();
		// managementSystem.buildIndex(TitanElementNames.ROLE_ID_PROPERTY
		// + ".Index", Vertex.class)
		// .addKey(roleIdProperty).unique().buildCompositeIndex();
		// PropertyKey userEmailProperty = managementSystem
		// .makePropertyKey(TitanElementNames.USER_EMAIL_PROPERTY).dataType(String.class)
		// .cardinality(Cardinality.SINGLE).make();
		// managementSystem.buildIndex(TitanElementNames.USER_EMAIL_PROPERTY
		// + ".Index", Vertex.class)
		// .addKey(userEmailProperty).unique().buildCompositeIndex();
		// } finally {
		// managementSystem.commit();
		// }
		// } finally {
		// titanGraph.commit();
		// }
	    }
	});

	sequence.appendTransformation(new AddRoleStep(sequence, SupportedRoles.UNPRIVILEGED, "Rick-Rainer Ludwig",
		"Create unprivileged role."));
	sequence.appendTransformation(
		new AddRoleStep(sequence, SupportedRoles.ENGINEER, "Rick-Rainer Ludwig", "Creates the engineer role."));
	sequence.appendTransformation(new AddRoleStep(sequence, SupportedRoles.ADMINISTRATOR, "Rick-Rainer Ludwig",
		"Creates the administrator role."));

	sequence.appendTransformation(new AddUserStep(sequence, new EmailAddress("user@puresol-technologies.com"),
		"Unprivileged User", SupportedRoles.UNPRIVILEGED, "Rick-Rainer Ludwig", "Create unprivileged user."));

	sequence.appendTransformation(new AddUserStep(sequence, new EmailAddress("engineer@puresol-technologies.com"),
		"Engineer", SupportedRoles.ENGINEER, "Rick-Rainer Ludwig", "Create engineer."));

	sequence.appendTransformation(
		new AddUserStep(sequence, new EmailAddress("administrator@puresol-technologies.com"), "Administrator",
			SupportedRoles.ADMINISTRATOR, "Rick-Rainer Ludwig", "Create administrator."));

	sequence.appendTransformation(new AddUserStep(sequence, new EmailAddress("ludwig@puresol-technologies.com"),
		"Rick-Rainer Ludwig", SupportedRoles.ADMINISTRATOR, "Rick-Rainer Ludwig", "Create first user."));
	return sequence;
    }

    private TransformationSequence migrateVersion0_4_0() {
	Version startVersion = new Version(0, 4, 0, "titan");
	Version targetVersion = new Version(0, 4, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata, HBASE_HOST);

	return sequence;
    }

    @Override
    public void dropAll() {
    }
}
