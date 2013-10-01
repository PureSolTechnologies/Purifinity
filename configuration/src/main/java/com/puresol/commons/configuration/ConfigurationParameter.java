package com.puresol.commons.configuration;

import com.puresol.commons.math.LevelOfMeasurement;
import com.puresol.commons.math.ParameterWithArbitraryUnit;

/**
 * This class specifies a single configuration parameter.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 */
public class ConfigurationParameter<T> extends ParameterWithArbitraryUnit<T> {

	private static final long serialVersionUID = 969219744450680004L;

	private final String propertyKey;
	private final T defaultValue;

	public ConfigurationParameter(String name, String unit,
			LevelOfMeasurement levelOfMeasurement, String description,
			Class<T> type, String propertyKey, T defaultValue) {
		super(name, unit, levelOfMeasurement, description, type);
		this.propertyKey = propertyKey;
		this.defaultValue = defaultValue;
	}

	public final String getPropertyKey() {
		return propertyKey;
	}

	public final T getDefaultValue() {
		return defaultValue;
	}

}
