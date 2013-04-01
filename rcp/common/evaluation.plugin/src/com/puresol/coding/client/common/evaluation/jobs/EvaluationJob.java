package com.puresol.coding.client.common.evaluation.jobs;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.client.common.evaluation.Activator;
import com.puresol.coding.client.common.ui.jobs.ObservedJob;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class EvaluationJob extends Job {

    private static final ILog logger = Activator.getDefault().getLog();

    private final AnalysisProject analysisProject;
    private final String projectName;
    private final AnalysisRun analysisRun;

    public EvaluationJob(AnalysisRun analysisRun) {
	super("");
	this.analysisRun = analysisRun;
	analysisProject = analysisRun.getInformation().getAnalysisProject();
	projectName = analysisProject.getSettings().getName();
	setName("Evaluation of project '" + projectName + "'");
    }

    @Override
    protected void canceling() {
	super.canceling();
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	Evaluators evaluators = Evaluators.createInstance();
	try {
	    return runEvaluators(monitor, evaluators);
	} finally {
	    IOUtils.closeQuietly(evaluators);
	}
    }

    private IStatus runEvaluators(IProgressMonitor monitor,
	    Evaluators evaluators) {
	List<EvaluatorFactory> evaluatorFactories = evaluators
		.getAllSortedByDependency();
	try {
	    monitor.beginTask("Running " + evaluatorFactories.size()
		    + " evaluation(s)", evaluatorFactories.size());
	    for (EvaluatorFactory factory : evaluatorFactories) {
		monitor.subTask("'" + factory.getName() + "' running");
		Evaluator evaluator = factory.create(analysisRun);
		ObservedJob<Evaluator, Boolean> observedJob = new ObservedJob<Evaluator, Boolean>(
			factory.getName(), evaluator);
		observedJob.schedule();
		observedJob.join();
		boolean successful = observedJob.getRunResult();
		if (!successful) {
		    monitor.setCanceled(true);
		    monitor.done();
		    return new Status(Status.ERROR, getClass().getName(),
			    "Run of evaluator '" + factory.getName()
				    + "' was not successful.");
		}
		monitor.worked(1);
	    }
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (InterruptedException e) {
	    logger.log(new Status(Status.WARNING, getClass().getName(),
		    "Evaluation was aborted.", e));
	    monitor.setCanceled(true);
	    monitor.done();
	    return Status.CANCEL_STATUS;
	}
    }
}
