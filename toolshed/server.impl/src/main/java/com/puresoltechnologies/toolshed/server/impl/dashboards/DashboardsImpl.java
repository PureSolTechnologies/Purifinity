package com.puresoltechnologies.toolshed.server.impl.dashboards;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest/dashboards")
public class DashboardsImpl {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<DashboardInformation> getDashboards() {
	Set<DashboardInformation> dashboards = new HashSet<>();
	dashboards.add(new DashboardInformation("id1", "Memory Host 1"));
	return dashboards;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DashboardDefinition getDashboard(String dashboardId) {
	return new DashboardDefinition("id1", "Memory Host 1");
    }

}
