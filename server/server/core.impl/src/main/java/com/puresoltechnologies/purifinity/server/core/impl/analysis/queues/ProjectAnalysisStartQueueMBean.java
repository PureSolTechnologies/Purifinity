package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.common.jms.JMSMessageSender;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

/**
 * This message driven beans gets all analysis requests for triggering the
 * analyzes. This bean's responsibility is to avoid collisions for analyzes of
 * same projects. If a project is already under analysis, the current request is
 * requeued.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@MessageDriven(name = "ProjectAnalysisStartQueueMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectAnalysisStartQueue.TYPE), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectAnalysisStartQueue.NAME), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectAnalysisStartQueueMBean implements MessageListener {

	@Inject
	private EventLogger eventLogger;

	@Resource(mappedName = ProjectFileStorageQueue.NAME)
	private Queue projectFileStorageQueue;

	@Inject
	private JMSMessageSender messageSender;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private AnalysisProcessStateTracker analysisProcessStateTracker;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			UUID uuid = UUID.fromString(textMessage.getText());

			AnalysisProject analysisProject = analysisStore
					.readAnalysisProject(uuid);
			eventLogger.logEvent(ProjectAnalysisEvents
					.createQueueAnalysisEvent(uuid, analysisProject
							.getSettings().getName()));

			// TODO the actual logic for the collision avoidance is still
			// missing.

			AnalysisRunInformation analysisRunInformation = analysisStore
					.createAnalysisRun(analysisProject.getInformation()
							.getUUID(), new Date(), 0, "", analysisProject
							.getSettings().getFileSearchConfiguration());

			Map<String, String> stringMap = new HashMap<>();
			stringMap.put("AnalysisProject",
					JSONSerializer.toJSONString(analysisProject));
			stringMap.put("AnalysisRunInformation",
					JSONSerializer.toJSONString(analysisRunInformation));

			analysisProcessStateTracker.changeProcessState(uuid,
					analysisRunInformation.getRunUUID(),
					AnalysisProcessTransition.QUEUE_FOR_STORAGE);
			messageSender.sendMessage(projectFileStorageQueue, stringMap);
		} catch (JMSException | AnalysisStoreException | IOException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not start the project analysis.",
					e);
		}
	}
}
