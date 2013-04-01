package com.puresol.coding.client.common.analysis.jobs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.client.common.analysis.Activator;
import com.puresol.utils.progress.ProgressObserver;

public class AnalysisJob extends Job implements ProgressObserver<AnalysisRun> {

	private static final ILog logger = Activator.getDefault().getLog();

	private final ExecutorService executor = Executors
			.newSingleThreadExecutor();
	private final AnalysisProject analysisProject;
	private AnalysisRun analysisRun;
	private IProgressMonitor monitor = null;
	private Future<Boolean> future = null;

	public AnalysisJob(AnalysisProject analysisProject) {
		super(analysisProject.getSettings().getName());
		this.analysisProject = analysisProject;
	}

	/**
	 * This method returns the newly create {@link AnalysisRun} object. This can
	 * be used to trigger upstream calculation like evaluators.
	 * 
	 * @return An {@link AnalysisRun} object is returned.
	 */
	public AnalysisRun getAnalysisRun() {
		return analysisRun;
	}

	public AnalysisProject getAnalysis() {
		return analysisProject;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			this.monitor = monitor;

			analysisRun = analysisProject.createAnalysisRun();
			analysisRun.addObservable(this);

			future = executor.submit(analysisRun);
			executor.shutdown();
			executor.awaitTermination(1, TimeUnit.HOURS);
			return Status.OK_STATUS;
		} catch (OperationCanceledException e) {
			logger.log(new Status(Status.INFO, AnalysisJob.class.getName(),
					"Analysis was cancelled!", e));
			return Status.CANCEL_STATUS;
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
					"Error in directory store!", e));
			return Status.CANCEL_STATUS;
		} catch (InterruptedException e) {
			logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
					"Analysis was interrupted!", e));
			return Status.CANCEL_STATUS;
		}
	}

	@Override
	protected void canceling() {
		super.canceling();
		if (future != null) {
			future.cancel(true);
		}
	}

	@Override
	public void started(AnalysisRun observable, String message, long total) {
		monitor.beginTask(analysisProject.getSettings().getName(), (int) total);
		monitor.subTask(message);
	}

	@Override
	public void done(AnalysisRun observable, String message, boolean successful) {
		monitor.subTask(message);
		monitor.setCanceled(successful);
		monitor.done();
	}

	@Override
	public void updateWork(AnalysisRun observable, String message, long finished) {
		monitor.subTask(message);
		monitor.worked((int) finished);
	}

}
