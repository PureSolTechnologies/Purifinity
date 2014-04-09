package com.puresoltechnologies.purifinity.server.systemmonitor.events;

/**
 * This enum classifies an event in the event logger by its severity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum EventSeverity {

	/**
	 * Info levels are used for information to be used for normal auditing.
	 */
	INFO,
	/**
	 * Warnings are stored in situations where something might have gone wrong,
	 * but what is not preventing the system from performing the actual goal.
	 */
	WARNING,
	/**
	 * Errors signal situations where the system cannot proceed with its current
	 * task and abort the execution. But, the whole system itself is not
	 * effected and still runs. Only the current action is aborted.
	 */
	ERROR,
	/**
	 * Fatal signalizes a state, where the system is out of control and it may
	 * not be possible to proceed normal operation anymore. Fatal should be
	 * logged before the system is shut down due to an irrecoverable issue, or
	 * if a situation occurs, where it is likely, that the system is crashing
	 * afterwards.
	 */
	FATAL;

}
