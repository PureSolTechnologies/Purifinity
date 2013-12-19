package com.puresoltechnologies.purifinity.server.eventlogger;

import java.io.Serializable;

/**
 * This is the central interface for the event logger. The event logger provides
 * logging on administrator level. The output needs to be standardized and needs
 * to be parsed automatically.
 * 
 * @author "Rick-Rainer Ludwig"
 */
public interface EventLogger extends Serializable {

	public void logUserAction(String user, String message);

	public void logUserException(String user, String message, String additional);

	public void logUserException(String user, Exception exception,
			String additional);

	public void logSystemEvent(String message);

	public void logSystemException(String message, String additional);

	public void logSystemException(Exception excpetion, String additional);

}
