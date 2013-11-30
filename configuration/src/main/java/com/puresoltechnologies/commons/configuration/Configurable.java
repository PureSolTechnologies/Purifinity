package com.puresoltechnologies.commons.configuration;

import java.util.Set;

/**
 * This interface is implemented by all classes which have the possibility for
 * generic configuration. This interface should be only implemented in
 * 'high-end' classes whose behavior is exposed to the end user. The
 * configuration handled here is generic and UI optimized to be used in
 * preference pages.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Configurable {

	/**
	 * This method returns a list of all configuration parameters for the
	 * programming language. This list of parameters is used to setup the
	 * specific behavior of the analyzer.
	 * 
	 * Via configuration behavior like file search patterns can be specified as
	 * well all other behavior which might be specified by the user.
	 * 
	 * @return A {@link Set} of {@link ConfigurationParameter} is returned
	 *         specifying the parameters for configuration.
	 */
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters();

	/**
	 * This method is used to set a parameter in the class.
	 * 
	 * @param parameter
	 *            is the {@link ConfigurationParameter} retrieved from
	 *            {@link #getAvailableConfigurationParameters()}.
	 * @param value
	 *            is the value to be set.
	 */
	public <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value);

	/**
	 * This method is used to set a parameter in the class.
	 * 
	 * @param parameter
	 *            is the {@link ConfigurationParameter} retrieved from
	 *            {@link #getAvailableConfigurationParameters()}.
	 * @return The value which was set by
	 *         {@link #setConfigurationParameter(ConfigurationParameter, Object)}
	 *         is returned. If no value (or null) was set the default value of
	 *         the parameter is returned.
	 */
	public <T> T getConfigurationParameter(ConfigurationParameter<T> parameter);

}
