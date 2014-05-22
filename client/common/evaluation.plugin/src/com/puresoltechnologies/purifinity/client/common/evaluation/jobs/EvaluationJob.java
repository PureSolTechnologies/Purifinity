package com.puresoltechnologies.purifinity.client.common.evaluation.jobs;

import java.util.List;
import java.util.UUID;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.Bundle;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.server.Evaluators;
import com.puresoltechnologies.purifinity.client.common.ui.jobs.ObservedJob;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisStoreClient;

public class EvaluationJob extends Job {

	private static final ILog logger = Activator.getDefault().getLog();

	private final AnalysisRun analysisRun;
	private final boolean reEvaluation;
	private final String projectName;

	private ObservedJob<Evaluator, Boolean> currentJob = null;

	public EvaluationJob(AnalysisRun analysisRun) {
		this(analysisRun, false);
	}

	public EvaluationJob(AnalysisRun analysisRun, boolean reEvaluation) {
		super("Project Evaluation");
		this.analysisRun = analysisRun;
		this.reEvaluation = reEvaluation;
		AnalysisProjectSettings analysisProjectSettings = null;
		try {
			AnalysisRunInformation information = analysisRun.getInformation();
			UUID projectUUID = information.getProjectUUID();
			analysisProjectSettings = AnalysisStoreClient.getInstance()
					.readAnalysisProjectSettings(projectUUID);
		} catch (AnalysisStoreException e) {
			Activator activator = Activator.getDefault();
			Bundle bundle = activator.getBundle();
			activator.getLog().log(
					new Status(Status.ERROR, bundle.getSymbolicName(),
							"Could not read results.", e));
		}
		projectName = analysisProjectSettings != null ? analysisProjectSettings
				.getName() : "<no name>";
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
		try (Evaluators evaluators = Evaluators.createInstance()) {
			return runEvaluators(monitor, evaluators);
		}
	}

	private IStatus runEvaluators(IProgressMonitor monitor,
			Evaluators evaluators) {
		List<EvaluatorFactory> evaluatorFactories = evaluators
				.getAllSortedByDependency();
		try {
			monitor.beginTask(projectName + " / " + evaluatorFactories.size()
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
