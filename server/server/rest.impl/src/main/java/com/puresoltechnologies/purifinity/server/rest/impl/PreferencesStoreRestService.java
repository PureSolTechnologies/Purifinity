package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.common.utils.data.TypeWrapper;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesValue;
import com.puresoltechnologies.purifinity.server.rest.api.PreferencesStoreRestInterface;

public class PreferencesStoreRestService implements
		PreferencesStoreRestInterface {

	@Inject
	private PreferencesStore preferencesStore;

	@Override
	public List<ConfigurationParameter<?>> getSystemParameters() {
		return preferencesStore.getSystemParameters();
	}

	@Override
	public void setSystemParameter(String key, String value) {
		@SuppressWarnings("unchecked")
		ConfigurationParameter<Object> configurationParameter = (ConfigurationParameter<Object>) findConfigurationParameter(key);
		Object o = TypeWrapper.convertFromString(
				configurationParameter.getType(), value);
		preferencesStore.setSystemPreference(configurationParameter, o);
	}

	@Override
	public String getSystemParameter(String key) {
		@SuppressWarnings("unchecked")
		ConfigurationParameter<Object> configurationParameter = (ConfigurationParameter<Object>) findConfigurationParameter(key);
		PreferencesValue<?> systemPreference = preferencesStore
				.getSystemPreference(configurationParameter);
		if (systemPreference == null) {
			return null;
		}
		return systemPreference.getValue().toString();
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			String pluginId) {
		return preferencesStore.getPluginDefaultParameters(pluginId);
	}

	@Override
	public void setPluginDefaultParameter(String pluginId, String key,
			String value) {
		@SuppressWarnings("unchecked")
		ConfigurationParameter<Object> configurationParameter = (ConfigurationParameter<Object>) findPluginDefaultConfigurationParameter(
				pluginId, key);
		Object o = TypeWrapper.convertFromString(
				configurationParameter.getType(), value);
		preferencesStore.setPluginDefaultPreference(pluginId,
				configurationParameter, o);
	}

	@Override
	public String getPluginDefaultParameter(String pluginId, String key) {
		@SuppressWarnings("unchecked")
		ConfigurationParameter<Object> configurationParameter = (ConfigurationParameter<Object>) findPluginDefaultConfigurationParameter(
				pluginId, key);
		PreferencesValue<?> pluginDefaultPreference = preferencesStore
				.getPluginDefaultPreference(pluginId, configurationParameter);
		if (pluginDefaultPreference == null) {
			return null;
		}
		return pluginDefaultPreference.getValue().toString();
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			String projectId, String pluginId) {
		return preferencesStore.getPluginProjectParameters(projectId, pluginId);
	}

	@Override
	public String getPluginProjectParameter(String projectId, String pluginId,
			String key) {
		@SuppressWarnings("unchecked")
		ConfigurationParameter<Object> configurationParameter = (ConfigurationParameter<Object>) findPluginProjectConfigurationParameter(
				projectId, pluginId, key);
		PreferencesValue<?> pluginProjectPreference = preferencesStore
				.getPluginProjectPreference(projectId, pluginId,
						configurationParameter);
		if (pluginProjectPreference == null) {
			return null;
		}
		return pluginProjectPreference.getValue().toString();
	}

	@Override
	public void deletePluginProjectParameter(String projectId, String pluginId,
			String key) {
		preferencesStore.deletePluginProjectParameter(projectId, pluginId, key);
	}

	@Override
	public void setPluginProjectParameter(String projectId, String pluginId,
			String key, String value) {
		@SuppressWarnings("unchecked")
		ConfigurationParameter<Object> configurationParameter = (ConfigurationParameter<Object>) findPluginProjectConfigurationParameter(
				projectId, pluginId, key);
		Object o = TypeWrapper.convertFromString(
				configurationParameter.getType(), value);
		preferencesStore.setPluginProjectPreference(projectId, pluginId,
				configurationParameter, o);
	}

	@Override
	public List<ConfigurationParameter<?>> getUserParameters(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserParameter(String key, String userId, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUserParameter(String key, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationParameter<?>> getUserDefaultParameters() {
		return preferencesStore.getUserDefaultParameters();
	}

	@Override
	public void setUserDefaultParameter(String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUserDefaultParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	private ConfigurationParameter<?> findConfigurationParameter(String key) {
		ConfigurationParameter<?> parameter = null;
		for (ConfigurationParameter<?> configurationParameter : preferencesStore
				.getSystemParameters()) {
			if (configurationParameter.getPropertyKey().equals(key)) {
				parameter = configurationParameter;
			}
		}
		return parameter;
	}

	private ConfigurationParameter<?> findPluginDefaultConfigurationParameter(
			String pluginId, String key) {
		ConfigurationParameter<?> parameter = null;
		for (ConfigurationParameter<?> configurationParameter : preferencesStore
				.getPluginDefaultParameters(pluginId)) {
			if (configurationParameter.getPropertyKey().equals(key)) {
				parameter = configurationParameter;
			}
		}
		return parameter;
	}

	private ConfigurationParameter<?> findPluginProjectConfigurationParameter(
			String projectId, String pluginId, String key) {
		ConfigurationParameter<?> parameter = null;
		for (ConfigurationParameter<?> configurationParameter : preferencesStore
				.getPluginProjectParameters(projectId, pluginId)) {
			if (configurationParameter.getPropertyKey().equals(key)) {
				parameter = configurationParameter;
			}
		}
		return parameter;
	}

}
