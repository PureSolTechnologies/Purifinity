package com.puresoltechnologies.commons.domain;

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
     * This method returns a list of all configuration parameters of the
     * implementing class. This list of parameters is used to setup the specific
     * behavior of the class.
     * 
     * @return An Array of {@link ConfigurationParameter} is returned specifying
     *         the parameters for configuration.
     */
    public ConfigurationParameter<?>[] getConfigurationParameters();

    /**
     * This method is used to set a parameter in the class.
     * 
     * @param parameter
     *            is the {@link ConfigurationParameter} retrieved from
     *            {@link #getConfigurationParameters()}.
     * @param value
     *            is the value to be set.
     * @throws IllegalArgumentException
     *             may be thrown if the parameter does not fit the list of
     *             parameter retieved from {@link #getConfigurationParameters()}
     *             , but this is up to the actual implementation.
     */
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value);

    /**
     * This method is used to get a parameter from the class.
     * 
     * @param parameter
     *            is the {@link ConfigurationParameter} retrieved from
     *            {@link #getConfigurationParameters()}.
     * @return The value which was set by
     *         {@link #setConfigurationParameter(ConfigurationParameter, Object)}
     *         is returned. If no value (or null) was set the default value of
     *         the parameter is returned.
     * @throws IllegalArgumentException
     *             may be thrown if the parameter does not fit the list of
     *             parameter retieved from {@link #getConfigurationParameters()}
     *             , but this is up to the actual implementation.
     */
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter);

}
