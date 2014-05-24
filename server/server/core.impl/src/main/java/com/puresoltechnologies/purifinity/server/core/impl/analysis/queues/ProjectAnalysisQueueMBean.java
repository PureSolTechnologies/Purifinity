package com.puresoltechnologies.purifinity.server.core.impl.analysis.queues;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;

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
	private AnalysisStoreService analysisStore;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			AnalysisRunInformation analysisRun = JSONSerializer.fromJSONString(
					textMessage.getText(), AnalysisRunInformation.class);
			AnalysisProjectSettings projectSettings = analysisStore
					.readAnalysisProjectSettings(analysisRun.getProjectUUID());
			logger.info("Start analysis for project '"
					+ projectSettings.getName() + "'.");
		} catch (JMSException | IOException | AnalysisStoreException e) {
			logger.error("Could not store the project files.", e);
		}
	}
}
