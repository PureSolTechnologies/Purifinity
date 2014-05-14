package com.puresoltechnologies.purifinity.server.database.migration;

import java.io.IOException;
import java.util.ArrayList;
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
public class MigrationSequence implements MigrationStep {

	private final List<MigrationStep> migrationSteps = new ArrayList<>();

	@Override
	public MigrationStepMetadata getMetadata() {
		return null;
	}

	@Override
	public void migrate(DatabaseMigrationConnector connector)
			throws IOException, MigrationException {
		for (MigrationStep migrationStep : migrationSteps) {
			migrationStep.migrate(connector);
		}
	}

	/**
	 * Adds a new {@link MigrationStep} at the end of the sequence.
	 * 
	 * @param migrationStep
	 */
	protected void registerMigrationStep(MigrationStep migrationStep) {
		migrationSteps.add(migrationStep);
	}

}
