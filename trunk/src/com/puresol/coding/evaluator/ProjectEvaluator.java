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

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressObserver;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This is the central class for managing all information for the code
 * evaluation system. All metrics and evaluator classes are managed here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectEvaluator implements ProgressObservable, Serializable {

	private static final long serialVersionUID = -3421021424023319623L;

	private static final Logger logger = Logger
			.getLogger(ProjectEvaluator.class);

	private final ProjectAnalyzer projectAnalyser;
	private final ArrayList<Evaluator> evaluators = new ArrayList<Evaluator>();
	transient private ProgressObserver observer = null;

	public ProjectEvaluator(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}

	@Override
	public void run() {
		evaluators.clear();
		List<EvaluatorFactory> evaluatorFactories = Evaluators.getInstance()
				.getEvaluators();
		if (observer != null) {
			observer.setDescription("Code Evaluation");
			observer.setRange(0, evaluatorFactories.size());
		}
		int count = 0;
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			if (observer != null) {
				count++;
				observer.setStatus(count);
			}
			try {
				Evaluator evaluator = evaluatorFactory.create(projectAnalyser);
				observer.startSubProgress(evaluator);
				evaluators.add(evaluator);
			} catch (NotSupportedException e) {
				logger.warn(e.getMessage(), e);
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

	public List<File> getFiles() {
		return projectAnalyser.getFiles();
	}

	public List<File> getFailedFiles() {
		return projectAnalyser.getFailedFiles();
	}

	public ProjectAnalyzer getProjectAnalyser() {
		return projectAnalyser;
	}
}
