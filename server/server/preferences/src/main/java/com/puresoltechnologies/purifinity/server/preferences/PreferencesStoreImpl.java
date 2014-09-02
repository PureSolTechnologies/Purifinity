package com.puresoltechnologies.purifinity.server.preferences;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraElementNames;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraPreparedStatements;

public class PreferencesStoreImpl implements PreferencesStore {

	@Inject
	private Logger logger;

	@Inject
	private CassandraPreparedStatements preparedStatements;

	@Inject
	@PreferencesStoreKeyspace
	private Session session;

	@Override
	public PreferencesValue getValue(String group, String name) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session,
						"SELECT changed, changed_by, value FROM "
								+ CassandraElementNames.PREFERENCES_TABLE
								+ " WHERE group=? AND name=?;");
		BoundStatement boundStatement = preparedStatement.bind(group, name);
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		PreferencesValue value = new PreferencesValue(result.getDate(0),
				result.getString(1), group, name, result.getString(2));
		logger.info("Read preference: " + value.toString());
		return value;
	}

	@Override
	public PreferencesValue getValue(String group, String name,
			String defaultValue) {
		PreferencesValue value = getValue(group, name);
		if (value != null) {
			return value;
		}
		return new PreferencesValue(null, null, group, name, defaultValue);
	}

	@Override
	public void setValue(String group, String name, String value) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.PREFERENCES_TABLE
								+ " (changed, changed_by, group, name, value) VALUES (?, ?, ?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(new Date(),
				"n/a", group, name, value);
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
		logger.info("Wrote preference: '" + group + "/" + name + "'='" + value
				+ "'");
	}

	@Override
	public void setValue(String group, String name, boolean value) {
		setValue(group, name, String.valueOf(value));
	}

	@Override
	public boolean hasValue(String group, String name) {
		return getValue(group, name) != null;
	}

	@Override
	public Boolean getBoolean(String group, String name) {
		return Boolean.valueOf(getString(group, name));
	}

	@Override
	public boolean getBoolean(String group, String name, String defaultValue) {
		return Boolean.valueOf(getString(group, name, defaultValue));
	}

	@Override
	public String getString(String group, String name) {
		return getValue(group, name).getValue();
	}

	@Override
	public String getString(String group, String name, String defaultValue) {
		return getValue(group, name, defaultValue).getValue();
	}

	@Override
	public boolean isActive(String serviceId) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session, "SELECT active FROM "
						+ CassandraElementNames.SERVICE_ACTIVATION_TABLE
						+ " where service_id=?;");
		BoundStatement boundStatement = preparedStatement.bind(serviceId);
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		ResultSet result = session.execute(boundStatement);
		if (result.isExhausted()) {
			return false;
		}
		return result.one().getBool(0);
	}

	@Override
	public void setServiceActive(String serviceId, boolean active) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO  "
								+ CassandraElementNames.SERVICE_ACTIVATION_TABLE
								+ " (changed, changed_by, service_id, active) VALUES (?, ?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(new Date(),
				"n/a", serviceId, active);
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
		logger.info("Set service to active=" + active);
	}
}
