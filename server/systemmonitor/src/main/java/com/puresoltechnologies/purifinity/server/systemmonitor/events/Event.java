package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import java.net.InetAddress;
import java.util.Date;

/**
 * This class contains the data for an event to be logged bz the event logger.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Event {

	private final Date time;
	private final EventType type;
	private final EventSeverity severity;
	private final String message;

	private String user = null;
	private InetAddress ipAddress = null;
	private Throwable throwable;

	public Event(EventType type, EventSeverity severity, String message) {
		this(new Date(), type, severity, message);
	}

	public Event(Date time, EventType type, EventSeverity severity,
			String message) {
		super();
		this.time = time;
		this.type = type;
		this.severity = severity;
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public EventSeverity getSeverity() {
		return severity;
	}

	public EventType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

}
