package com.puresoltechnologies.purifinity.framework.database.migration;

import java.io.IOException;

/**
 * This interface is used for an implementation of a single migration step.
 * 
 * @author Rick-Rainer Ludwigs
 */
public interface MigrationStep {

	/**
	 * This method runs the actual Migration.
	 * 
	 * @throws MigrationException
	 *             is thrown if there is something wrong with the migration
	 *             itself.
	 * @throws IOException
	 *             is thrown in a case of connection and database issues.
	 */
	public void migrate() throws IOException, MigrationException;

}
