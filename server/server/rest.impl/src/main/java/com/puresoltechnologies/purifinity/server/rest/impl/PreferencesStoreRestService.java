package com.puresoltechnologies.purifinity.server.rest.impl;

import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.UserConfiguration;
import com.puresoltechnologies.purifinity.server.rest.api.PreferencesStoreRestInterface;

public class PreferencesStoreRestService implements
		PreferencesStoreRestInterface {

	@Inject
	private PurifinityConfiguration purifinityConfiguration;

	@Inject
	private UserConfiguration userConfiguration;

	@Override
	public List<ConfigurationParameter<?>> getSystemParameters() {
		return purifinityConfiguration.getParameters();
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			String pluginId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			String projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationParameter<?>> getUserParameters(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConfigurationParameter<?>> getUserDefaultParameters() {
		return userConfiguration.getDefaultParameters();
	}

}
