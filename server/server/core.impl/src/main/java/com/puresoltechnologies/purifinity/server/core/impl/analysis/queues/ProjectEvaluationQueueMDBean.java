package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@MessageDriven(name = "ProjectEvaluationQueueMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectEvaluationQueue.TYPE), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectEvaluationQueue.NAME), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectEvaluationQueueMDBean implements MessageListener {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private AnalysisStore analysisStore;

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
					AnalysisProcessTransition.START_EVALUATION);

			// FIXME: Implement evaluation here...

			analysisProcessStateTracker.changeProcessState(
					analysisRunInformation.getProjectUUID(),
					analysisRunInformation.getRunUUID(),
					AnalysisProcessTransition.FINISH);
			analysisProcessStateTracker.stopProcess(analysisRunInformation
					.getProjectUUID());
		} catch (JMSException | IOException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not analyze the project.", e);
		}
	}
}
