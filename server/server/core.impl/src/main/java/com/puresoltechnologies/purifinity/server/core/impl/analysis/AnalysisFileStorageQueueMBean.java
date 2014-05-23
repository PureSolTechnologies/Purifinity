package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.util.UUID;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;

@MessageDriven(name = "AnalysisFileStorageQueueMBean",//
activationConfig = {//
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), //
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/queue/analysisFileStorageQueue"), //
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") //
}//
)
public class AnalysisFileStorageQueueMBean implements MessageListener {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			UUID uuid = UUID.fromString(textMessage.getText());
			logger.info("Start analysis for project '" + uuid + "'.");
		} catch (JMSException e) {
			logger.error("Could not store the project files.", e);
		}
	}
}
