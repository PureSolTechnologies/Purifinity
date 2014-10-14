package com.puresoltechnologies.purifinity.server.database.migration;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorTracker;

public final class Migrator implements AutoCloseable {

    private static final Logger logger = LoggerFactory
	    .getLogger(Migrator.class);

    private final UniversalMigratorTracker tracker;

    public Migrator() throws MigrationException {
	super();
	ServiceLoader<UniversalMigratorTracker> trackerServices = ServiceLoader
		.load(UniversalMigratorTracker.class);
	Iterator<UniversalMigratorTracker> iterator = trackerServices
		.iterator();
	if (!iterator.hasNext()) {
	    throw new IllegalStateException(
		    "No tracker for universal migrator found.");
	}
	tracker = iterator.next();
	logger.info("Found migration tracker '" + tracker.getClass().getName()
		+ "'.");
	if (iterator.hasNext()) {
	    logger.error("Found another migration tracker '"
		    + tracker.getClass().getName() + "'!");
	    throw new IllegalStateException(
		    "Multiple trackers for universal migrator found.");
	}
	tracker.open();
    }

    @Override
    public void close() throws Exception {
	tracker.close();
    }

    public void runMigration(MigrationSequence sequence)
	    throws MigrationException {
	MigrationMetadata metadata = sequence.getMetadata();
	logMigrationStart(metadata);
	if (!tracker.wasMigrated(metadata.getVersion(),
		metadata.getComponent(), metadata.getCommand())) {
	    for (MigrationStep step : sequence.getMigrationSteps()) {
		runMigration(step);
	    }
	    tracker.trackMigration(metadata.getVersion(),
		    metadata.getDeveloper(), metadata.getComponent(),
		    metadata.getCommand(), metadata.getComment());
	} else {
	    logMigrationSkip(metadata);
	}
    }

    public void runMigration(MigrationStep step) throws MigrationException {
	MigrationMetadata metadata = step.getMetadata();
	logMigrationStart(metadata);
	if (!tracker.wasMigrated(metadata.getVersion(),
		metadata.getComponent(), metadata.getCommand())) {
	    step.migrate();
	    tracker.trackMigration(metadata.getVersion(),
		    metadata.getDeveloper(), metadata.getComponent(),
		    metadata.getCommand(), metadata.getComment());
	} else {
	    logMigrationSkip(metadata);
	}
    }

    private void logMigrationStart(MigrationMetadata metadata) {
	logger.info("Start migration by " + metadata.getDeveloper() + ": '"
		+ metadata.getCommand() + "' in component "
		+ metadata.getComponent() + " " + metadata.getVersion()
		+ " (comment: " + metadata.getComment() + ")");
    }

    private void logMigrationSkip(MigrationMetadata metadata) {
	logger.info("Skip migration by " + metadata.getDeveloper() + ": '"
		+ metadata.getCommand() + "' " + metadata.getComponent() + " "
		+ metadata.getVersion());
    }
}
