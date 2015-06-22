package com.puresoltechnologies.purifinity.server.core.impl.preferences;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
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
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChange;
import com.puresoltechnologies.purifinity.server.core.api.preferences.SystemPreferenceChangeEvent;
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

	@Inject
	@SystemPreferenceChange
	private Event<SystemPreferenceChangeEvent> event;

	@Override
	public List<ConfigurationParameter<?>> getSystemParameters() {
		return purifinityConfiguration.getParameters();
	}

	@Override
	public List<ConfigurationParameter<?>> getUserDefaultParameters() {
		return userConfiguration.getDefaultParameters();
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			String projectId, String pluginId) {
		return getPluginDefaultParameters(pluginId);
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
	public <T> PreferencesValue<T> getSystemPreference(
			ConfigurationParameter<T> configurationParameter) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"SELECT changed, changed_by, value FROM "
								+ CassandraElementNames.SYSTEM_PREFERENCES_TABLE
								+ " WHERE key=?;");
		BoundStatement boundStatement = preparedStatement
				.bind(configurationParameter.getPropertyKey());
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return new PreferencesValue<>(null, null, PreferencesGroup.SYSTEM,
					"", configurationParameter.getPropertyKey(),
					configurationParameter.getDefaultValue());
		}
		return PreferencesValue.create(configurationParameter.getType(),
				result.getDate(0), result.getString(1),
				PreferencesGroup.SYSTEM, "",
				configurationParameter.getPropertyKey(), result.getString(2));
	}

	@Override
	public <T> void setSystemPreference(ConfigurationParameter<T> parameter,
			T value) {
		setSystemPreferenceDB(parameter, value.toString());
	}

	private void setSystemPreferenceDB(ConfigurationParameter<?> parameter,
			String value) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.SYSTEM_PREFERENCES_TABLE
								+ " (changed, changed_by, key, value) VALUES (?, ?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(new Date(),
				"n/a", parameter.getPropertyKey(), value);
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
		logger.info("Wrote system preference: '" + parameter.getPropertyKey()
				+ "'='" + value + "' (" + parameter + ")");
		PreferencesValue<?> preferenceValue = PreferencesValue.create(
				parameter.getType(), null, null, PreferencesGroup.SYSTEM, "",
				parameter.getPropertyKey(), value);
		event.fire(new SystemPreferenceChangeEvent(parameter, preferenceValue
				.getValue()));
	}

	@Override
	public boolean hasSystemPreference(
			ConfigurationParameter<?> configurationParameter) {
		return getSystemPreference(configurationParameter) != null;
	}

	@Override
	public <T> PreferencesValue<T> getPluginDefaultPreference(String pluginId,
			ConfigurationParameter<T> configurationParameter) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"SELECT changed, changed_by, value FROM "
								+ CassandraElementNames.PLUGIN_DEFAULTS_PREFERENCES_TABLE
								+ " WHERE plugin_id=? AND key=?;");
		BoundStatement boundStatement = preparedStatement.bind(pluginId,
				configurationParameter.getPropertyKey());
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return new PreferencesValue<>(null, null, PreferencesGroup.SYSTEM,
					"", configurationParameter.getPropertyKey(),
					configurationParameter.getDefaultValue());
		}
		return PreferencesValue.create(configurationParameter.getType(),
				result.getDate(0), result.getString(1),
				PreferencesGroup.PLUGIN_DEFAULT, "",
				configurationParameter.getPropertyKey(), result.getString(2));
	}

	@Override
	public <T> void setPluginDefaultPreference(String pluginId,
			ConfigurationParameter<T> configurationParameter, T value) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.PLUGIN_DEFAULTS_PREFERENCES_TABLE
								+ " (changed, changed_by, plugin_id, key, value) VALUES (?, ?, ?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(new Date(),
				"n/a", pluginId, configurationParameter.getPropertyKey(),
				value.toString());
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
		logger.info("Wrote plugin default preference: '" + pluginId + "/"
				+ configurationParameter.getPropertyKey() + "'='" + value + "'");
	}

	@Override
	public boolean hasPluginDefaultPreference(String pluginId,
			ConfigurationParameter<?> configurationParameter) {
		return getPluginDefaultPreference(pluginId, configurationParameter) != null;
	}

	@Override
	public <T> PreferencesValue<T> getPluginProjectPreference(String projectId,
			String pluginId, ConfigurationParameter<T> configurationParameter) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"SELECT changed, changed_by, value FROM "
								+ CassandraElementNames.PLUGIN_PREFERENCES_TABLE
								+ " WHERE project_id=? AND plugin_id=? AND key=?;");
		BoundStatement boundStatement = preparedStatement.bind(projectId,
				pluginId, configurationParameter.getPropertyKey());
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		ResultSet resultSet = session.execute(boundStatement);
		Row result = resultSet.one();
		if (result == null) {
			return new PreferencesValue<>(null, null, PreferencesGroup.SYSTEM,
					"", configurationParameter.getPropertyKey(),
					configurationParameter.getDefaultValue());
		}
		return PreferencesValue.create(configurationParameter.getType(),
				result.getDate(0), result.getString(1),
				PreferencesGroup.PLUGIN_DEFAULT, "",
				configurationParameter.getPropertyKey(), result.getString(2));
	}

	@Override
	public void deletePluginProjectParameter(String projectId, String pluginId,
			String key) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(session, "DELETE FROM "
						+ CassandraElementNames.PLUGIN_PREFERENCES_TABLE
						+ " WHERE project_id=? AND plugin_id=? AND key=?;");
		BoundStatement boundStatement = preparedStatement.bind(projectId,
				pluginId, key);
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
	}

	@Override
	public <T> void setPluginProjectPreference(String projectId,
			String pluginId, ConfigurationParameter<T> configurationParameter,
			T value) {
		PreparedStatement preparedStatement = preparedStatements
				.getPreparedStatement(
						session,
						"INSERT INTO "
								+ CassandraElementNames.PLUGIN_PREFERENCES_TABLE
								+ " (changed, changed_by, project_id, plugin_id, key, value) VALUES (?, ?, ?, ?, ?, ?);");
		BoundStatement boundStatement = preparedStatement.bind(new Date(),
				"n/a", projectId, pluginId,
				configurationParameter.getPropertyKey(), value.toString());
		boundStatement.setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
		session.execute(boundStatement);
		logger.info("Wrote plugin project preference: '" + projectId + "/"
				+ pluginId + "/" + configurationParameter.getPropertyKey()
				+ "'='" + value + "'");
	}

	@Override
	public boolean hasPluginProjectPreference(String projectId,
			String pluginId, ConfigurationParameter<?> configurationParameter) {
		return getPluginProjectPreference(pluginId, projectId,
				configurationParameter) != null;
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
