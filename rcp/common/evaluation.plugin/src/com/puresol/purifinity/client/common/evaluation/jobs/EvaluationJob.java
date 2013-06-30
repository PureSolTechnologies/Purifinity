package com.puresol.purifinity.client.common.evaluation.jobs;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.purifinity.client.common.evaluation.Activator;
import com.puresol.purifinity.client.common.ui.jobs.ObservedJob;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluators;

public class EvaluationJob extends Job {

    private static final ILog logger = Activator.getDefault().getLog();

    private final AnalysisProject analysisProject;
    private final String projectName;
    private final AnalysisRun analysisRun;
    private final boolean reEvaluation;

    private ObservedJob<Evaluator, Boolean> currentJob = null;

    public EvaluationJob(AnalysisRun analysisRun) {
	this(analysisRun, false);
    }

    public EvaluationJob(AnalysisRun analysisRun, boolean reEvaluation) {
	super("");
	this.analysisRun = analysisRun;
	this.reEvaluation = reEvaluation;
	analysisProject = analysisRun.getInformation().getAnalysisProject();
	projectName = analysisProject.getSettings().getName();
	setName("Evaluation of project '" + projectName + "'");
    }

    @Override
    protected void canceling() {
	super.canceling();
	if (currentJob != null) {
	    currentJob.cancel();
	}
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
		evaluator.setReEvaluation(reEvaluation);
		currentJob = new ObservedJob<Evaluator, Boolean>(
			factory.getName(), evaluator);
		currentJob.schedule();
		currentJob.join();
		Boolean successful = currentJob.getRunResult();
		if ((successful == null) || (!successful)) {
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
