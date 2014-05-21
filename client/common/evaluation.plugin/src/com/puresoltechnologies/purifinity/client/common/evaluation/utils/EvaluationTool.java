package com.puresoltechnologies.purifinity.client.common.evaluation.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.UIJob;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.ui.jobs.ObservedJob;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

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
	 * @throws EvaluationStoreException
	 */
	public static synchronized void showEvaluationAsynchronous(
			final EvaluationsTarget target,
			final EvaluatorFactory evaluatorFactory,
			final AnalysisRun analysisRun, final AnalysisFileTree path)
			throws EvaluationStoreException {
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
	 * @throws EvaluationStoreException
	 */
	private static boolean checkForResults(EvaluatorFactory evaluatorFactory,
			AnalysisFileTree path) throws EvaluationStoreException {
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
			final AnalysisRun analysisRun, final AnalysisFileTree path) {
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
						try {
							showSynchronous(target, path);
						} catch (EvaluationStoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
	 * @throws EvaluationStoreException
	 */
	private static void showSynchronous(final EvaluationsTarget target,
			final AnalysisFileTree path) throws EvaluationStoreException {
		target.showEvaluation(path);
	}
}
