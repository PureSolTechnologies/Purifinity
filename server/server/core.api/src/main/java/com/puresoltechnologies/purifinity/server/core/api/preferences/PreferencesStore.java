package com.puresoltechnologies.purifinity.server.core.api.preferences;

/**
 * This interface represents the preferences store used in Purifinity.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PreferencesStore {

	/**
	 * This method reads a preference value from the store.
	 * 
	 * @param group
	 *            is the name of the group.
	 * @param name
	 *            is the name of the parameter.
	 * @return A {@link PreferencesValue} is returned containing the value. If
	 *         no value was found <code>null</code> is returned.
	 */
	public PreferencesValue getValue(PreferencesGroup group, String name);

	/**
	 * This method is used like {@link #getValue(String, String)}, but an
	 * additional default value can be specified which is returned if no value
	 * is found in store. This method is used to avoid null pointer exceptionss.
	 * 
	 * @param group
	 *            is the name of the group.
	 * @param name
	 *            is the name of the parameter.
	 * @param defaultValue
	 *            is the default value to be returned if no value is found in
	 *            store. This value is not(!) put into the store automatically!
	 * @return A {@link PreferencesValue} is returned containing the value. If
	 *         no value was found defaultValue as specified is returned.
	 */
	public PreferencesValue getValue(PreferencesGroup group, String name,
			String defaultValue);

	/**
	 * This method is used to store a new value into the store.
	 * 
	 * @param group
	 *            is the name of the group.
	 * @param name
	 *            is the name of the parameter.
	 * @param value
	 *            is the parameter's value.
	 */
	public void setValue(PreferencesGroup group, String name, String value);

	public void setValue(PreferencesGroup group, String name, boolean value);

	/**
	 * This method checks whether a value was stored for a parameter or not.
	 * 
	 * @param group
	 *            is the name of the group.
	 * @param name
	 *            is the name of the parameter.
	 * @return <code>true</code> is returned in case a value was already stored.
	 *         <code>false</code> is returned otherwise.
	 */
	public boolean hasValue(PreferencesGroup group, String name);

	public Boolean getBoolean(PreferencesGroup group, String name);

	public boolean getBoolean(PreferencesGroup group, String name,
			String defaultValue);

	public String getString(PreferencesGroup group, String name);

	public String getString(PreferencesGroup group, String name,
			String defaultValue);

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
