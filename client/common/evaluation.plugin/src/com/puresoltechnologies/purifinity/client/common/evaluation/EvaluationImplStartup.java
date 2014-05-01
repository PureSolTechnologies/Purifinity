package com.puresoltechnologies.purifinity.client.common.evaluation;

import org.eclipse.core.runtime.jobs.Job;

import com.puresoltechnologies.purifinity.client.common.osgi.AbstractStartup;

public class EvaluationImplStartup extends AbstractStartup {

	public EvaluationImplStartup() {
		super(Activator.class);
		Job.getJobManager().addJobChangeListener(new AnalysisJobListener());
	}

}
