package com.puresol.coding.client.common.evaluation.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.UIJob;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.ui.jobs.ObservedJob;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class EvaluationTool {

	public static synchronized void putAsynchronous(
			final EvaluationsTarget target,
			final EvaluatorFactory evaluatorFactory,
			final AnalysisRun analysisRun, final HashIdFileTree path) {
		Job job = new Job("Calculate Evaluation...") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Evaluator evaluator = evaluatorFactory
						.create(analysisRun, path);
				ObservedJob<Evaluator, Boolean> job = new ObservedJob<Evaluator, Boolean>(
						"Evaluation", evaluator);
				job.schedule();
				try {
					job.join();
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				}
				UIJob uiJob = new UIJob("Update view") {
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						target.showEvaluation(path);
						return Status.OK_STATUS;
					}
				};
				uiJob.schedule();
				try {
					uiJob.join();
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}
}
