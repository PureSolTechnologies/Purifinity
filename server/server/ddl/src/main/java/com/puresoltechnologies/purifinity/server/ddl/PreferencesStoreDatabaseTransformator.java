package com.puresoltechnologies.purifinity.server.ddl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

public class PreferencesStoreDatabaseTransformator implements ComponentTransformator {

    private static final Logger logger = LoggerFactory.getLogger(PreferencesStoreDatabaseTransformator.class);

    private static final String SYSTEM_PREFERENCES_TABLE = "preferences_store_system_preferences";
    private static final String USER_PREFERENCES_TABLE = "preferences_store_user_preferences";
    private static final String USER_DEFAULTS_PREFERENCES_TABLE = "preferences_store_user_default_preferences";
    private static final String PLUGIN_PREFERENCES_TABLE = "preferences_store_plugin_preferences";
    private static final String PLUGIN_DEFAULTS_PREFERENCES_TABLE = "preferences_store_plugin_default_preferences";
    private static final String SERVICE_ACTIVATION_TABLE = "preferences_store_service_activation";
    private static final String SERVICE_PROJECT_ACTIVATION_TABLE = "preferences_store_service_project_activation";

    public static final String HBASE_HOST = "localhost";

    @Override
    public String getComponentName() {
	return "PreferencesStore";
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
	Version targetVersion = new Version(0, 3, 0);
	ProvidedVersionRange versionRange = new ProvidedVersionRange(targetVersion, null);
	SequenceMetadata metadata = new SequenceMetadata(getComponentName(), startVersion, versionRange);
	PhoenixTransformationSequence sequence = new PhoenixTransformationSequence(metadata, HBASE_HOST);

	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + SYSTEM_PREFERENCES_TABLE + " ("//
			+ "name varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "setting varchar, " //
			+ "CONSTRAINT " + SYSTEM_PREFERENCES_TABLE + "_PK PRIMARY KEY(name))",
		"Keeps preferences for the system wide settings."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + PLUGIN_DEFAULTS_PREFERENCES_TABLE + " ("//
			+ "plugin_id varchar not null, "//
			+ "name varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "setting varchar, " //
			+ "CONSTRAINT " + PLUGIN_DEFAULTS_PREFERENCES_TABLE + "_PK PRIMARY KEY(plugin_id, name))",
		"Keeps preferences for the project specific settings."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + PLUGIN_PREFERENCES_TABLE + " ("//
			+ "project_id varchar not null, "//
			+ "plugin_id varchar not null, "//
			+ "name varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "setting varchar, "//
			+ "CONSTRAINT " + PLUGIN_PREFERENCES_TABLE + "_PK PRIMARY KEY(project_id, plugin_id, name))",
		"Keeps preferences for the project specific settings."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + USER_DEFAULTS_PREFERENCES_TABLE + " ("//
			+ "name varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "setting varchar, "//
			+ "CONSTRAINT " + USER_DEFAULTS_PREFERENCES_TABLE + "_PK PRIMARY KEY(name))",
		"Keeps preferences for the user specific settings."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + USER_PREFERENCES_TABLE + " ("//
			+ "user_id varchar not null, "//
			+ "name varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "setting varchar, " + "CONSTRAINT " //
			+ USER_PREFERENCES_TABLE + "_PK PRIMARY KEY(user_id, name))",
		"Keeps preferences for the user specific settings."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + SERVICE_ACTIVATION_TABLE + " ("//
			+ "service_id varchar, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "activated boolean, "//
			+ "CONSTRAINT " + SERVICE_ACTIVATION_TABLE + "_PK PRIMARY KEY(service_id))",
		"Keeps the activation states of services."));
	sequence.appendTransformation(new PhoenixTransformationStep(sequence, "Rick-Rainer Ludwig",
		"CREATE TABLE " + SERVICE_PROJECT_ACTIVATION_TABLE + " ("//
			+ "project_id varchar not null, "//
			+ "service_id varchar not null, "//
			+ "changed timestamp, "//
			+ "changed_by varchar, "//
			+ "activated boolean, "//
			+ "CONSTRAINT " + SERVICE_PROJECT_ACTIVATION_TABLE + "_PK PRIMARY KEY(project_id, service_id))",
		"Keeps the activation states of services."));

	return sequence;
    }

    @Override
    public void dropAll() {
	try (Connection connection = DriverManager.getConnection("jdbc:phoenix:" + HBASE_HOST);) {
	    try (Statement statement = connection.createStatement();) {
		statement.execute("DROP TABLE IF EXISTS " + SYSTEM_PREFERENCES_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + USER_PREFERENCES_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + USER_DEFAULTS_PREFERENCES_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + PLUGIN_PREFERENCES_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + PLUGIN_DEFAULTS_PREFERENCES_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + SERVICE_ACTIVATION_TABLE);
		statement.execute("DROP TABLE IF EXISTS " + SERVICE_PROJECT_ACTIVATION_TABLE);
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
