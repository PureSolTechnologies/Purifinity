package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.common.jms.JMSMessageSender;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.RepositoryLocationCreator;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

/**
 * This message driven bean is the storage stage of the analysis. Here the
 * project files are read and stored into the file store.
 * 
 * @author Rick-Rainer Ludwig
 */
@MessageDriven(name = "AnalysisFileStorageQueueMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectFileStorageQueue.TYPE), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectFileStorageQueue.NAME), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectFileStorageQueueMDBean implements MessageListener {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Resource(mappedName = ProjectAnalysisQueue.NAME)
	private Queue projectAnalysisQueue;

	@Inject
	private JMSMessageSender messageSender;

	@Inject
	private AnalysisStoreService analysisStoreService;

	@Inject
	private FileStoreService fileStore;

	@Inject
	private AnalysisProcessStateTracker analysisProcessStateTracker;

	@Override
	public void onMessage(Message message) {
		try {
			MapMessage mapMessage = (MapMessage) message;
			AnalysisProject analysisProject = JSONSerializer.fromJSONString(
					mapMessage.getString("AnalysisProject"),
					AnalysisProject.class);
			AnalysisRunInformation analysisRunInformation = JSONSerializer
					.fromJSONString(
							mapMessage.getString("AnalysisRunInformation"),
							AnalysisRunInformation.class);

			analysisProcessStateTracker.changeProcessState(
					analysisRunInformation.getProjectUUID(),
					analysisRunInformation.getRunUUID(),
					AnalysisProcessTransition.START_STORAGE);

			Map<SourceCodeLocation, HashId> storedSources = storeFilesInStore(analysisProject
					.getSettings());
			AnalysisRunFileTree fileTree = analysisStoreService
					.createAndStoreFileAndContentTree(
							analysisRunInformation.getProjectUUID(),
							analysisRunInformation.getRunUUID(),
							analysisProject.getSettings().getName(),
							storedSources);

			Map<String, String> stringMap = new HashMap<>();
			stringMap.put("AnalysisProject",
					JSONSerializer.toJSONString(analysisProject));
			stringMap.put("AnalysisRunInformation",
					JSONSerializer.toJSONString(analysisRunInformation));
			stringMap.put("AnalysisRunFileTree",
					JSONSerializer.toJSONString(fileTree));

			analysisProcessStateTracker.changeProcessState(
					analysisRunInformation.getProjectUUID(),
					analysisRunInformation.getRunUUID(),
					AnalysisProcessTransition.QUEUE_FOR_ANALYSIS);
			messageSender.sendMessage(projectAnalysisQueue, stringMap);
		} catch (JMSException | IOException | AnalysisStoreException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not store the project files.", e);
		}
	}

	/**
	 * Searches the source code repository for source files and stores them in
	 * the database.
	 * 
	 * @param projectSettings
	 *            is a {@link AnalysisProjectSettings} object representing the
	 *            analysis project.
	 * @return A {@link Map} is returned containing a mapping of the
	 *         {@link SourceCodeLocation}s to the {@link HashId}s calculated
	 *         during storage.
	 */
	private Map<SourceCodeLocation, HashId> storeFilesInStore(
			AnalysisProjectSettings projectSettings) {
		logger.debug("Start file storage for project '"
				+ projectSettings.getName() + "'.");
		Map<SourceCodeLocation, HashId> storedSources = new HashMap<SourceCodeLocation, HashId>();
		RepositoryLocation repository = RepositoryLocationCreator
				.createFromSerialization(projectSettings
						.getRepositoryLocation());
		List<SourceCodeLocation> sourceCodeLocations = repository
				.getSourceCodes(projectSettings.getFileSearchConfiguration());
		for (SourceCodeLocation sourceCodeLocation : sourceCodeLocations) {
			HashId hashId = storeFile(projectSettings, repository,
					sourceCodeLocation);
			storedSources.put(sourceCodeLocation, hashId);
		}
		return storedSources;
	}

	/**
	 * Stores a single file in database.
	 * 
	 * @param projectSettings
	 * @param repository
	 * @param sourceCodeLocation
	 * @return
	 */
	private HashId storeFile(AnalysisProjectSettings projectSettings,
			RepositoryLocation repository, SourceCodeLocation sourceCodeLocation) {
		try (InputStream stream = sourceCodeLocation.openStream()) {
			HashId hashId = fileStore.storeRawFile(stream);
			eventLogger.logEvent(ProjectAnalysisEvents
					.createRawFileStoredEvent(
							sourceCodeLocation.getInternalLocation(), hashId,
							projectSettings.getName(),
							repository.getHumanReadableLocationString()));
			return hashId;
		} catch (FileStoreException | IOException e) {
			eventLogger.logEvent(ProjectAnalysisEvents.createRawFileStoreError(
					sourceCodeLocation.getInternalLocation(),
					projectSettings.getName(),
					repository.getHumanReadableLocationString(), e));
			throw new RuntimeException("Error storing file '"
					+ sourceCodeLocation.getHumanReadableLocationString()
					+ "'.");
		}
	}
}
