package com.puresol.coding.client.common.evaluation.jobs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.client.common.evaluation.Activator;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class EvaluationJob extends Job {

	private static final ILog logger = Activator.getDefault().getLog();

	private final AnalysisRun analysisRun;

	public EvaluationJob(AnalysisRun analysisRun) {
		super("Evalutions of '"
				+ analysisRun.getInformation().getAnalysisProject()
						.getSettings().getName() + "'");
		this.analysisRun = analysisRun;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		Evaluators evaluators = Evaluators.createInstance();
		try {
			Set<Class<? extends Evaluator>> finished = new HashSet<Class<? extends Evaluator>>();
			List<EvaluatorFactory> evaluatorFactories = evaluators.getAll();
			monitor.beginTask("Running evaluations for '" + getName() + "'",
					evaluatorFactories.size());
			boolean evaluated = true;
			while (evaluated) {
				evaluated = false;
				for (EvaluatorFactory factory : evaluatorFactories) {
					boolean canRun = checkDepdencies(finished, factory);
					if (!canRun) {
						continue;
					}
					Evaluator evaluator = factory.create(analysisRun);
					if (!finished.contains(evaluator.getClass())) {
						try {
							evaluator.call();
							finished.add(evaluator.getClass());
							monitor.worked(1);
						} catch (InterruptedException e) {
							logger.log(new Status(Status.ERROR,
									EvaluationJob.class.getName(),
									"Could not run evaluation '"
											+ evaluator.getInformation()
													.getName() + "'!", e));
							monitor.done();
							return Status.CANCEL_STATUS;
						} catch (Exception e) {
							logger.log(new Status(Status.ERROR,
									EvaluationJob.class.getName(),
									"Could not run evaluation '"
											+ evaluator.getInformation()
													.getName() + "'!", e));
							monitor.done();
							return Status.CANCEL_STATUS;
						}
						evaluated = true;
					}
				}
			}
			monitor.done();
			return Status.OK_STATUS;
		} finally {
			IOUtils.closeQuietly(evaluators);
		}
	}

	private boolean checkDepdencies(Set<Class<? extends Evaluator>> finished,
			EvaluatorFactory factory) {
		boolean dependenciesResolved = true;
		for (Class<? extends Evaluator> dependsOn : factory.getDependencies()) {
			if (!finished.contains(dependsOn)) {
				dependenciesResolved = false;
				break;
			}
		}
		return dependenciesResolved;
	}
}
