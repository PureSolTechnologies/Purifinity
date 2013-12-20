package com.puresoltechnologies.purifinity.client.common.evaluation;

import org.eclipse.core.runtime.jobs.Job;

import com.puresoltechnologies.purifinity.client.common.osgi.AbstractStartup;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Activator;

public class EvaluationImplStartup extends AbstractStartup {

	public EvaluationImplStartup() {
		super(Activator.class);
		Job.getJobManager().addJobChangeListener(new AnalysisJobListener());
	}

}
