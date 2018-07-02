package com.puresoltechnologies.toolshed.server.api;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/nodes")
public interface MachinesService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Node> getNodes();

}
