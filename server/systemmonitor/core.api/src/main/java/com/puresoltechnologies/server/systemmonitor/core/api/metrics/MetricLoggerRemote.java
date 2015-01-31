package com.puresoltechnologies.server.systemmonitor.core.api.metrics;

import javax.ejb.Remote;

@Remote
public interface MetricLoggerRemote extends MetricLoggerCommon {

    public static final String JNDI_NAME = "java:global/systemmonitor.app/systemmonitor.core.impl/MetricLoggerBean!com.puresoltechnologies.server.systemmonitor.core.api.metrics.MetricLoggerRemote";

}
