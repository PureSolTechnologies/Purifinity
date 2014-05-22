package com.puresoltechnologies.purifinity.client.common.analysis.jobs;

import java.util.concurrent.Future;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisServiceClient;

public class AnalysisJob extends Job implements ProgressObserver<Runnable> {

	private final AnalysisProjectInformation analysisProjectInformation;
	private final AnalysisProjectSettings analysisProjectSettings;
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
		monitor.beginTask("Starting a new analysis run...", 1);
		AnalysisServiceClient.getInstance().triggerNewAnalysisRun(
				analysisProjectInformation.getUUID());
		monitor.done();
		return Status.OK_STATUS;
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
