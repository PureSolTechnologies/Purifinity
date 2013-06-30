package com.puresol.purifinity.client.common.evaluation;

import org.eclipse.core.runtime.jobs.Job;

import com.puresol.purifinity.client.common.osgi.AbstractStartup;
import com.puresol.purifinity.coding.evaluation.impl.Activator;

public class EvaluationImplStartup extends AbstractStartup {

	public EvaluationImplStartup() {
		super(Activator.class);
		Job.getJobManager().addJobChangeListener(new AnalysisJobListener());
	}

}
