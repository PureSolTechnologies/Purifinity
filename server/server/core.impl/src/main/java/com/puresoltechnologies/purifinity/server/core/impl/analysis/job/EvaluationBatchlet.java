package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

@Named("EvaluationBatchlet")
@TransactionManagement(TransactionManagementType.BEAN)
public class EvaluationBatchlet implements Batchlet {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private EvaluatorServiceManager evaluatorPluginService;

	@Inject
	private JobContext jobContext;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String process() throws Exception {
		AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext
				.getTransientUserData();

		AnalysisRunInformation analysisRunInformation = analysisJobContext
				.getAnalysisRunInformation();

		AnalysisFileTree analysisFileTree = analysisStore.readAnalysisFileTree(
				analysisRunInformation.getProjectId(),
				analysisRunInformation.getRunId());

		AnalysisRun analysisRun = new AnalysisRun(analysisRunInformation,
				analysisFileTree);
		evaluate(analysisRun);
		return "SUCCESS";
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

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
	}
}
