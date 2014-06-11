package com.puresoltechnologies.purifinity.server.preferences;

import java.util.Date;

/**
 * This class represents a single date in the preferences store as value object.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PreferencesValue {

	private final Date changed;
	private final String changedBy;
	private final String group;
	private final String name;
	private final String value;

	/**
	 * This is the initial value constructor.
	 * 
	 * @param changed
	 *            is the date of the last change. Might be <code>null</code>.
	 *            See {@link #getChanged()} for details.
	 * @param changedBy
	 *            is the users name of the user who did the last change. Might
	 *            be <code>null</code>. See {@link #getChangedBy()} for details.
	 * @param group
	 *            is the name of the group.
	 * @param name
	 *            is the name of the preference.
	 * @param value
	 *            is the current value of the preference.
	 */
	public PreferencesValue(Date changed, String changedBy, String group,
			String name, String value) {
		super();
		this.changed = changed;
		this.changedBy = changedBy;
		this.group = group;
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns the time stamp of the last change. This value might be null, if
	 * the value was not stored in the store, yet.
	 * 
	 * @return A {@link Date} with the user's name is returned.
	 *         <code>null</code> is returned if the preference value was not
	 *         stored in database, yet.
	 */
	public Date getChanged() {
		return changed;
	}

	/**
	 * Returns the name of the user who did the last change. This value might be
	 * null, if the value was not stored in the store, yet.
	 * 
	 * @return A {@link String} with the user's name is returned.
	 *         <code>null</code> is returned if the preference value was not
	 *         stored in database, yet.
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * Returns the preference's group name.
	 * 
	 * @return
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Returns the preference's name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the preference's value.
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return group + "/" + name + "=" + " (changed " + changed + " by "
				+ changedBy + ")";
	}

}
