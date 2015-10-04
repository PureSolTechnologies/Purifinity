package com.puresoltechnologies.purifinity.server.core.impl.preferences;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;

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
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseElementNames;

public class PreferencesStoreImpl implements PreferencesStore {

    @Inject
    private Logger logger;

    @Inject
    @PreferencesStoreConnection
    private Connection connection;

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
    public List<ConfigurationParameter<?>> getPluginProjectParameters(String projectId, String pluginId) {
	return getPluginDefaultParameters(pluginId);
    }

    @Override
    public List<ConfigurationParameter<?>> getPluginDefaultParameters(String pluginId) {
	ProgrammingLanguageAnalyzer analyzer = analyzerServiceManager.getInstanceById(pluginId);
	if (analyzer != null) {
	    return analyzer.getConfigurationParameters();
	}
	Evaluator evaluator = evaluatorServiceManager.getInstanceById(pluginId);
	if (evaluator != null) {
	    return evaluator.getConfigurationParameters();
	}
	Repository repository = repositoryServiceManager.getInstanceById(pluginId);
	if (repository != null) {
	    return repository.getConfigurationParameters();
	}
	return new ArrayList<>();
    }

    @Override
    public <T> PreferencesValue<T> getSystemPreference(ConfigurationParameter<T> configurationParameter) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT changed, changed_by, setting FROM "
			+ HBaseElementNames.SYSTEM_PREFERENCES_TABLE + " WHERE name=?")) {
	    preparedStatement.setString(1, configurationParameter.getPropertyKey());
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return new PreferencesValue<>(null, null, PreferencesGroup.SYSTEM, "",
			    configurationParameter.getPropertyKey(), configurationParameter.getDefaultValue());
		}
		return PreferencesValue.create(configurationParameter.getType(), resultSet.getDate(1),
			resultSet.getString(2), PreferencesGroup.SYSTEM, "", configurationParameter.getPropertyKey(),
			resultSet.getString(3));
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Could not read system preference.", e);
	}
    }

    @Override
    public <T> void setSystemPreference(ConfigurationParameter<T> parameter, T value) {
	setSystemPreferenceDB(parameter, value.toString());
    }

    private void setSystemPreferenceDB(ConfigurationParameter<?> parameter, String value) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.SYSTEM_PREFERENCES_TABLE
			+ " (changed, changed_by, name, setting) VALUES (?, ?, ?, ?)")) {
	    preparedStatement.setTime(1, new Time(new Date().getTime()));
	    preparedStatement.setString(2, "n/a");
	    preparedStatement.setString(3, parameter.getPropertyKey());
	    preparedStatement.setString(4, value);
	    preparedStatement.execute();
	    connection.commit();
	    logger.info("Wrote system preference: '" + parameter.getPropertyKey() + "'='" + value + "' (" + parameter
		    + ")");
	    PreferencesValue<?> preferenceValue = PreferencesValue.create(parameter.getType(), null, null,
		    PreferencesGroup.SYSTEM, "", parameter.getPropertyKey(), value);
	    event.fire(new SystemPreferenceChangeEvent(parameter, preferenceValue.getValue()));
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback system preference change.", e1);
	    }
	    throw new RuntimeException("Could not change system preference.", e);
	}
    }

    @Override
    public boolean hasSystemPreference(ConfigurationParameter<?> configurationParameter) {
	return getSystemPreference(configurationParameter) != null;
    }

    @Override
    public <T> PreferencesValue<T> getPluginDefaultPreference(String pluginId,
	    ConfigurationParameter<T> configurationParameter) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("SELECT changed, changed_by, setting FROM "
			+ HBaseElementNames.PLUGIN_DEFAULTS_PREFERENCES_TABLE + " WHERE plugin_id=? AND name=?")) {
	    preparedStatement.setString(1, pluginId);
	    preparedStatement.setString(2, configurationParameter.getPropertyKey());
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return new PreferencesValue<>(null, null, PreferencesGroup.SYSTEM, "",
			    configurationParameter.getPropertyKey(), configurationParameter.getDefaultValue());
		}
		return PreferencesValue.create(configurationParameter.getType(), resultSet.getDate(1),
			resultSet.getString(2), PreferencesGroup.PLUGIN_DEFAULT, "",
			configurationParameter.getPropertyKey(), resultSet.getString(3));
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Could not read plugin default.", e);
	}
    }

    @Override
    public <T> void setPluginDefaultPreference(String pluginId, ConfigurationParameter<T> configurationParameter,
	    T value) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.PLUGIN_DEFAULTS_PREFERENCES_TABLE
			+ " (changed, changed_by, plugin_id, name, setting) VALUES (?, ?, ?, ?, ?)")) {
	    preparedStatement.setTime(1, new Time(new Date().getTime()));
	    preparedStatement.setString(2, "n/a");
	    preparedStatement.setString(3, pluginId);
	    preparedStatement.setString(4, configurationParameter.getPropertyKey());
	    preparedStatement.setString(5, value.toString());
	    preparedStatement.execute();
	    connection.commit();
	    logger.info("Wrote plugin default preference: '" + pluginId + "/" + configurationParameter.getPropertyKey()
		    + "'='" + value + "'");
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback plugin default change.", e1);
	    }
	    throw new RuntimeException("Could not set plugin default.", e);
	}
    }

    @Override
    public boolean hasPluginDefaultPreference(String pluginId, ConfigurationParameter<?> configurationParameter) {
	return getPluginDefaultPreference(pluginId, configurationParameter) != null;
    }

    @Override
    public <T> PreferencesValue<T> getPluginProjectPreference(String projectId, String pluginId,
	    ConfigurationParameter<T> configurationParameter) {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT changed, changed_by, setting FROM " + HBaseElementNames.PLUGIN_PREFERENCES_TABLE
			+ " WHERE project_id=? AND plugin_id=? AND name=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setString(2, pluginId);
	    preparedStatement.setString(3, configurationParameter.getPropertyKey());
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return new PreferencesValue<>(null, null, PreferencesGroup.SYSTEM, "",
			    configurationParameter.getPropertyKey(), configurationParameter.getDefaultValue());
		}
		return PreferencesValue.create(configurationParameter.getType(), resultSet.getDate(1),
			resultSet.getString(2), PreferencesGroup.PLUGIN_DEFAULT, "",
			configurationParameter.getPropertyKey(), resultSet.getString(3));
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Could not read plugin project preference.", e);
	}
    }

    @Override
    public void deletePluginProjectParameter(String projectId, String pluginId, String key) {
	try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "
		+ HBaseElementNames.PLUGIN_PREFERENCES_TABLE + " WHERE project_id=? AND plugin_id=? AND name=?")) {
	    preparedStatement.setString(1, projectId);
	    preparedStatement.setString(2, pluginId);
	    preparedStatement.setString(3, key);
	    preparedStatement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback plugin project preference deletion.", e1);
	    }
	    throw new RuntimeException("Could not delete plugin project preference.", e);
	}
    }

    @Override
    public <T> void setPluginProjectPreference(String projectId, String pluginId,
	    ConfigurationParameter<T> configurationParameter, T value) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO " + HBaseElementNames.PLUGIN_PREFERENCES_TABLE
			+ " (changed, changed_by, project_id, plugin_id, name, setting) VALUES (?, ?, ?, ?, ?, ?)")) {
	    preparedStatement.setTime(1, new Time(new Date().getTime()));
	    preparedStatement.setString(2, "n/a");
	    preparedStatement.setString(3, projectId);
	    preparedStatement.setString(4, pluginId);
	    preparedStatement.setString(5, configurationParameter.getPropertyKey());
	    preparedStatement.setString(6, value.toString());
	    preparedStatement.execute();
	    connection.commit();
	    logger.info("Wrote plugin project preference: '" + projectId + "/" + pluginId + "/"
		    + configurationParameter.getPropertyKey() + "'='" + value + "'");
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback plugin project preference change.", e1);
	    }
	    throw new RuntimeException("Could not set plugin project preference.", e);
	}
    }

    @Override
    public boolean hasPluginProjectPreference(String projectId, String pluginId,
	    ConfigurationParameter<?> configurationParameter) {
	return getPluginProjectPreference(pluginId, projectId, configurationParameter) != null;
    }

    @Override
    public boolean isServiceActive(String serviceId) {
	try (PreparedStatement preparedStatement = connection.prepareStatement(
		"SELECT activated FROM " + HBaseElementNames.SERVICE_ACTIVATION_TABLE + " where service_id=?")) {
	    preparedStatement.setString(1, serviceId);
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
		if (!resultSet.next()) {
		    return false;
		}
		return resultSet.getBoolean(1);
	    }
	} catch (SQLException e) {
	    throw new RuntimeException("Could not read service activation.", e);
	}
    }

    @Override
    public void setServiceActive(String serviceId, boolean active) {
	try (PreparedStatement preparedStatement = connection
		.prepareStatement("UPSERT INTO  " + HBaseElementNames.SERVICE_ACTIVATION_TABLE
			+ " (changed, changed_by, service_id, activated) VALUES (?, ?, ?, ?)")) {
	    preparedStatement.setTime(1, new Time(new Date().getTime()));
	    preparedStatement.setString(2, "n/a");
	    preparedStatement.setString(3, serviceId);
	    preparedStatement.setBoolean(4, active);
	    preparedStatement.execute();
	    connection.commit();
	    logger.info("Set service to active=" + active);
	} catch (SQLException e) {
	    try {
		connection.rollback();
	    } catch (SQLException e1) {
		logger.warn("Could not rollback service activation.", e1);
	    }
	    throw new RuntimeException("Could not set service activation.", e);
	}
    }
}
