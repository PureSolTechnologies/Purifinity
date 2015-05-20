package com.puresoltechnologies.purifinity.server.core.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.purifinity.server.core.api.PurifinityConfiguration;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;

public class PurifinityConfigurationImpl implements PurifinityConfiguration {

	private static final ConfigurationParameter<Boolean> ANONYMOUS_CAN_READ = new ConfigurationParameter<Boolean>(
			"Anonymous can read",
			"",
			LevelOfMeasurement.NOMINAL,
			"Specifies whether an anonymous user (a user not logged in) is allowed to see results in read only mode.",
			Boolean.class, "anonymous.can.read", "/Security", false);

	private static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<ConfigurationParameter<?>>();
	static {
		PARAMETERS.add(ANONYMOUS_CAN_READ);
	}

	@Inject
	private PreferencesStore preferencesStore;

	@Override
	public List<ConfigurationParameter<?>> getParameters() {
		return PARAMETERS;
	}
}
