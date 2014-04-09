package com.puresoltechnologies.purifinity.server.systemmonitor.events;

/**
 * This enum contains all event types which can be logged for audit. Everything
 * which is not logged in the event logger, is logged in the normal system
 * logger.
 * 
 * Events for the event logger are needed for audit of the processes and the
 * system as whole.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum EventType {

	/**
	 * This is a logged user action which logs something the user did for later
	 * audit.
	 */
	USER_ACTION,
	/**
	 * This event type marks an exception the user saw during his usage. These
	 * are wanted exceptions due to wrong inputs or usage.
	 */
	USER_EXCEPTION,
	/**
	 * This is a system event. This shows an important event of the system which
	 * is needed for later audit.
	 */
	SYSTEM,
	/**
	 * This is a system exception which is not expected, but which needs to be
	 * logged anyway for audit and system analysis.
	 */
	SYSTEM_EXCEPTION;

}
