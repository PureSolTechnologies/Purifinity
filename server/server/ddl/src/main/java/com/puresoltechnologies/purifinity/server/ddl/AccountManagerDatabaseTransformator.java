package com.puresoltechnologies.purifinity.server.ddl;

import com.puresoltechnologies.commons.versioning.Version;
import com.puresoltechnologies.genesis.transformation.spi.Transformator;

public class AccountManagerDatabaseTransformator implements Transformator {

	private static final Version V_1_0_0 = new Version(1, 0, 0);

	private final UniversalMigratorConnector connector;

	protected AccountManagerDatabaseTransformator(
			UniversalMigratorConnector connector) {
		this.connector = connector;
	}

	public void drop() {
	}

	public MigrationSequence getSequence() {
		MigrationSequence sequence = new MigrationSequence(
				new MigrationMetadata(V_1_0_0, "Rick-Rainer Ludwig",
						"Account Manager", "", "Version " + V_1_0_0
								+ " sequence."));
		sequence.registerMigrationStep(new MigrationStep() {

			@Override
			public void migrate() throws MigrationException {

			}

			@Override
			public MigrationMetadata getMetadata() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		return sequence;
	}
}
