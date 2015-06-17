package com.puresoltechnologies.purifinity.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Singleton;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.UserConfiguration;

@Singleton
public class UserConfigurationImpl implements UserConfiguration {

	private static final List<ConfigurationParameter<?>> DEFAULT_PARAMETERS = new ArrayList<ConfigurationParameter<?>>();

	@Override
	@Lock(LockType.READ)
	public List<ConfigurationParameter<?>> getDefaultParameters() {
		return DEFAULT_PARAMETERS;
	}

}
