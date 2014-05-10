package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.puresoltechnologies.purifinity.server.common.rest.AbstractRestClient;

public class AnalysisStoreClient extends
		AbstractRestClient<AnalysisStoreRestInterface> implements AnalysisStore {

	public static AnalysisStoreClient getInstance() {
		return new AnalysisStoreClient();
	}

	private AnalysisStoreClient() {
		super(AnalysisStoreRestInterface.class);
	}

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		return getProxy().createAnalysisProject(settings);
	}

	@Override
	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
			throws AnalysisStoreException {
		return getProxy().readAllAnalysisProjectInformation();
	}

	@Override
	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAnalysisProject(UUID projectUUID) {
		// TODO Auto-generated method stub

	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) {
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
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID,
			UUID analysisRunUUID) {
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
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID) {
		// TODO Auto-generated method stub

	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration) {
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
