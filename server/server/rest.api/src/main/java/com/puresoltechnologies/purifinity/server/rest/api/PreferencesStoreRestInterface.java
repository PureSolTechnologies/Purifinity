package com.puresoltechnologies.purifinity.server.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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

	@PUT
	@Path("system/{property}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void setSystemParameter(@PathParam("property") String property,
			String value);

	@GET
	@Path("system/{property}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSystemParameter(@PathParam("property") String property);

	@GET
	@Path("plugins/{pluginId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getPluginDefaultParameters(
			@PathParam("pluginId") String pluginId);

	@PUT
	@Path("plugins/{pluginId}/{property}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void setPluginDefaultParameter(
			@PathParam("pluginId") String pluginId,
			@PathParam("property") String property, String value);

	@GET
	@Path("plugins/{pluginId}/{property}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String getPluginDefaultParameter(
			@PathParam("pluginId") String pluginId,
			@PathParam("property") String property);

	@GET
	@Path("plugins/{pluginId}/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getPluginProjectParameters(
			@PathParam("projectId") String projectId);

	@PUT
	@Path("plugins/{pluginId}/{projectId}/{property}")
	@Consumes(MediaType.TEXT_PLAIN)
	public String getPluginDefaultParameter(
			@PathParam("pluginId") String pluginId,
			@PathParam("projectId") String projectId,
			@PathParam("property") String property);

	@GET
	@Path("plugins/{pluginId}/{projectId}/{property}")
	@Produces(MediaType.TEXT_PLAIN)
	public void setPluginDefaultParameter(
			@PathParam("pluginId") String pluginId,
			@PathParam("projectId") String projectId,
			@PathParam("property") String property, String value);

	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getUserDefaultParameters();

	@PUT
	@Path("users/{property}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void setUserDefaultParameter(@PathParam("property") String property,
			String value);

	@GET
	@Path("users/{property}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserDefaultParameter(@PathParam("property") String property);

	@GET
	@Path("users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfigurationParameter<?>> getUserParameters(
			@PathParam("userId") String userId);

	@PUT
	@Path("users/{userId}/{property}")
	@Consumes(MediaType.TEXT_PLAIN)
	public void setUserParameter(@PathParam("property") String property,
			@PathParam("userId") String userId, String value);

	@GET
	@Path("users/{userId}/{property}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserParameter(@PathParam("property") String property,
			@PathParam("userId") String userId);

}
