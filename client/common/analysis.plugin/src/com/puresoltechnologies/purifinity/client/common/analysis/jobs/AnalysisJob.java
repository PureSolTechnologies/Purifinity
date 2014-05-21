package com.puresoltechnologies.purifinity.client.common.analysis.jobs;

import java.util.concurrent.Future;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.framework.commons.utils.NotImplementedException;

public class AnalysisJob extends Job implements ProgressObserver<Runnable> {

	private static final ILog logger = Activator.getDefault().getLog();

	private static final int RUN_TIMEOUT_IN_SECONDS = 3600;

	private final AnalysisProjectInformation analysisProjectInformation;
	private final AnalysisProjectSettings analysisProjectSettings;
	private Runnable analysisRunner;
	private AnalysisRun analysisRun;
	private final IProgressMonitor monitor = null;
	private final Future<Boolean> future = null;

	public AnalysisJob(AnalysisProjectInformation analysisProjectInformation,
			AnalysisProjectSettings analysisProjectSettings) {
		super("Project Analysis");
		this.analysisProjectInformation = analysisProjectInformation;
		this.analysisProjectSettings = analysisProjectSettings;
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

	public AnalysisProjectSettings getAnalysisSettings() {
		return analysisProjectSettings;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		throw new NotImplementedException("Analysis Job");
		// FIXME
		// try {
		// this.monitor = monitor;
		// analysisRunner = new AnalysisRunnerImpl(
		// analysisProjectInformation.getUUID());
		// analysisRunner.addObserver(this);
		//
		// ExecutorService executor = Executors.newSingleThreadExecutor();
		// future = executor.submit(analysisRunner);
		// executor.shutdown();
		// executor.awaitTermination(RUN_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		// try {
		// Boolean result = future.get();
		// if ((result != null) && (result)) {
		// analysisRun = analysisRunner.getAnalysisRun();
		// return Status.OK_STATUS;
		// } else {
		// return Status.CANCEL_STATUS;
		// }
		// } catch (ExecutionException e) {
		// logger.log(new Status(Status.ERROR,
		// AnalysisJob.class.getName(),
		// "Analysis finished with an exception!", e));
		// throw new RuntimeException("Analysis was not successful.");
		// }
		// } catch (OperationCanceledException e) {
		// logger.log(new Status(Status.INFO, AnalysisJob.class.getName(),
		// "Analysis was cancelled!", e));
		// return Status.CANCEL_STATUS;
		// } catch (InterruptedException e) {
		// logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
		// "Analysis was interrupted!", e));
		// return Status.CANCEL_STATUS;
		// } catch (AnalysisProjectException e) {
		// logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
		// "Analysis project has issues!", e));
		// return Status.CANCEL_STATUS;
		// } catch (AnalysisStoreException e) {
		// logger.log(new Status(Status.ERROR, AnalysisJob.class.getName(),
		// "Analysis store has issues!", e));
		// return Status.CANCEL_STATUS;
		// }
	}

	@Override
	protected void canceling() {
		super.canceling();
		if (future != null) {
			future.cancel(true);
		}
	}

	@Override
	public void started(Runnable observable, String message, long total) {
		monitor.beginTask(analysisProjectSettings.getName(), (int) total);
		monitor.subTask(message);
	}

	@Override
	public void done(Runnable observable, String message, boolean successful) {
		monitor.subTask(message);
		monitor.done();
	}

	@Override
	public void updateWork(Runnable observable, String message, long finished) {
		monitor.subTask(message);
		monitor.worked((int) finished);
	}

}
