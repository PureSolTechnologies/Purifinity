package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.util.UUID;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

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
@MessageDriven(name = "ProjectAnalysisStartDLQMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectAnalysisStartDLQ.TYPE), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectAnalysisStartDLQ.NAME), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectAnalysisStartDLQMBean implements MessageListener {

	@Inject
	private EventLogger eventLogger;
	@Inject
	private AnalysisProcessStateTracker analysisProcessStateTracker;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			UUID uuid = UUID.fromString(textMessage.getText());

			analysisProcessStateTracker.changeProcessState(uuid, null,
					AnalysisProcessTransition.FAIL);
		} catch (JMSException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
		}
	}
}
