package com.puresoltechnologies.purifinity.server.core.impl.preferences;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.repository.spi.Repository;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.UserConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesGroup;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesValue;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManager;
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

	@Inject
	private PurifinityConfiguration purifinityConfiguration;

	@Inject
	private UserConfiguration userConfiguration;

	@Inject
	private AnalyzerServiceManager analyzerServiceManager;

	@Inject
	private EvaluatorServiceManager evaluatorServiceManager;

	@Inject
	private RepositoryServiceManager repositoryServiceManager;

	@Override
	public List<ConfigurationParameter<?>> getSystemParameters() {
		return purifinityConfiguration.getParameters();
	}

	@Override
	public List<ConfigurationParameter<?>> getUserDefaultParameters() {
		return userConfiguration.getDefaultParameters();
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			String pluginId) {
		ProgrammingLanguageAnalyzer analyzer = analyzerServiceManager
				.getInstanceById(pluginId);
		if (analyzer != null) {
			return analyzer.getConfigurationParameters();
		}
		Evaluator evaluator = evaluatorServiceManager.getInstanceById(pluginId);
		if (evaluator != null) {
			return evaluator.getConfigurationParameters();
		}
		Repository repository = repositoryServiceManager
				.getInstanceById(pluginId);
		if (repository != null) {
			return repository.getConfigurationParameters();
		}
		return new ArrayList<>();
	}

	@Override
	public PreferencesValue getValue(PreferencesGroup group, String groupName,
			String key) {
		BoundStatement boundStatement;
		switch (group) {
		case SYSTEM:
			PreparedStatement preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"SELECT changed, changed_by, value FROM "
									+ CassandraElementNames.SYSTEM_PREFERENCES_TABLE
									+ " WHERE key=?;");
			boundStatement = preparedStatement.bind(key);
			break;
		case PLUGIN_DEFAULT:
			preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"SELECT changed, changed_by, value FROM "
									+ CassandraElementNames.PLUGIN_DEFAULTS_PREFERENCES_TABLE
									+ " WHERE key=?;");
			boundStatement = preparedStatement.bind(key);
			break;
		case PLUGIN_PROJECT:
			preparedStatement = preparedStatements.getPreparedStatement(
					session, "SELECT changed, changed_by, value FROM "
							+ CassandraElementNames.PLUGIN_PREFERENCES_TABLE
							+ " WHERE plugin_id=? AND key=?;");
			boundStatement = preparedStatement.bind(groupName, key);
			break;
		case USER:
			preparedStatement = preparedStatements.getPreparedStatement(
					session, "SELECT changed, changed_by, value FROM "
							+ CassandraElementNames.USER_PREFERENCES_TABLE
							+ " WHERE user_id=null AND key=?;");
			boundStatement = preparedStatement.bind(key);
			break;
		case USER_DEFAULT:
			preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"SELECT changed, changed_by, value FROM "
									+ CassandraElementNames.USER_DEFAULTS_PREFERENCES_TABLE
									+ " WHERE key=?;");
			boundStatement = preparedStatement.bind(groupName, key);
			break;
		default:
			throw new IllegalArgumentException("Unknown preferences group '"
					+ group.name() + "'.");
		}
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return null;
		}
		return new PreferencesValue(result.getDate(0), result.getString(1),
				group, groupName, key, result.getString(2));
	}

	@Override
	public PreferencesValue getValue(PreferencesGroup group, String groupName,
			String key, String defaultValue) {
		PreferencesValue value = getValue(group, groupName, key);
		if (value != null) {
			return value;
		}
		return new PreferencesValue(null, null, group, groupName, key,
				defaultValue);
	}

	@Override
	public void setValue(PreferencesGroup group, String groupName, String key,
			String value) {
		BoundStatement boundStatement;
		switch (group) {
		case SYSTEM:
			PreparedStatement preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"INSERT INTO "
									+ CassandraElementNames.SYSTEM_PREFERENCES_TABLE
									+ " (changed, changed_by, key, value) VALUES (?, ?, ?, ?);");
			boundStatement = preparedStatement.bind(new Date(), "n/a", key,
					value);
			break;
		case PLUGIN_DEFAULT:
			preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"INSERT INTO "
									+ CassandraElementNames.PLUGIN_DEFAULTS_PREFERENCES_TABLE
									+ " (changed, changed_by, key, value) VALUES (?, ?, ?, ?);");
			boundStatement = preparedStatement.bind(new Date(), "n/a", key,
					value);
			break;
		case PLUGIN_PROJECT:
			preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"INSERT INTO "
									+ CassandraElementNames.PLUGIN_PREFERENCES_TABLE
									+ " (changed, changed_by, plugin_id, key, value) VALUES (?, ?, ?, ?, ?);");
			boundStatement = preparedStatement.bind(new Date(), "n/a",
					groupName, key, value);
			break;
		case USER_DEFAULT:
			preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"INSERT INTO "
									+ CassandraElementNames.USER_DEFAULTS_PREFERENCES_TABLE
									+ " (changed, changed_by, key, value) VALUES (?, ?, ?, ?);");
			boundStatement = preparedStatement.bind(new Date(), "n/a", key,
					value);
			break;
		case USER:
			preparedStatement = preparedStatements
					.getPreparedStatement(
							session,
							"INSERT INTO "
									+ CassandraElementNames.USER_PREFERENCES_TABLE
									+ " (changed, changed_by, group_name, key, value) VALUES (?, ?, ?, ?, ?);");
			boundStatement = preparedStatement.bind(new Date(), "n/a",
					groupName, key, value);
			break;
		default:
			throw new IllegalArgumentException("Unknown preferences group '"
					+ group.name() + "'.");
		}
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
		logger.info("Wrote preference: '" + group + "/" + key + "'='" + value
				+ "'");
	}

	@Override
	public void setValue(PreferencesGroup group, String groupName, String key,
			boolean value) {
		setValue(group, groupName, key, String.valueOf(value));
	}

	@Override
	public boolean hasValue(PreferencesGroup group, String groupName, String key) {
		return getValue(group, groupName, key) != null;
	}

	@Override
	public Boolean getBoolean(PreferencesGroup group, String groupName,
			String key) {
		return Boolean.valueOf(getString(group, groupName, key));
	}

	@Override
	public boolean getBoolean(PreferencesGroup group, String groupName,
			String key, String defaultValue) {
		return Boolean.valueOf(getString(group, groupName, key, defaultValue));
	}

	@Override
	public String getString(PreferencesGroup group, String groupName, String key) {
		PreferencesValue value = getValue(group, groupName, key);
		if (value == null) {
			return null;
		}
		return value.getValue();
	}

	@Override
	public String getString(PreferencesGroup group, String groupName,
			String key, String defaultValue) {
		return getValue(group, groupName, key, defaultValue).getValue();
	}

	@Override
	public boolean isServiceActive(String serviceId) {
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
