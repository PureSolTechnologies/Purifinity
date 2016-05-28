
package com.puresoltechnologies.purifinity.server.systemmonitor.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.genesis.commons.ProvidedVersionRange;
import com.puresoltechnologies.genesis.commons.SequenceMetadata;
import com.puresoltechnologies.genesis.transformation.phoenix.PhoenixTransformationSequence;
import com.puresoltechnologies.genesis.transformation.phoenix.PhoenixTransformationStep;
import com.puresoltechnologies.genesis.transformation.spi.ComponentTransformator;
import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.versioning.Version;

public class SystemMonitorDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(SystemMonitorDatabaseTransformator.class);

    private static final String HBASE_HOST = "localhost";

    private static final String EVENTS_TABLE_NAME = "system_monitor.events";
    private static final String METRICS_TABLE_NAME = "system_monitor.metrics";

    @Override
    public String getComponentName() {
	return "SystemMonitor";
    }

    @Override
    public Set<String> getDependencies() {
	return Collections.emptySet();
    }

    @Override
    public boolean isHostBased() {
	return false;
    }

    @Override
    public Set<TransformationSequence> getSequences() {
	Set<TransformationSequence> sequences = new HashSet<>();
	sequences.add(migrateVersion0_4_0());
	return sequences;
    }

    private TransformationSequence migrateVersion0_4_0() {
	Version startVersion = new Version(0, 0, 0);
	Version targetVersion = new Version(0, 4, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata, HBASE_HOST);

	String description = "This is the table for the event log.";
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE IF NOT EXISTS " + EVENTS_TABLE_NAME //
			+ " (" //
			+ "server varchar not null, " //
			+ "time timestamp not null, " //
			+ "severity varchar not null, "//
			+ "type varchar not null, " //
			+ "component varchar not null, " //
			+ "event_id bigint not null, " //
			+ "message varchar not null, "//
			+ "user varchar, " //
			+ "user_id bigint," //
			+ "client varchar, " //
			+ "exception_message varchar, " //
			+ "exception_stacktrace varchar, "//
			+ "CONSTRAINT " + EVENTS_TABLE_NAME.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY (server, time, severity, type, component, event_id, message))",
		description));

	description = "This is the table for metrics and KPIs.";
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE IF NOT EXISTS " + METRICS_TABLE_NAME //
			+ " ("//
			+ "server varchar not null," //
			+ "time timestamp not null, " //
			+ "name varchar not null," //
			+ "unit varchar, " //
			+ "type varchar, " //
			+ "description varchar, "//
			+ "decimal_value decimal, "//
			+ "integer_value bigint, "//
			+ "level_of_measurement varchar, "//
			+ "CONSTRAINT " + METRICS_TABLE_NAME.replaceAll("\\.", "_")
			+ "_PK PRIMARY KEY (server, time, name))",
		description));
	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DriverManager.getConnection("jdbc:phoenix:" + HBASE_HOST);) {
	    try (Statement statement = connection.createStatement();) {
		statement.execute("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
		statement.execute("DROP TABLE IF EXISTS " + METRICS_TABLE_NAME);
		connection.commit();
	    } catch (SQLException e) {
		try {
		    connection.rollback();
		} catch (SQLException e1) {
		    logger.warn("Cannot rollback.", e);
		}
		throw new RuntimeException("Could not drop component tables.", e);
	    }
	} catch (SQLException e2) {
	    throw new RuntimeException("Could not open Phoenix connection to HBase.", e2);
	}
    }
}
