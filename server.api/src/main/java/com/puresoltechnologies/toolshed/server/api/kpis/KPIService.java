package com.puresoltechnologies.toolshed.server.api.kpis;

import javax.ws.rs.Path;

@Path("/kpis")
public interface KPIService {

    @Path("/counters")
    public void getCounters();

    @Path("/timers")
    public void getTimers();

    @Path("/meters")
    public void getMeters();

    @Path("/gauges")
    public void getGauges();

    @Path("/histograms")
    public void getHistograms();

}
