package com.puresoltechnologies.purifinity.server.database.migration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is a compound implementaion of {@link MigrationStep} to bundle
 * several steps into a logical sequence which is run within migration step.
 * 
 * The steps of the sequence are run in the order of their registration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class MigrationSequence implements MigrationStep {

    private final List<MigrationStep> migrationSteps = new ArrayList<>();

    private final MigrationMetadata metadata;

    public MigrationSequence(MigrationMetadata metadata) {
	super();
	this.metadata = metadata;
    }

    @Override
    public MigrationMetadata getMetadata() {
	return metadata;
    }

    @Override
    public void migrate() {
	// intentionally left empty
    }

    /**
     * Adds a new {@link MigrationStep} at the end of the sequence.
     * 
     * @param migrationStep
     */
    public void registerMigrationStep(MigrationStep migrationStep) {
	migrationSteps.add(migrationStep);
    }

    /**
     * Adds new {@link MigrationStep}s at the end of the sequence.
     * 
     * @param migrationSteps
     *            is a {@link Collection} of {@link MigrationStep}
     */
    public void registerMigrationSteps(List<MigrationStep> migrationSteps) {
	this.migrationSteps.addAll(migrationSteps);
    }

    public List<MigrationStep> getMigrationSteps() {
	return migrationSteps;
    }
}
