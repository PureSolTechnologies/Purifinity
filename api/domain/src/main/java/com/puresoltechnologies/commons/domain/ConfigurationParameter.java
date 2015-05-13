package com.puresoltechnologies.commons.domain;

import java.util.Properties;

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
	private final String path;
	private final T defaultValue;

	public ConfigurationParameter(String name, String unit,
			LevelOfMeasurement levelOfMeasurement, String description,
			Class<T> type, String propertyKey, String path, T defaultValue) {
		super(name, unit, levelOfMeasurement, description, type);
		if ((propertyKey == null) || (propertyKey.isEmpty())) {
			throw new IllegalArgumentException(
					"The property key must not be null or empty.");
		}
		if ((path == null) || (path.isEmpty())) {
			throw new IllegalArgumentException(
					"The path must not be null or empty.");
		}
		if (!path.startsWith("/")) {
			throw new IllegalArgumentException(
					"The path has to start with a slash '/'.");
		}
		if (defaultValue == null) {
			throw new IllegalArgumentException(
					"The default value must not be null.");
		}
		this.propertyKey = propertyKey;
		this.path = path;
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
	 * Returns the path where the configuration parameter is to be shown in a
	 * configuration dialog.
	 * 
	 * @return A {@link String} is returned which provides the path with a slash
	 *         '/' as separator.
	 */
	public String getPath() {
		return path;
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
