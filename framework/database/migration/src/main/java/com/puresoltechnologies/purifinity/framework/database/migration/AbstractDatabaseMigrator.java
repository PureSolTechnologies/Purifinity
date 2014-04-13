package com.puresoltechnologies.purifinity.framework.database.migration;

import java.io.IOException;

/**
 * This is the abstract base class of a database migrator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractDatabaseMigrator implements DatabaseMigrator {

	private final MigrationSequence sequence = new MigrationSequence();
	private final DatabaseMigrationConnector connector;

	protected AbstractDatabaseMigrator(DatabaseMigrationConnector connector) {
		super();
		this.connector = connector;
	}

	@Override
	public DatabaseMigrationConnector getConnector() {
		return connector;
	}

	public void migrate() throws IOException, MigrationException {
		getConnector().initialize();
		try {
			getConnector().startMigration();
			try {
				sequence.migrate(connector);
			} finally {
				getConnector().finishMigration();
			}
		} finally {
			getConnector().close();
		}
	}

	@Override
	public void registerMigrationStep(MigrationStep migrationStep) {
		sequence.registerMigrationStep(migrationStep);
	}
}
