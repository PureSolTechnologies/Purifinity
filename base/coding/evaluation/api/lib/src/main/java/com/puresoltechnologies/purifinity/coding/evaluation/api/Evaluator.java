package com.puresoltechnologies.purifinity.coding.evaluation.api;

import java.util.Set;

import com.puresoltechnologies.commons.configuration.Configurable;
import com.puresoltechnologies.commons.utils.progress.CallableProgressObservable;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.TimeAwareness;
import com.puresoltechnologies.purifinity.coding.evaluation.iso9126.QualityCharacteristic;

/**
 * This is the central interface for an evaluator. An evaluator is responsible
 * to evaluate files, directories (summary of all files in directory) and the
 * project as a whole.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator extends
		CallableProgressObservable<Evaluator, Boolean>, TimeAwareness,
		Configurable {

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
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics();

	/**
	 * This method creates an {@link EvaluatorStore} object which is responsible
	 * for storing and restoring evaluation results.
	 * 
	 * @return An {@link EvaluatorStore} object is returned containing the
	 *         appropriate evaluator store.
	 */
	public EvaluatorStore createEvaluatorStore();

	/**
	 * This method set whether or not a re-evaluation is needed. If
	 * re-evaluation is set to true, an evaluation is performed even if results
	 * are available.
	 * 
	 * @param reEvaluation
	 */
	public void setReEvaluation(boolean reEvaluation);

	/**
	 * This method returns whether this evaluator is used to re-evaluate former
	 * results or not.
	 * 
	 * @return
	 */
	public boolean isReEvaluation();

}
