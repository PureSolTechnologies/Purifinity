package com.puresoltechnologies.purifinity.server.rest.api;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;

@Path("projectmanager")
public interface ProjectManagerRestInterface {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}")
    public AnalysisProjectInformation createAnalysisProject(@PathParam("project_id") String projectId,
	    AnalysisProjectSettings settings) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/information")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public List<AnalysisProjectInformation> readAllAnalysisProjectInformation() throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public List<AnalysisProject> readAllAnalysisProjects() throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisProject readAnalysisProject(@PathParam("project_id") String projectId) throws AnalysisStoreException;

    @DELETE
    @Path("projects/{project_id}")
    public void removeAnalysisProject(@PathParam("project_id") String projectId) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/settings")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisProjectSettings readAnalysisProjectSettings(@PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/information")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisProjectInformation readAnalysisProjectInformation(@PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}")
    public void updateAnalysisProjectSettings(@PathParam("project_id") String projectId,
	    AnalysisProjectSettings settings) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public List<AnalysisRunInformation> readAllRunInformation(@PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisRun readAnalysisRun(@PathParam("project_id") String projectId, @PathParam("run_id") long runId)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/information")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisRunInformation readAnalysisRunInformation(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/lastrun")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisRunInformation readLastAnalysisRun(@PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @DELETE
    @Path("projects/{project_id}/runs/{run_id}")
    public void removeAnalysisRun(@PathParam("project_id") String projectId, @PathParam("run_id") long runId)
	    throws AnalysisStoreException;

    @GET
    @Path("projects/{project_id}/runs/{run_id}/searchconfiguration")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public FileSearchConfiguration readSearchConfiguration(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/filetree")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalysisFileTree readAnalysisFileTree(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/create_run")
    public AnalysisRunInformation createAnalysisRun(@PathParam("project_id") String projectId,
	    @QueryParam("startTime") Date startTime, @QueryParam("duration") long duration,
	    @QueryParam("description") @DefaultValue("") String description,
	    FileSearchConfiguration fileSearchConfiguration) throws AnalysisStoreException;
}
