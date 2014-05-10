package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisProject readAnalysisProject(
			AnalysisProjectInformation information)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID,
			UUID analysisRunUUID) throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRun readAnalysisRun(
			AnalysisRunInformation analysisRunInformation)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeAnalysisFileTree(UUID projectUUID, UUID analysisRunUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeAnalysisFileTree(
			ProgressObserver<AnalysisStore> progressObserver, UUID projectUUID,
			UUID analysisRunUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub

	}

}
