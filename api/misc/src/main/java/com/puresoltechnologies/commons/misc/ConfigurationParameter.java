package com.puresoltechnologies.commons.misc;

import static com.puresoltechnologies.commons.misc.ParameterChecks.checkNotNullOrEmpty;

import java.util.Properties;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

/**
 * This class specifies a single configuration parameter.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T>
 *            is the type of the parameter value.
 */
public class ConfigurationParameter<T> extends ParameterWithArbitraryUnit<T> {

	private static final long serialVersionUID = 969219744450680004L;

	private final String propertyKey;
	private final T defaultValue;

	public ConfigurationParameter(String name, String unit,
			LevelOfMeasurement levelOfMeasurement, String description,
			Class<T> type, String propertyKey, T defaultValue) {
		super(name, unit, levelOfMeasurement, description, type);
		checkNotNullOrEmpty("propertyKey", propertyKey);
		if ((propertyKey == null) || (propertyKey.isEmpty())) {
			throw new IllegalArgumentException(
					"The property key must not be null or empty. No identification is possible otherwise.");
		}
		this.propertyKey = propertyKey;
		this.defaultValue = defaultValue;
	}

	/**
	 * This method returns a key name which can be used in {@link Properties} to
	 * store and retrieve the parameter value.
	 * 
	 * @return A {@link String} with the key name is returned.
	 */
	public final String getPropertyKey() {
		return propertyKey;
	}

	/**
	 * This method returns a default value of the configuration parameter. This
	 * value is to be used if no setting was made.
	 * 
	 * @return A value of T is returned containing the default value.
	 */
	public final T getDefaultValue() {
		return defaultValue;
	}

}
