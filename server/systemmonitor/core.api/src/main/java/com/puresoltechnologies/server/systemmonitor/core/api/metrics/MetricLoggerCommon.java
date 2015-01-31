package com.puresoltechnologies.server.systemmonitor.core.api.metrics;

import java.io.Serializable;
import java.util.Date;

import com.puresoltechnologies.commons.math.Value;

/**
 * This is the central interface for the metric logger. The metric logger
 * provides logging of KPIs and metrics which are needed for close monitoring.
 * The output needs to be standardized and needs to be parsed automatically.
 * 
 * @author "Rick-Rainer Ludwig"
 */
public interface MetricLoggerCommon extends Serializable {

    /**
     * This method logs the value which is provided.
     * 
     * The time stamp is set to the current time within the method.
     * 
     * @param value
     *            is the {@link Value} to be logged.
     */
    public void logEvent(Value<?> value);

    /**
     * This method logs the value which is provided.
     * 
     * @param time
     *            is the time stamp of type {@link Date} which is used to assign
     *            the time of value retrieval.
     * @param value
     *            is the {@link Value} to be logged.
     */
    public void logEvent(Date time, Value<?> value);

}
