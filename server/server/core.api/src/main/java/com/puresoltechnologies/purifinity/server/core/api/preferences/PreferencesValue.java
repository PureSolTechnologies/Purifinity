package com.puresoltechnologies.purifinity.server.core.api.preferences;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a single date in the preferences store as value object.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PreferencesValue<T> {

	private final Date changed;
	private final String changedBy;
	private final PreferencesGroup group;
	private final String groupName;
	private final String key;
	private final T value;

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
	 * @param key
	 *            is the name of the preference.
	 * @param value
	 *            is the current value of the preference.
	 */
	@JsonCreator
	public PreferencesValue(@JsonProperty("changed") Date changed,
			@JsonProperty("changedBy") String changedBy,
			@JsonProperty("group") PreferencesGroup group,
			@JsonProperty("groupName") String groupName,
			@JsonProperty("key") String key, //
			@JsonProperty("value") T value) {
		super();
		this.changed = changed;
		this.changedBy = changedBy;
		this.group = group;
		this.groupName = groupName;
		this.key = key;
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
	public PreferencesGroup getGroup() {
		return group;
	}

	/**
	 * Returns the group name.
	 * 
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Returns the preference's key.
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the preference's value.
	 * 
	 * @return
	 */
	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return group + "/" + key + "=" + value + " (changed " + changed
				+ " by " + changedBy + ")";
	}
}
