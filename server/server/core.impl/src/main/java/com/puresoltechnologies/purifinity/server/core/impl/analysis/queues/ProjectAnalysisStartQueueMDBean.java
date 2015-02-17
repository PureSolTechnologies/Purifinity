package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.common.jms.JMSMessageSender;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

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
public class ProjectAnalysisStartQueueMDBean implements MessageListener {

    @Inject
    private EventLoggerRemote eventLogger;

    @Resource(mappedName = ProjectFileStorageQueue.NAME)
    private Queue projectFileStorageQueue;

    @Resource(mappedName = ProjectAnalysisStartQueue.NAME)
    private Queue projectAnalysisStartQueue;

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
	    String projectId = textMessage.getText();

	    if (analysisProcessStateTracker.readProcessState(projectId) != null) {
		/*
		 * There is already a process running for the project, so we
		 * re-queue here.
		 */
		messageSender.sendMessageWithDelay(projectAnalysisStartQueue,
			projectId.toString(), 60000);
		/*
		 * We finish here and let the re-delivery do the job.
		 */
		return;
	    }
	    analysisProcessStateTracker.startProcess(projectId);

	    AnalysisProject analysisProject = analysisStore
		    .readAnalysisProject(projectId);
	    eventLogger.logEvent(ProjectAnalysisEvents
		    .createQueueAnalysisEvent(projectId, analysisProject
			    .getSettings().getName()));

	    // TODO the actual logic for the collision avoidance is still
	    // missing.

	    AnalysisRunInformation analysisRunInformation = analysisStore
		    .createAnalysisRun(analysisProject.getInformation()
			    .getProjectId(), new Date(), 0, "", analysisProject
			    .getSettings().getFileSearchConfiguration());

	    Map<String, String> stringMap = new HashMap<>();
	    stringMap.put("AnalysisProject",
		    JSONSerializer.toJSONString(analysisProject));
	    stringMap.put("AnalysisRunInformation",
		    JSONSerializer.toJSONString(analysisRunInformation));

	    analysisProcessStateTracker.changeProcessState(projectId,
		    analysisRunInformation.getRunId(),
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
