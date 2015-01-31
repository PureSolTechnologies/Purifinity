package com.puresoltechnologies.purifinity.server.systemmonitor.client;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;
import com.puresoltechnologies.server.systemmonitor.core.api.metrics.MetricLoggerRemote;

@Singleton
public class SystemMonitorProducer {

    private static final int MAX_RETRIES = 60;
    private static final int RETRY_DELAY = 10;

    @Inject
    private Logger logger;

    private EventLoggerRemote eventLogger;
    private MetricLoggerRemote metricLogger;

    @PostConstruct
    public void PostConstruct() {
	eventLogger = connect(EventLoggerRemote.class,
		EventLoggerRemote.JNDI_NAME);
	metricLogger = connect(MetricLoggerRemote.class,
		MetricLoggerRemote.JNDI_NAME);
    }

    private <T> T connect(Class<T> clazz, String jndi) {
	int counter = 0;
	while (counter < MAX_RETRIES) {
	    try {
		logger.info("Try to connect '" + clazz.toString() + "'...");
		T remote = JndiUtils.createRemoteEJBInstance(clazz, jndi);
		logger.info("'" + clazz.toString() + "' was connected.");
		return remote;
	    } catch (IllegalStateException e) {
		logger.info("'" + clazz.toString()
			+ "' could not be connected '" + e.getMessage() + "'.");
		try {
		    Thread.sleep(TimeUnit.SECONDS.toMicros(RETRY_DELAY));
		} catch (InterruptedException e1) {
		    logger.warn("'" + clazz.toString()
			    + "' could not be connected due to interrupt.", e1);
		}
		counter++;
	    }
	}
	throw new RuntimeException("Could not connect to '" + clazz.toString()
		+ "' due to timeout.");
    }

    @Produces
    @Singleton
    public EventLoggerRemote getEventLogger() {
	return eventLogger;
    }

    @Produces
    @Singleton
    public MetricLoggerRemote getMetricLogger() {
	return metricLogger;
    }

}
