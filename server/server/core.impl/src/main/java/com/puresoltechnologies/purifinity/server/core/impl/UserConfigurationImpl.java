package com.puresoltechnologies.purifinity.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.UserConfiguration;

public class UserConfigurationImpl implements UserConfiguration {

	private static final List<ConfigurationParameter<?>> DEFAULT_PARAMETERS = new ArrayList<ConfigurationParameter<?>>();

	@Override
	public List<ConfigurationParameter<?>> getDefaultParameters() {
		return DEFAULT_PARAMETERS;
	}

}
