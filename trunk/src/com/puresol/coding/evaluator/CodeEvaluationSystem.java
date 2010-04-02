/***************************************************************************
 *
 *   CodeEvaluationSystem.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator;

import java.util.ArrayList;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

/**
 * This is the central class for managing all information for the code
 * evaluation system. All metrics and evaluator classes are managed here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeEvaluationSystem implements ProgressObservable {

	private static final Logger logger = Logger
			.getLogger(CodeEvaluationSystem.class);

	private final ProjectAnalyser projectAnalyser;
	private final ArrayList<Evaluator> evaluators = new ArrayList<Evaluator>();
	private ProgressObserver observer = null;

	public CodeEvaluationSystem(ProjectAnalyser projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}

	@Override
	public void run() {
		evaluators.clear();
		ArrayList<Class<? extends Evaluator>> evaluatorClasses = EvaluatorManager
				.getInstance().getEvaluatorClasses();
		if (observer != null) {
			observer.setDescription("Code Evaluation");
			observer.setRange(0, evaluatorClasses.size());
		}
		int count = 0;
		for (Class<? extends Evaluator> evaluatorClass : evaluatorClasses) {
			if (observer != null) {
				count++;
				observer.setStatus(count);
			}
			try {
				Evaluator evaluator = Instances.createInstance(evaluatorClass,
						projectAnalyser);
				observer.startSubProgress(evaluator);
				evaluators.add(evaluator);
			} catch (ClassInstantiationException e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.observer = observer;
	}

	public ArrayList<Evaluator> getEvaluators() {
		return evaluators;
	}
}
