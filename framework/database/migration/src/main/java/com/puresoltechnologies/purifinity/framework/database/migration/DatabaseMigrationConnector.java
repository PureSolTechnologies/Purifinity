package com.puresoltechnologies.purifinity.framework.database.migration;

import java.io.Closeable;
import java.io.IOException;

/**
 * This interface represents a single connector for a database for its
 * migration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface DatabaseMigrationConnector extends Closeable {

	/**
	 * This method is called during the initialization phase of the migrator.
	 * Here the database connection should be opened and it shall be checked
	 * that all requirements are met for a success migration run.
	 * 
	 * @throws IOException
	 *             is thrown if an IO error occurred with the database.
	 * @throws MigrationException
	 *             is thrown in case of an issues during migration preparation.
	 */
	public void initialize() throws IOException, MigrationException;

	/**
	 * This method is called directly before the actual migration starts.
	 */
	public void startMigration() throws MigrationException;

	/**
	 * This method is called directly after the migration process.
	 */
	public void finishMigration() throws MigrationException;

	/**
	 * This method is called before the migrator is shut down to release all
	 * connections and to free all resources.
	 */
	@Override
	public void close() throws IOException;
}
