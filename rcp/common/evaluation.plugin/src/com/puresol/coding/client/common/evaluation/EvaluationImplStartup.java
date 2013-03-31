package com.puresol.coding.client.common.evaluation;

import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.client.common.osgi.AbstractStartup;
import com.puresol.coding.evaluation.impl.Activator;

public class EvaluationImplStartup extends AbstractStartup {

	public EvaluationImplStartup() {
		super(Activator.class);
		Job.getJobManager().addJobChangeListener(new AnalysisJobListener());
	}

}
