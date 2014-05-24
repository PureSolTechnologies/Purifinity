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
import com.puresoltechnologies.commons.trees.TreePrinter;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@MessageDriven(name = "ProjectAnalysisQueueMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = ProjectAnalysisQueue.TYPE), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = ProjectAnalysisQueue.NAME), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class ProjectAnalysisQueueMBean implements MessageListener {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	@Inject
	private AnalysisStoreService analysisStore;

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
			AnalysisRunFileTree analysisRunFileTree = JSONSerializer
					.fromJSONString(
							mapMessage.getString("AnalysisRunFileTree"),
							AnalysisRunFileTree.class);

			System.out.println("Start analysis for project '"
					+ analysisProject.getSettings().getName() + "' and run '"
					+ analysisRunInformation.getRunUUID() + "'.");
			new TreePrinter(System.out).println(analysisRunFileTree);

			AnalysisProjectSettings projectSettings = analysisProject
					.getSettings();
			logger.info("Start analysis for project '"
					+ projectSettings.getName() + "'.");
		} catch (JMSException | IOException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not analyze the project.", e);
		}
	}
}
