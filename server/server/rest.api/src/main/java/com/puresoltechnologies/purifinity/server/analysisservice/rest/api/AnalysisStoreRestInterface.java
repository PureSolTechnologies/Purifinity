package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

@Path("store/rest")
public interface AnalysisStoreRestInterface extends AnalysisStore {

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects")
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects")
	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
			throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}")
	public AnalysisProject readAnalysisProject(
			@PathParam("project_uuid") UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	@DELETE
	@Path("projects/{project_uuid}")
	public void removeAnalysisProject(
			@PathParam("project_uuid") UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/settings")
	public AnalysisProjectSettings readAnalysisProjectSettings(
			@PathParam("project_uuid") UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/information")
	public AnalysisProjectInformation readAnalysisProjectInformation(
			@PathParam("project_uuid") UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}")
	public void updateAnalysisProjectSettings(
			@PathParam("project_uuid") UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/runs")
	public List<AnalysisRunInformation> readAllRunInformation(
			@PathParam("project_uuid") UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/runs/{run_uuid}")
	public AnalysisRun readAnalysisRun(
			@PathParam("project_uuid") UUID projectUUID,
			@PathParam("run_uuid") UUID analysisRunUUID)
			throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/runs/{run_uuid}/information")
	public AnalysisRunInformation readAnalysisRunInformation(
			@PathParam("project_uuid") UUID projectUUID,
			@PathParam("run_uuid") UUID analysisRunUUID)
			throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/lastrun")
	public AnalysisRunInformation readLastAnalysisRun(
			@PathParam("project_uuid") UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	@DELETE
	@Path("projects/{project_uuid}/runs/{run_uuid}")
	public void removeAnalysisRun(@PathParam("project_uuid") UUID projectUUID,
			@PathParam("run_uuid") UUID runUUID) throws AnalysisStoreException;

	@Override
	@GET
	@Path("projects/{project_uuid}/runs/{run_uuid}/searchconfiguration")
	@Produces(MediaType.APPLICATION_JSON)
	public FileSearchConfiguration readSearchConfiguration(
			@PathParam("run_uuid") UUID runUUID) throws AnalysisStoreException;

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/runs/{run_uuid}/filetree")
	public AnalysisFileTree readAnalysisFileTree(
			@PathParam("project_uuid") UUID projectUUID,
			@PathParam("run_uuid") UUID runUUID) throws AnalysisStoreException;

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/runs/{run_uuid}/filetree")
	public void storeAnalysisFileTree(
			@PathParam("project_uuid") UUID projectUUID,
			@PathParam("run_uuid") UUID runUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException;

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("projects/{project_uuid}/create_run")
	public AnalysisRunInformation createAnalysisRun(
			@PathParam("project_uuid") UUID analysisProjectUUID,
			@QueryParam("startTime") Date startTime,
			@QueryParam("duration") long duration,
			@QueryParam("description") @DefaultValue("") String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException;
}
