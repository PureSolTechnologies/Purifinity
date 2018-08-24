package com.puresoltechnologies.toolshed.server.api.kpis;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/kpis")
public interface KPIService {

    @GET
    @Path("/counters")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<CounterDefinition> getCounters();

    @GET
    @Path("/timers")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<TimerDefinition> getTimers();

    @GET
    @Path("/meters")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<MeterDefinition> getMeters();

    @GET
    @Path("/gauges")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<GaugeDefinition> getGauges();

    @GET
    @Path("/histograms")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<HistogramDefinition> getHistograms();

}
