package com.puresoltechnologies.purifinity.server.core.api.preferences;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

/**
 * This interface represents the preferences store used in Purifinity.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PreferencesStore {

    public ConfigurationParameter<?>[] getSystemParameters();

    public ConfigurationParameter<?>[] getUserDefaultParameters();

    public ConfigurationParameter<?>[] getPluginDefaultParameters(String pluginId);

    public ConfigurationParameter<?>[] getPluginProjectParameters(String projectId, String pluginId);

    /**
     * This method reads a preference value from the system store.
     * 
     * @param configurationParameter
     *            is the parameter.
     * @param <T>
     *            is the actual type of the parameter's value.
     * @return A {@link PreferencesValue} is returned containing the value. If
     *         no value was found <code>null</code> is returned.
     */
    public <T> PreferencesValue<T> getSystemPreference(ConfigurationParameter<T> configurationParameter);

    /**
     * This method is used to store a new value into the store.
     * 
     * @param configurationParameter
     *            is the parameter.
     * @param value
     *            is the parameter's value.
     * @param <T>
     *            is the actual type of the parameter's value.
     */
    public <T> void setSystemPreference(ConfigurationParameter<T> configurationParameter, T value);

    /**
     * This method checks whether a value was stored for a parameter or not.
     * 
     * @param configurationParameter
     *            is the parameter.
     * @return <code>true</code> is returned in case a value was already stored.
     *         <code>false</code> is returned otherwise.
     */
    public boolean hasSystemPreference(ConfigurationParameter<?> configurationParameter);

    /**
     * This method reads a preference value from the system store.
     * 
     * @param pluginId
     *            is the id of the plug-in the preference belongs to.
     * @param configurationParameter
     *            is the parameter.
     * @param <T>
     *            is the actual type of the parameter's value.
     * @return A {@link PreferencesValue} is returned containing the value. If
     *         no value was found <code>null</code> is returned.
     */
    public <T> PreferencesValue<T> getPluginDefaultPreference(String pluginId,
	    ConfigurationParameter<T> configurationParameter);

    /**
     * This method is used to store a new value into the store.
     * 
     * @param pluginId
     *            is the id of the plug-in the preference belongs to.
     * @param configurationParameter
     *            is the parameter.
     * @param value
     *            is the parameter's value.
     * @param <T>
     *            is the actual type of the parameter's value.
     */
    public <T> void setPluginDefaultPreference(String pluginId, ConfigurationParameter<T> configurationParameter,
	    T value);

    /**
     * This method checks whether a value was stored for a parameter or not.
     * 
     * @param pluginId
     *            is the id of the plugin the preference belongs to.
     * @param configurationParameter
     *            is the parameter.
     * @return <code>true</code> is returned in case a value was already stored.
     *         <code>false</code> is returned otherwise.
     */
    public boolean hasPluginDefaultPreference(String pluginId, ConfigurationParameter<?> configurationParameter);

    /**
     * This method reads a preference value from the system store.
     * 
     * @param pluginId
     *            is the id of the plug-in the preference belongs to.
     * @param projectId
     *            is the id of the project the preference belongs to.
     * @param configurationParameter
     *            is the parameter.
     * @param <T>
     *            is the actual type of the parameter's value.
     * @return A {@link PreferencesValue} is returned containing the value. If
     *         no value was found <code>null</code> is returned.
     */
    public <T> PreferencesValue<T> getPluginProjectPreference(String projectId, String pluginId,
	    ConfigurationParameter<T> configurationParameter);

    public void deletePluginProjectParameter(String projectId, String pluginId, String key);

    /**
     * This method is used to store a new value into the store.
     * 
     * @param projectId
     *            is the id of the project the preference belongs to.
     * @param pluginId
     *            is the id of the plug-in the preference belongs to.
     * @param configurationParameter
     *            is the parameter.
     * @param value
     *            is the parameter's value.
     * @param <T>
     *            is the actual type of the parameter's value.
     */
    public <T> void setPluginProjectPreference(String projectId, String pluginId,
	    ConfigurationParameter<T> configurationParameter, T value);

    /**
     * This method checks whether a value was stored for a parameter or not.
     * 
     * @param projectId
     *            is the id of the project the preference belongs to.
     * @param pluginId
     *            is the id of the plug-in the preference belongs to.
     * @param configurationParameter
     *            is the parameter.
     * @return <code>true</code> is returned in case a value was already stored.
     *         <code>false</code> is returned otherwise.
     */
    public boolean hasPluginProjectPreference(String projectId, String pluginId,
	    ConfigurationParameter<?> configurationParameter);

    /**
     * This method checks whether a specified service is activated or not. This
     * setting is system wide!
     * 
     * @param serviceId
     *            is the id of the service to be checked.
     * @return <code>true</code> is returned if the service is known and
     *         activated. <code>false</code> is returned otherwise if the
     *         service is unknown or deactivated.
     */
    public boolean isServiceActive(String serviceId);

    /**
     * This method sets the activation state of a service. This setting is
     * system wide!
     * 
     * @param serviceId
     *            is the id of the service to be set active or inactive.
     * @param active
     *            is set to <code>true</code> if the service is to be activated.
     *            <code>false</code> is to be set otherwise.
     */
    public void setServiceActive(String serviceId, boolean active);

}
