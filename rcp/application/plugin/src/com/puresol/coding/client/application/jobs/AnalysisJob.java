package com.puresol.coding.client.application.jobs;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.client.application.Activator;

public class AnalysisJob extends Job {

    private static final ILog logger = Activator.getDefault().getLog();

    private final AnalysisProject analysis = null;

    public AnalysisJob(AnalysisProject analysis) {
	super(analysis.getInformation().getName());
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	try {
	    monitor.beginTask("Analysis of '" + getName() + "'", 1);

	    // AnalysisRun analysisRun = analysis.runAnalysis();
	    monitor.worked(1);
	    monitor.done();

	    // EvaluationJob job = new EvaluationJob(analysisRun);
	    // job.schedule();
	    return Status.OK_STATUS;
	} catch (OperationCanceledException e) {
	    logger.log(new Status(Status.INFO, AnalysisJob.class.getName(),
		    "Analysis was cancelled!", e));
	    return Status.CANCEL_STATUS;
	    // } catch (ModuleStoreException e) {
	    // logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
	    // "Error in directory store!", e));
	    // return Status.CANCEL_STATUS;
	    // } catch (InterruptedException e) {
	    // logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
	    // "Analysis was interrupted!", e));
	    // return Status.CANCEL_STATUS;
	}
    }

    public AnalysisProject getAnalysis() {
	return analysis;
    }
}
