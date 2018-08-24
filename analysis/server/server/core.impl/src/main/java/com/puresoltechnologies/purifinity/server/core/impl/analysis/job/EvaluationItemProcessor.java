package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
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

@Named("EvaluationItemProcessor")
public class EvaluationItemProcessor implements ItemProcessor {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private EvaluatorServiceManager evaluatorPluginService;

	@Inject
	private JobContext jobContext;

	@Override
	public Object processItem(Object item) throws Exception {
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
}
