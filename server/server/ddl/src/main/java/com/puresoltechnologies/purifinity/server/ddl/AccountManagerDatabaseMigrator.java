package com.puresoltechnologies.purifinity.server.ddl;

import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationSequence;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class AccountManagerDatabaseMigrator {

	private static final Version V_1_0_0 = new Version(1, 0, 0);

	private final UniversalMigratorConnector connector;

	protected AccountManagerDatabaseMigrator(
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
