package com.puresoltechnologies.purifinity.server.database.migration;

import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

/**
 * This is the abstract base class of a database migrator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractUniversalMigrator implements UniversalMigrator {

	private final MigrationSequence sequence = new MigrationSequence();
	private final UniversalMigratorConnector connector;

	private final UniversalMigratorTracker tracker;

	protected AbstractUniversalMigrator(UniversalMigratorConnector connector) {
		super();
		this.connector = connector;
		ServiceLoader<UniversalMigratorTracker> trackerServices = ServiceLoader
				.load(UniversalMigratorTracker.class);
		Iterator<UniversalMigratorTracker> iterator = trackerServices
				.iterator();
		if (!iterator.hasNext()) {
			throw new IllegalStateException(
					"No tracker for universal migrator found.");
		}
		tracker = iterator.next();
		if (iterator.hasNext()) {
			throw new IllegalStateException(
					"Multiple trackers for universal migrator found.");
		}
	}

	@Override
	public UniversalMigratorConnector getConnector() {
		return connector;
	}

	@Override
	public void migrate() throws IOException, MigrationException {
		tracker.open();
		try {
			getConnector().initialize();
			try {
				getConnector().startMigration();
				try {
					sequence.migrate(tracker, connector);
				} finally {
					getConnector().finishMigration();
				}
			} finally {
				getConnector().close();
			}
		} finally {
			tracker.close();
		}
	}

	@Override
	public void registerMigrationStep(MigrationStep migrationStep) {
		sequence.registerMigrationStep(migrationStep);
	}
}
