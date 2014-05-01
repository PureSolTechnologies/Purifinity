package com.puresoltechnologies.purifinity.server.analysisservice.core.impl.store;

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
import com.puresoltechnologies.purifinity.framework.analysis.impl.store.AnalysisStoreImpl;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.Event;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventSeverity;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventType;

public class AnalysisStoreServiceBean implements AnalysisStoreService {

	public static final String CASSANDRA_HOST = "localhost";
	public static final int CASSANDRA_CQL_PORT = 9042;
	public static final String KEYSPACE_NAME = "analysis_store";
	public static final String COMPONENT_NAME = "AnalysisStoreService";

	@Inject
	private EventLogger eventLogger;

	private final AnalysisStore analysisStore = new AnalysisStoreImpl();

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreException {
		eventLogger
				.logEvent(new Event(COMPONENT_NAME, 0x01,
						EventType.USER_ACTION, EventSeverity.INFO,
						"Create project..."));
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
			UUID analysisProjectUUID) throws AnalysisStoreException {
		return analysisStore.readAnalysisProjectSettings(analysisProjectUUID);
	}

	@Override
	public AnalysisProject readAnalysisProject(
			AnalysisProjectInformation information)
			throws AnalysisStoreException {
		return analysisStore.readAnalysisProject(information);
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
	public AnalysisRun readAnalysisRun(
			AnalysisRunInformation analysisRunInformation)
			throws AnalysisStoreException {
		return analysisStore.readAnalysisRun(analysisRunInformation);
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
	public void storeAnalysisFileTree(UUID projectUUID, UUID analysisRunUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException {
		analysisStore.storeAnalysisFileTree(projectUUID, analysisRunUUID,
				fileTree);
	}

	@Override
	public void storeAnalysisFileTree(
			ProgressObserver<AnalysisStore> progressObserver, UUID projectUUID,
			UUID analysisRunUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException {
		analysisStore.storeAnalysisFileTree(projectUUID, analysisRunUUID,
				fileTree);
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreException {
		return analysisStore.readAnalysisFileTree(projectUUID, runUUID);
	}

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException {
		return analysisStore.createAnalysisRun(analysisProjectUUID, startTime,
				duration, description, fileSearchConfiguration);
	}

}
