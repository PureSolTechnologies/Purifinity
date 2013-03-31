package com.puresol.coding.client.common.evaluation;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.client.common.analysis.jobs.AnalysisJob;
import com.puresol.coding.client.common.evaluation.jobs.EvaluationJob;

public class AnalysisJobListener extends JobChangeAdapter {

    private static final Logger logger = LoggerFactory
	    .getLogger(AnalysisJobListener.class);

    @Override
    public void done(IJobChangeEvent event) {
	super.done(event);
	Job job = event.getJob();
	if (job.getClass().equals(AnalysisJob.class)) {
	    String name = job.getName();
	    if (event.getResult().isOK()) {
		logger.info("AnalysisJob '"
			+ name
			+ "' was finished and was OK. Tiggering evaluations....");
		AnalysisJob analysisJob = (AnalysisJob) job;
		AnalysisRun analysisRun = analysisJob.getAnalysisRun();
		EvaluationJob evaluationJob = new EvaluationJob(analysisRun);
		evaluationJob.schedule();
	    } else {
		logger.info("AnalysisJob '"
			+ name
			+ "'was finished, but was not OK. So no evaluation job is triggered.");
	    }
	}
    }

}
