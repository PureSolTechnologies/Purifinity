package com.puresoltechnologies.toolshed.server.api.kpis;

import java.util.Set;

import javax.ws.rs.Path;

@Path("/kpis")
public interface KPIService {

    @Path("/counters")
    public Set<CounterDefinition> getCounters();

    @Path("/timers")
    public Set<TimerDefinition> getTimers();

    @Path("/meters")
    public Set<MeterDefinition> getMeters();

    @Path("/gauges")
    public Set<GaugeDefinition> getGauges();

    @Path("/histograms")
    public Set<HistogramDefinition> getHistograms();

}
