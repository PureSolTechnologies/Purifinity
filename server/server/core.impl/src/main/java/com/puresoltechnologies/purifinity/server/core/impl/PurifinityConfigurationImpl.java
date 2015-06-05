package com.puresoltechnologies.purifinity.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;

public class PurifinityConfigurationImpl implements PurifinityConfiguration {

	private static final ConfigurationParameter<Boolean> ANONYMOUS_CAN_READ = new ConfigurationParameter<>(
			"Anonymous can read",
			"",
			LevelOfMeasurement.NOMINAL,
			"Specifies whether an anonymous user (a user not logged in) is allowed to see results in read only mode.",
			Boolean.class, "anonymous.can.read", "/Security", false);

	private static final ConfigurationParameter<Integer> ALERT_MESSAGE_TIMEOUT = new ConfigurationParameter<>(
			"Alert Message Timeout", "s", LevelOfMeasurement.RATIO,
			"Specifies the timeout for messages shown by Alerter.",
			Integer.class, "webui.messages.timeout", "/WebUI", 3);

	private static final ConfigurationParameter<Integer> USER_INACTIVITY_TIMEOUT = new ConfigurationParameter<>(
			"User Inactivity Timeout",
			"min",
			LevelOfMeasurement.RATIO,
			"Specifies the time in minutes after which an inactive user is automatically logged off.",
			Integer.class, "user.login.timeout.inactive", "/Security", 15);

	private static final ConfigurationParameter<Integer> USER_SESSION_TIMEOUT = new ConfigurationParameter<>(
			"User Session Timeout",
			"min",
			LevelOfMeasurement.RATIO,
			"Specifies the time in minutes after which an user is automatically logged off for re-login.",
			Integer.class, "user.login.timeout.session", "/Security", 1440);

	private static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<ConfigurationParameter<?>>();
	static {
		PARAMETERS.add(ANONYMOUS_CAN_READ);
		PARAMETERS.add(ALERT_MESSAGE_TIMEOUT);
		PARAMETERS.add(USER_INACTIVITY_TIMEOUT);
		PARAMETERS.add(USER_SESSION_TIMEOUT);
	}

	@Override
	public List<ConfigurationParameter<?>> getParameters() {
		return PARAMETERS;
	}
}
