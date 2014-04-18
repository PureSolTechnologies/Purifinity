package com.puresoltechnologies.purifinity.server.passwordstore.core.impl;


/**
 * This enum represents the different state of a user account.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum AccountState {

	/**
	 * This account is just created, but not yet activated.
	 */
	CREATED(0),
	/**
	 * This account is active and can be used.
	 */
	ACTIVE(1),
	/**
	 * This account has been deactivated and needs to be activated again.
	 */
	DEACTIVATED(2),
	/**
	 * This account is deleted.
	 */
	DELETED(3);

	private final int value;

	private AccountState(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static AccountState fromValue(int value) {
		for (AccountState status : values()) {
			if (value == status.getValue()) {
				return status;
			}
		}
		throw new IllegalArgumentException("Illegal value '" + value
				+ "' was provided!");
	}

}
