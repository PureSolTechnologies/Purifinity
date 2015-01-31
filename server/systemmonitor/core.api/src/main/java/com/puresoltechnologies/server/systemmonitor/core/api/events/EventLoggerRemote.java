package com.puresoltechnologies.server.systemmonitor.core.api.events;

import javax.ejb.Remote;

@Remote
public interface EventLoggerRemote extends EventLoggerCommon {

    public static final String JNDI_NAME = "java:global/systemmonitor.app/systemmonitor.core.impl/EventLoggerBean!com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote";

}
