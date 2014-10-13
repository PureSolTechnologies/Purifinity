package com.puresoltechnologies.purifinity.server.database.migration;

import java.io.IOException;

import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

/**
 * This interface represents a database migrator.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface UniversalMigrator {

	/**
	 * This method returns the connector used for migration.
	 * 
	 * @return A {@link UniversalMigratorConnector} object is returned.
	 */
	public UniversalMigratorConnector getConnector();

	/**
	 * This method registers a new migration step for the migrator.
	 * 
	 * @param migrationStep
	 *            is a {@link MigrationStep} to be used for migration.
	 */
	public void registerMigrationStep(MigrationStep migrationStep);

	public void migrate() throws IOException, MigrationException;
}
