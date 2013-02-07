package com.puresol.coding.analysis.api.evaluation;

import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.TimeAwareness;
import com.puresol.coding.analysis.api.quality.QualityCharacteristic;

/**
 * This is the central interface for an evaluator. An evaluator is responsible
 * to evaluate files, directories (summary of all files in directory) and the
 * project as a whole.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator extends TimeAwareness {

	/**
	 * This method returns the evaluator meta data which describes the evaluator
	 * in more detail.
	 * 
	 * @return
	 */
	public EvaluatorInformation getInformation();

	/**
	 * This method returns the analysis run which is the foundation of the
	 * evaluation run.
	 * 
	 * @return
	 */
	public AnalysisRun getAnalysisRun();

	/**
	 * This method returns a list with quality characteristics which might be
	 * evaluated by the evaluator.
	 * 
	 * @return
	 */
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

	/**
	 * This method starts the actual evaluation. This method runs synchronous!
	 * 
	 * @throws InterruptedException
	 */
	public void runEvaluation() throws InterruptedException;

	public EvaluatorStore getEvaluatorStore();
}
