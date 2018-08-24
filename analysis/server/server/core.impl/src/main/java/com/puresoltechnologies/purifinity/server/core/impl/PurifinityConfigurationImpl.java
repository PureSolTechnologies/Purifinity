package com.puresoltechnologies.purifinity.server.core.impl;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;

@Singleton
public class PurifinityConfigurationImpl implements PurifinityConfiguration {

    private static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] { ANONYMOUS_CAN_READ,
	    ALERT_MESSAGE_TIMEOUT, USER_INACTIVITY_TIMEOUT, USER_SESSION_TIMEOUT, MAX_FILE_SIZE };

    @Override
    @Lock(LockType.READ)
    public ConfigurationParameter<?>[] getParameters() {
	return PARAMETERS;
    }
}
