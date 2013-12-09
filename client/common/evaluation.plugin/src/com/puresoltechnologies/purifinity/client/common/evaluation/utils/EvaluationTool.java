package com.puresoltechnologies.purifinity.client.common.evaluation.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.UIJob;

import com.puresoltechnologies.commons.HashId;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.client.common.ui.jobs.ObservedJob;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStoreFactory;

/**
 * This class contains helpers to handle evaluation in RCP application.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EvaluationTool {

	/**
	 * This method is used to show an evaluation in an asynchronous way. This is
	 * used to check for the existence of an evaluation before it can be shown.
	 * The calculation is started if needed. It is assumed, that this method is
	 * called out of the UI thread.
	 * 
	 * @param target
	 *            is the {@link EvaluationsTarget} which is used to show the
	 *            evaluation.
	 * @param evaluatorFactory
	 *            is the {@link EvaluatorFactory} of the evaluation to be shown.
	 * @param analysisRun
	 *            is the {@link AnalysisRun} which is used to calculate the
	 *            evaluation on.
	 * @param path
	 *            is the path to be shown.
	 */
	public static synchronized void showEvaluationAsynchronous(
			final EvaluationsTarget target,
			final EvaluatorFactory evaluatorFactory,
			final AnalysisRun analysisRun, final HashIdFileTree path) {
		boolean hasResults = checkForResults(evaluatorFactory, path);
		if (hasResults) {
			showSynchronous(target, path);
		} else {
			calculateMetricAndShowAsynchronous(target, evaluatorFactory,
					analysisRun, path);
		}
	}

	/**
	 * This method checks for the presence of results.
	 * 
	 * @param evaluatorFactory
	 * @param path
	 * @return
	 */
	private static boolean checkForResults(EvaluatorFactory evaluatorFactory,
			HashIdFileTree path) {
		EvaluatorStoreFactory evaluatorStoreFactory = EvaluatorStoreFactory
				.getFactory();
		Class<? extends Evaluator> evaluatorClass = evaluatorFactory
				.getEvaluatorClass();
		EvaluatorStore store = evaluatorStoreFactory
				.createInstance(evaluatorClass);
		HashId hashId = path.getHashId();
		boolean hasResults = false;
		if (path.isFile()) {
			if (store.hasFileResults(hashId)) {
				hasResults = true;
			}
		} else {
			if (store.hasDirectoryResults(hashId)) {
				hasResults = true;
			}
		}
		return hasResults;
	}

	private static void calculateMetricAndShowAsynchronous(
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
						showSynchronous(target, path);
						return Status.OK_STATUS;
					}
				};
				uiJob.schedule();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	/**
	 * Shows synchronous the evaluation resultsin {@link EvaluationsTarget}.
	 * 
	 * @param target
	 * @param path
	 */
	private static void showSynchronous(final EvaluationsTarget target,
			final HashIdFileTree path) {
		target.showEvaluation(path);
	}
}
