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

import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

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
	private EventLoggerRemote eventLogger;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private AnalysisProcessStateTracker analysisProcessStateTracker;

	@Inject
	private EvaluatorServiceManager evaluatorPluginService;

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
					analysisRunInformation.getProjectId(),
					analysisRunInformation.getRunId(),
					AnalysisProcessTransition.START_EVALUATION);

			AnalysisFileTree analysisFileTree = analysisStore
					.readAnalysisFileTree(
							analysisRunInformation.getProjectId(),
							analysisRunInformation.getRunId());
			AnalysisRun analysisRun = new AnalysisRun(analysisRunInformation,
					analysisFileTree);
			evaluate(analysisRun);

			analysisProcessStateTracker.changeProcessState(
					analysisRunInformation.getProjectId(),
					analysisRunInformation.getRunId(),
					AnalysisProcessTransition.FINISH);
			analysisProcessStateTracker.stopProcess(analysisRunInformation
					.getProjectId());
		} catch (InterruptedException | EvaluationStoreException | JMSException
				| AnalysisStoreException | IOException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(ProjectAnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not evaluate the run.", e);
		}
	}

	/**
	 * 
	 * @param fileTree
	 * @throws EvaluationStoreException
	 * @throws InterruptedException
	 */
	private void evaluate(AnalysisRun analysisRun) throws InterruptedException,
			EvaluationStoreException {
		for (EvaluatorServiceInformation evaluatorInformation : evaluatorPluginService
				.getServicesSortedByDependency()) {
			if (evaluatorPluginService.isActive(evaluatorInformation.getId())) {
				logger.info("Starting evaluator "
						+ evaluatorInformation.getName() + "...");
				Evaluator evaluator = evaluatorPluginService
						.createProxy(evaluatorInformation.getJndiName());
				evaluator.evaluate(analysisRun, false);
			}
		}
	}
}
