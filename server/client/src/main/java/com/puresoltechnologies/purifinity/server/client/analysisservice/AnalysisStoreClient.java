package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.AbstractRestClient;

public class AnalysisStoreClient extends
		AbstractRestClient<AnalysisStoreRestInterface> {

	private static final AnalysisStoreClient INSTANCE;
	static {
		try {
			INSTANCE = new AnalysisStoreClient();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Unknown URI for Analysis Service.", e);
		}
	}

	public static AnalysisStoreClient getInstance() {
		return INSTANCE;
	}

	private AnalysisStoreClient() throws URISyntaxException {
		super(new URI("http://localhost:8080/purifinityserver/rest"),
				AnalysisStoreRestInterface.class);
	}

	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		return getProxy().createAnalysisProject(settings);
	}

	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
			throws AnalysisStoreException {
		return getProxy().readAllAnalysisProjectInformation();
	}

	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) throws AnalysisStoreException {
		return getProxy().readAnalysisProjectInformation(projectUUID);
	}

	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		getProxy().removeAnalysisProject(projectUUID);
	}

	public AnalysisProjectSettings readAnalysisProjectSettings(UUID projectUUID)
			throws AnalysisStoreException {
		return getProxy().readAnalysisProjectSettings(projectUUID);
	}

	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		getProxy().updateAnalysisProjectSettings(projectUUID, settings);
	}

	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreException {
		return getProxy().readAllRunInformation(projectUUID);
	}

	public AnalysisProject readAnalysisProject(UUID projectUUID)
			throws AnalysisStoreException {
		return getProxy().readAnalysisProject(projectUUID);
	}

	public AnalysisRun readAnalysisRun(UUID projectUUID, UUID analysisRunUUID)
			throws AnalysisStoreException {
		return getProxy().readAnalysisRun(projectUUID, analysisRunUUID);
	}

	public AnalysisRunInformation readAnalysisRunInformation(UUID projectUUID,
			UUID analysisRunUUID) throws AnalysisStoreException {
		return getProxy().readAnalysisRunInformation(projectUUID,
				analysisRunUUID);
	}

	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreException {
		return getProxy().readLastAnalysisRun(projectUUID);
	}

	public void removeAnalysisRun(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		getProxy().removeAnalysisRun(projectUUID, runUUID);
	}

	public FileSearchConfiguration readSearchConfiguration(UUID runUUID)
			throws AnalysisStoreException {
		return getProxy().readSearchConfiguration(runUUID);
	}

	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		return getProxy().readAnalysisFileTree(projectUUID, runUUID);
	}

}
