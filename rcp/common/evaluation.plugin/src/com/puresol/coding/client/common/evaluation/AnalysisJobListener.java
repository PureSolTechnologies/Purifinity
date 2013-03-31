package com.puresol.coding.client.common.evaluation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.client.common.analysis.jobs.AnalysisJob;

public class AnalysisJobListener extends JobChangeAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisJobListener.class);

	@Override
	public void done(IJobChangeEvent event) {
		super.done(event);
		Job job = event.getJob();
		if (job.getClass().equals(AnalysisJob.class)) {
			IStatus result = event.getResult();
			if (result.isOK()) {
				logger.info("AnalysisJob finished with OK.");
			} else {
				logger.info("AnalysisJob finished with NOK.");
			}
		}
	}

}
