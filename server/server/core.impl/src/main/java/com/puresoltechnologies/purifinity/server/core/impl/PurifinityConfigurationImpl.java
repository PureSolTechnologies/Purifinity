package com.puresoltechnologies.purifinity.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;

@Singleton
public class PurifinityConfigurationImpl implements PurifinityConfiguration {

	private static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<ConfigurationParameter<?>>();
	static {
		PARAMETERS.add(ANONYMOUS_CAN_READ);
		PARAMETERS.add(ALERT_MESSAGE_TIMEOUT);
		PARAMETERS.add(USER_INACTIVITY_TIMEOUT);
		PARAMETERS.add(USER_SESSION_TIMEOUT);
		PARAMETERS.add(MAX_FILE_SIZE);
	}

	@Override
	@Lock(LockType.READ)
	public List<ConfigurationParameter<?>> getParameters() {
		return PARAMETERS;
	}
}
