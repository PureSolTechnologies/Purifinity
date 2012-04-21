package com.puresol.coding.client.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class EvaluationJob extends Job {

    private final AnalysisRun analysisRun;

    public EvaluationJob(AnalysisRun analysisRun) {
	super("Evalutions of '"
		+ analysisRun.getInformation().getAnalysisInformation()
			.getName() + "'");
	this.analysisRun = analysisRun;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	List<EvaluatorFactory> evaluatorFactories = Evaluators
		.getAllFactories();
	monitor.beginTask("Running evaluations for '" + getName() + "'",
		evaluatorFactories.size());
	for (EvaluatorFactory factory : evaluatorFactories) {
	    try {
		Thread.sleep(1000);
		monitor.worked(1);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	monitor.done();
	return Status.OK_STATUS;
    }

}
