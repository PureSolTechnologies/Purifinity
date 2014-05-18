package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestInterface;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;

public class AnalysisStoreRestService implements AnalysisStoreRestInterface {

	@Inject
	private AnalysisStoreService analysisStore;

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		return analysisStore.createAnalysisProject(settings);
	}

	@Override
	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
			throws AnalysisStoreException {
		return analysisStore.readAllAnalysisProjectInformation();
	}

	@Override
	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) throws AnalysisStoreException {
		return analysisStore.readAnalysisProjectInformation(projectUUID);
	}

	@Override
	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		analysisStore.removeAnalysisProject(projectUUID);
	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID projectUUID) throws AnalysisStoreException {
		return analysisStore.readAnalysisProjectSettings(projectUUID);
	}

	@Override
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		analysisStore.updateAnalysisProjectSettings(projectUUID, settings);
	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		return analysisStore.readAllRunInformation(projectUUID);
	}

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID,
			UUID analysisRunUUID) throws AnalysisStoreException {
		return analysisStore.readAnalysisRun(projectUUID, analysisRunUUID);
	}

	@Override
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		return analysisStore.readLastAnalysisRun(projectUUID);
	}

	@Override
	public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		analysisStore.removeAnalysisRun(projectUUID, analysisRunUUID);
	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID)
			throws AnalysisStoreException {
		return analysisStore.readSearchConfiguration(analysisRunUUID);
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		return analysisStore.readAnalysisFileTree(projectUUID, runUUID);
	}

}
