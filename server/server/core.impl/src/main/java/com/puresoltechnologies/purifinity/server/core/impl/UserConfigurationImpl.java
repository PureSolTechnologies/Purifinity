package com.puresoltechnologies.purifinity.server.core.impl;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Singleton;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.UserConfiguration;

@Singleton
public class UserConfigurationImpl implements UserConfiguration {

    private static final ConfigurationParameter<?>[] DEFAULT_PARAMETERS = new ConfigurationParameter<?>[] {};

    @Override
    @Lock(LockType.READ)
    public ConfigurationParameter<?>[] getDefaultParameters() {
	return DEFAULT_PARAMETERS;
    }

}
