package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.List;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.job.PersistentStepUserData;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

@Named("EvaluationBatchlet")
public class EvaluationBatchlet implements Batchlet {

    @Inject
    private Logger logger;

    @Inject
    private AnalysisStore analysisStore;

    @Inject
    private EvaluatorServiceManager evaluatorPluginService;

    @Inject
    private JobContext jobContext;

    @Inject
    private StepContext stepContext;

    private boolean stopRequested = false;

    @Override
    public String process() throws Exception {
	AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext.getTransientUserData();

	AnalysisRunInformation analysisRunInformation = analysisJobContext.getAnalysisRunInformation();

	AnalysisFileTree analysisFileTree = analysisStore.readAnalysisFileTree(analysisRunInformation.getProjectId(),
		analysisRunInformation.getRunId());

	AnalysisRun analysisRun = new AnalysisRun(analysisRunInformation, analysisFileTree);
	evaluate(analysisRun);
	if (stopRequested) {
	    return AnalysisJobExitString.ABORT.get();
	}
	return AnalysisJobExitString.SUCCESSFUL.get();
    }

    /**
     * 
     * @param fileTree
     * @throws EvaluationStoreException
     * @throws InterruptedException
     */
    private void evaluate(AnalysisRun analysisRun) throws InterruptedException, EvaluationStoreException {
	List<EvaluatorServiceInformation> evaluators = evaluatorPluginService.getServicesSortedByDependency();

	PersistentStepUserData persistentUserData = new PersistentStepUserData("Evaluation", "Evaluation of project.",
		evaluators.size());
	stepContext.setPersistentUserData(persistentUserData);

	for (EvaluatorServiceInformation evaluatorInformation : evaluators) {
	    if (stopRequested) {
		break;
	    }
	    if (evaluatorPluginService.isActive(evaluatorInformation.getId())) {
		logger.info("Starting evaluator " + evaluatorInformation.getName() + "...");
		Evaluator evaluator = evaluatorPluginService.createProxy(evaluatorInformation.getJndiName());
		evaluator.evaluate(analysisRun, false);
	    }
	    persistentUserData.increaseCurrentItem(1);
	    stepContext.setPersistentUserData(persistentUserData);
	}
    }

    @Override
    public void stop() throws Exception {
	stopRequested = true;
    }
}
