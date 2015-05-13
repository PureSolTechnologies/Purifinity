package com.puresoltechnologies.purifinity.server.rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;

@Path("preferences")
public interface PreferencesStoreRestInterface {

	@GET
	@Path("system")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getSystemParameters();

	@GET
	@Path("plugins/{pluginId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			@PathParam("pluginId") String pluginId);

	@GET
	@Path("plugins/{pluginId}/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			@PathParam("projectId") String projectId);

	@GET
	@Path("users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getUserParameters(
			@PathParam("userId") String userId);

	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getUserDefaultParameters();

}
