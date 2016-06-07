package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.Date;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.ProjectManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;

@Named("WriteStatisticsBatchlet")
public class WriteStatisticsBatchlet implements Batchlet {

    @Inject
    private Logger logger;

    @Inject
    private ProjectManager analysisStore;

    @Inject
    private EvaluatorServiceManager evaluatorPluginService;

    @Inject
    private JobContext jobContext;

    @Override
    public String process() throws Exception {
	AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext.getTransientUserData();
	AnalysisProject analysisProject = analysisJobContext.getAnalysisProject();
	AnalysisRunInformation analysisRunInformation = analysisJobContext.getAnalysisRunInformation();
	Date startTime = analysisJobContext.getStartTime();
	Date stopTime = new Date();
	analysisStore.updateAnalysisRunInformation(analysisProject.getInformation().getProjectId(),
		analysisRunInformation.getRunId(), (stopTime.getTime() - startTime.getTime()));
	return "SUCCESS";
    }

    @Override
    public void stop() throws Exception {
	// Intentionally left empty.
    }
}
