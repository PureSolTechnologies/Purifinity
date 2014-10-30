package com.puresoltechnologies.purifinity.server.systemmonitor.events;

import java.util.Date;

import com.puresoltechnologies.commons.misc.types.EmailAddress;

/**
 * This class contains the data for an event to be logged bz the event logger.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Event {

    private final Date time;
    private final String component;
    private final long eventId;
    private final EventType type;
    private final EventSeverity severity;
    private final String message;

    private EmailAddress userEmail = null;
    private long userId = -1;
    private String clientHostname = null;
    private Throwable throwable;

    public Event(String component, long eventId, EventType type,
	    EventSeverity severity, String message) {
	this(new Date(), component, eventId, type, severity, message);
    }

    public Event(Date time, String component, long eventId, EventType type,
	    EventSeverity severity, String message) {
	super();
	this.time = time;
	this.component = component;
	this.eventId = eventId;
	this.type = type;
	this.severity = severity;
	this.message = message;
    }

    public Date getTime() {
	return time;
    }

    public String getComponent() {
	return component;
    }

    public long getEventId() {
	return eventId;
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

    public EmailAddress getUserEmail() {
	return userEmail;
    }

    public Event setUserEmail(EmailAddress userEmail) {
	this.userEmail = userEmail;
	return this;
    }

    public long getUserId() {
	return userId;
    }

    public Event setUserId(long userId) {
	this.userId = userId;
	return this;
    }

    public String getClientHostname() {
	return clientHostname;
    }

    public Event setClientHostname(String clientHostname) {
	this.clientHostname = clientHostname;
	return this;
    }

    public Throwable getThrowable() {
	return throwable;
    }

    public Event setThrowable(Throwable throwable) {
	this.throwable = throwable;
	return this;
    }

}
