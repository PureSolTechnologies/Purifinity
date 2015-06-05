package com.puresoltechnologies.purifinity.server.core.api.preferences;

import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

/**
 * This interface represents the preferences store used in Purifinity.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PreferencesStore {

	public List<ConfigurationParameter<?>> getSystemParameters();

	public List<ConfigurationParameter<?>> getUserDefaultParameters();

	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			String pluginId);

	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			String projectId, String pluginId);

	/**
	 * This method reads a preference value from the system store.
	 * 
	 * @param key
	 *            is the name of the parameter.
	 * @return A {@link PreferencesValue} is returned containing the value. If
	 *         no value was found <code>null</code> is returned.
	 */
	public PreferencesValue<?> getSystemPreference(String key);

	/**
	 * This method is used to store a new value into the store.
	 * 
	 * @param key
	 *            is the name of the parameter.
	 * @param value
	 *            is the parameter's value.
	 */
	public void setSystemPreference(String key, String value);

	/**
	 * This method checks whether a value was stored for a parameter or not.
	 * 
	 * @param group
	 *            is the name of the group.
	 * @param key
	 *            is the name of the parameter.
	 * @return <code>true</code> is returned in case a value was already stored.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasSystemPreference(String key);

	/**
	 * This method reads a preference value from the system store.
	 * 
	 * @param key
	 *            is the name of the parameter.
	 * @param pluginId
	 *            is the id of the plug-in the preference belongs to.
	 * @return A {@link PreferencesValue} is returned containing the value. If
	 *         no value was found <code>null</code> is returned.
	 */
	public PreferencesValue<?> getPluginDefaultPreference(String pluginId,
			String key);

	/**
	 * This method is used to store a new value into the store.
	 * 
	 * @param key
	 *            is the name of the parameter.
	 * @param pluginId
	 *            is the id of the plug-in the preference belongs to.
	 * @param value
	 *            is the parameter's value.
	 */
	public void setPluginDefaultPreference(String pluginId, String key,
			String value);

	/**
	 * This method checks whether a value was stored for a parameter or not.
	 * 
	 * @param pluginId
	 *            is the id of the plugin the preference belongs to.
	 * @param key
	 *            is the name of the parameter.
	 * @return <code>true</code> is returned in case a value was already stored.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasPluginDefaultPreference(String pluginId, String key);

	/**
	 * This method reads a preference value from the system store.
	 * 
	 * @param pluginId
	 *            is the id of the plug-in the preference belongs to.
	 * @param projectId
	 *            is the id of the project the preference belongs to.
	 * @param key
	 *            is the name of the parameter.
	 * @return A {@link PreferencesValue} is returned containing the value. If
	 *         no value was found <code>null</code> is returned.
	 */
	public PreferencesValue<?> getPluginProjectPreference(String projectId,
			String pluginId, String key);

	public void deletePluginProjectParameter(String projectId, String pluginId,
			String key);

	/**
	 * This method is used to store a new value into the store.
	 * 
	 * @param pluginId
	 *            is the id of the plug-in the preference belongs to.
	 * @param projectId
	 *            is the id of the project the preference belongs to.
	 * @param key
	 *            is the name of the parameter.
	 * @param value
	 *            is the parameter's value.
	 */
	public void setPluginProjectPreference(String projectId, String pluginId,
			String key, String value);

	/**
	 * This method checks whether a value was stored for a parameter or not.
	 * 
	 * @param pluginId
	 *            is the id of the plug-in the preference belongs to.
	 * @param projectId
	 *            is the id of the project the preference belongs to.
	 * @param key
	 *            is the name of the parameter.
	 * @return <code>true</code> is returned in case a value was already stored.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasPluginProjectPreference(String projectId,
			String pluginId, String key);

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
