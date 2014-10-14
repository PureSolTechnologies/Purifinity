package com.puresoltechnologies.purifinity.server.database.migration;

import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

/**
 * This interface is used for an implementation of a single migration step.
 * 
 * @author Rick-Rainer Ludwigs
 */
public interface MigrationStep {

    public MigrationMetadata getMetadata();

    /**
     * This method runs the actual Migration.
     * 
     * @param tracker
     *            is the {@link UniversalMigratorTracker} to be used for
     *            tracking migration steps.
     * @param connector
     *            is the connector to be used for migration connection.
     * @throws MigrationException
     *             is thrown if there is something wrong with the migration
     *             itself.
     */
    public void migrate() throws MigrationException;

}
