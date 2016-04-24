package com.puresoltechnologies.purifinity.server.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;

@Path("preferences")
public interface PreferencesStoreRestInterface {

    @GET
    @Path("system")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public ConfigurationParameter<?>[] getSystemParameters();

    @PUT
    @Path("system/{key}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void setSystemParameter(@PathParam("key") String key, String value);

    @GET
    @Path("system/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public String getSystemParameter(@PathParam("key") String key);

    @GET
    @Path("plugins/{pluginId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public ConfigurationParameter<?>[] getPluginDefaultParameters(@PathParam("pluginId") String pluginId);

    @PUT
    @Path("plugins/{pluginId}/{key}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void setPluginDefaultParameter(@PathParam("pluginId") String pluginId, @PathParam("key") String key,
	    String value);

    @GET
    @Path("plugins/{pluginId}/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public String getPluginDefaultParameter(@PathParam("pluginId") String pluginId, @PathParam("key") String key);

    @GET
    @Path("projects/{projectId}/{pluginId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public ConfigurationParameter<?>[] getPluginProjectParameters(@PathParam("projectId") String projectId,
	    @PathParam("pluginId") String pluginId);

    @GET
    @Path("projects/{projectId}/{pluginId}/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public String getPluginProjectParameter(@PathParam("projectId") String projectId,
	    @PathParam("pluginId") String pluginId, @PathParam("key") String key);

    @PUT
    @Path("projects/{projectId}/{pluginId}/{key}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void setPluginProjectParameter(@PathParam("projectId") String projectId,
	    @PathParam("pluginId") String pluginId, @PathParam("key") String key, String value);

    @DELETE
    @Path("projects/{projectId}/{pluginId}/{key}")
    public void deletePluginProjectParameter(@PathParam("projectId") String projectId,
	    @PathParam("pluginId") String pluginId, @PathParam("key") String key);

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public ConfigurationParameter<?>[] getUserDefaultParameters();

    @PUT
    @Path("users/{key}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void setUserDefaultParameter(@PathParam("key") String key, String value);

    @GET
    @Path("users/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public String getUserDefaultParameter(@PathParam("key") String key);

    @GET
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public ConfigurationParameter<?>[] getUserParameters(@PathParam("userId") String userId);

    @PUT
    @Path("users/{userId}/{key}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void setUserParameter(@PathParam("key") String key, @PathParam("userId") String userId, String value);

    @GET
    @Path("users/{userId}/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public String getUserParameter(@PathParam("key") String key, @PathParam("userId") String userId);

}
