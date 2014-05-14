package com.puresoltechnologies.purifinity.server.database.migration;

/**
 * This interface represents a database migrator.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface DatabaseMigrator {

	/**
	 * This method returns the connector used for migration.
	 * 
	 * @return A {@link DatabaseMigrationConnector} object is returned.
	 */
	public DatabaseMigrationConnector getConnector();

	/**
	 * This method registers a new migration step for the migrator.
	 * 
	 * @param migrationStep
	 *            is a {@link MigrationStep} to be used for migration.
	 */
	public void registerMigrationStep(MigrationStep migrationStep);

}
