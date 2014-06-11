package com.puresoltechnologies.purifinity.server.preferences;

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
	public PreferencesValue getValue(String group, String name);

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
	public PreferencesValue getValue(String group, String name,
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
	public void setValue(String group, String name, String value);

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
	public boolean hasValue(String group, String name);

}
