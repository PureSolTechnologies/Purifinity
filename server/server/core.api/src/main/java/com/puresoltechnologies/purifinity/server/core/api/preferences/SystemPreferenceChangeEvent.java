package com.puresoltechnologies.purifinity.server.core.api.preferences;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

/**
 * This class represents a single system configuration parameter change. This
 * class is used to transport the corresponding data via CDI events.
 * 
 * @author Rick-Rainer Ludwig
 *
 * @param <T>
 *            is the type of the configuration value.
 */
public class SystemPreferenceChangeEvent {

	private final ConfigurationParameter<?> configurationParameter;
	private final Object value;

	public SystemPreferenceChangeEvent(
			ConfigurationParameter<?> configurationParameter, Object value) {
		super();
		this.configurationParameter = configurationParameter;
		this.value = value;
	}

	public ConfigurationParameter<?> getConfigurationParameter() {
		return configurationParameter;
	}

	public Object getValue() {
		return value;
	}

}
