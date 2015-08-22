package com.puresoltechnologies.server.systemmonitor.core.api.metrics;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface MetricLoggerRemote extends MetricLoggerCommon {

    public static final String JNDI_NAME = "java:global/systemmonitor.app/com-puresoltechnologies-purifinity-server-systemmonitor.core.impl-"
	    + BuildInformation.getVersion()
	    + "/MetricLoggerBean!com.puresoltechnologies.server.systemmonitor.core.api.metrics.MetricLoggerRemote";

}
