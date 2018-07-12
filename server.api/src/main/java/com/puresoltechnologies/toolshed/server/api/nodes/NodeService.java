package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.toolshed.server.api.kpis.KPIDefinition;

@Path("/nodes")
public interface NodeService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<NodeInformation> getNodes();

    @GET
    @Path("/{node}/processes")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<ProcessInformation> getProcesses(@PathParam("node") String nodeName);

    @GET
    @Path("/{node}/processes/{pid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProcessInformation getProcess(@PathParam("node") String nodeName, @PathParam("pid") String pid);

    @GET
    @Path("/{node}/kpis")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<KPIDefinition> getKPIs(@PathParam("node") String nodeName);

    @GET
    @Path("/{node}/kpis/counters")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<KPIDefinition> getCounters(@PathParam("node") String nodeName);

    @GET
    @Path("/{node}/kpis/gauges")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<KPIDefinition> getGauges(@PathParam("node") String nodeName);

    @GET
    @Path("/{node}/kpis/histograms")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<KPIDefinition> getHistorams(@PathParam("node") String nodeName);

    @GET
    @Path("/{node}/kpis/meters")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<KPIDefinition> getMeters(@PathParam("node") String nodeName);

    @GET
    @Path("/{node}/kpis/timers")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<KPIDefinition> getTimers(@PathParam("node") String nodeName);

}
