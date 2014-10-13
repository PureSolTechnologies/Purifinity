package com.puresoltechnologies.purifinity.server.ddl.accountmanager;

import java.io.IOException;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStep;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationStepMetadata;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

public class AccountManagerSchema {

	private static final Version V_1_0_0 = new Version(1, 0, 0);

	public static void createSequence(
			AccountManagerDatabaseMigrator accountManagerDatabaseMigrator) {
		accountManagerDatabaseMigrator
				.registerMigrationStep(new MigrationStep() {

					@Override
					public void migrate(UniversalMigratorTracker tracker,
							UniversalMigratorConnector connector)
							throws IOException, MigrationException {

					}

					@Override
					public MigrationStepMetadata getMetadata() {
						// TODO Auto-generated method stub
						return null;
					}
				});
	}

}
