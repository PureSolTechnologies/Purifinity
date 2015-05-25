package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesGroup;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
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
	public void setSystemParameter(String property, String value) {
		preferencesStore.setValue(PreferencesGroup.SYSTEM, "", property, value);
	}

	@Override
	public String getSystemParameter(String property) {
		return preferencesStore
				.getString(PreferencesGroup.SYSTEM, "", property);
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			String pluginId) {
		return preferencesStore.getPluginDefaultParameters(pluginId);
	}

	@Override
	public void setPluginDefaultParameter(String pluginId, String property,
			String value) {
		preferencesStore.setValue(PreferencesGroup.PLUGIN_DEFAULT, pluginId,
				property, value);
	}

	@Override
	public String getPluginDefaultParameter(String pluginId, String property) {
		return preferencesStore.getString(PreferencesGroup.PLUGIN_DEFAULT,
				pluginId, property);
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPluginProjectParameter(String pluginId, String projectId,
			String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPluginDefaultParameter(String pluginId, String projectId,
			String property, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ConfigurationParameter<?>> getUserParameters(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserParameter(String property, String userId, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUserParameter(String property, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationParameter<?>> getUserDefaultParameters() {
		return preferencesStore.getUserDefaultParameters();
	}

	@Override
	public void setUserDefaultParameter(String property, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getUserDefaultParameter(String property) {
		// TODO Auto-generated method stub
		return null;
	}

}
