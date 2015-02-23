package com.puresoltechnologies.purifinity.server.rest.api;

import java.util.Collection;
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
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;

@Path("analysisstore")
public interface AnalysisStoreRestInterface {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}")
    public AnalysisProjectInformation createAnalysisProject(
	    @PathParam("project_id") String projectId,
	    AnalysisProjectSettings settings) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/information")
    public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects")
    public List<AnalysisProject> readAllAnalysisProjects()
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}")
    public AnalysisProject readAnalysisProject(
	    @PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @DELETE
    @Path("projects/{project_id}")
    public void removeAnalysisProject(@PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/settings")
    public AnalysisProjectSettings readAnalysisProjectSettings(
	    @PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/information")
    public AnalysisProjectInformation readAnalysisProjectInformation(
	    @PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}")
    public void updateAnalysisProjectSettings(
	    @PathParam("project_id") String projectId,
	    AnalysisProjectSettings settings) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs")
    public List<AnalysisRunInformation> readAllRunInformation(
	    @PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}")
    public AnalysisRun readAnalysisRun(
	    @PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/information")
    public AnalysisRunInformation readAnalysisRunInformation(
	    @PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/lastrun")
    public AnalysisRunInformation readLastAnalysisRun(
	    @PathParam("project_id") String projectId)
	    throws AnalysisStoreException;

    @DELETE
    @Path("projects/{project_id}/runs/{run_id}")
    public void removeAnalysisRun(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @GET
    @Path("projects/{project_id}/runs/{run_id}/searchconfiguration")
    @Produces(MediaType.APPLICATION_JSON)
    public FileSearchConfiguration readSearchConfiguration(
	    @PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/filetree")
    public AnalysisFileTree readAnalysisFileTree(
	    @PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws AnalysisStoreException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/create_run")
    public AnalysisRunInformation createAnalysisRun(
	    @PathParam("project_id") String projectId,
	    @QueryParam("startTime") Date startTime,
	    @QueryParam("duration") long duration,
	    @QueryParam("description") @DefaultValue("") String description,
	    FileSearchConfiguration fileSearchConfiguration)
	    throws AnalysisStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("repositories")
    public Collection<RepositoryTypeServiceInformation> getRepositoryTypes();
}
