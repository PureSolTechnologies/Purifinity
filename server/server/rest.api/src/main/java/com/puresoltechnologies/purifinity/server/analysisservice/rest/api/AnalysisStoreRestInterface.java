package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
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
	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) throws AnalysisStoreException;

	@Override
	public AnalysisProject readAnalysisProject(
			AnalysisProjectInformation information)
			throws AnalysisStoreException;

	@Override
	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) throws AnalysisStoreException;

	@Override
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException;

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID,
			UUID analysisRunUUID) throws AnalysisStoreException;

	@Override
	public AnalysisRun readAnalysisRun(
			AnalysisRunInformation analysisRunInformation)
			throws AnalysisStoreException;

	@Override
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException;

	@Override
	public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException;

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID)
			throws AnalysisStoreException;

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException;

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException;

	@Override
	public void storeAnalysisFileTree(UUID projectUUID, UUID analysisRunUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException;

	@Override
	public void storeAnalysisFileTree(
			ProgressObserver<AnalysisStore> progressObserver, UUID projectUUID,
			UUID analysisRunUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException;
}
