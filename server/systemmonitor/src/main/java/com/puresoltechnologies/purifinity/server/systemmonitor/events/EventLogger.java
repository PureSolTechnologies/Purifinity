package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import java.io.Serializable;

/**
 * This is the central interface for the event logger. The event logger provides
 * logging on administrator level. The output needs to be standardized and needs
 * to be parsed automatically.
 * 
 * @author "Rick-Rainer Ludwig"
 */
public interface EventLogger extends Serializable {

	/**
	 * This method logs the event which is provided.
	 * 
	 * @param event
	 *            is the {@link Event} to be logged.
	 */
	public void logEvent(Event event);

}
