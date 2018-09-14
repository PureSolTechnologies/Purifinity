package com.puresoltechnologies.server.systemmonitor.core.api.events;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface EventLoggerRemote extends EventLoggerCommon {

    public static final String JNDI_NAME = "java:global/systemmonitor.app/com-puresoltechnologies-purifinity-server-systemmonitor.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EventLoggerBean!com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote";

}