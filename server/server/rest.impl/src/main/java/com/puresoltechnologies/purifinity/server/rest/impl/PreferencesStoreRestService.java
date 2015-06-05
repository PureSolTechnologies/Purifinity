package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
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
		preferencesStore.setSystemPreference(key, value);
	}

	@Override
	public String getSystemParameter(String key) {
		PreferencesValue<?> systemPreference = preferencesStore
				.getSystemPreference(key);
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
		preferencesStore.setPluginDefaultPreference(pluginId, key, value);
	}

	@Override
	public String getPluginDefaultParameter(String pluginId, String key) {
		PreferencesValue<?> pluginDefaultPreference = preferencesStore
				.getPluginDefaultPreference(pluginId, key);
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
		PreferencesValue<?> pluginProjectPreference = preferencesStore
				.getPluginProjectPreference(projectId, pluginId, key);
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
		preferencesStore.setPluginProjectPreference(projectId, pluginId, key,
				value);
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

}
