package com.puresoltechnologies.purifinity.evaluation.api;

import java.util.Set;

import com.puresoltechnologies.commons.misc.Configurable;
import com.puresoltechnologies.commons.misc.TimeAwareness;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;

/**
 * This is the central interface for an evaluator. An evaluator is responsible
 * to evaluate files, directories (summary of all files in directory) and the
 * project as a whole.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator extends TimeAwareness, Configurable {

	/**
	 * This method returns the evaluator meta data which describes the evaluator
	 * in more detail.
	 * 
	 * @return
	 */
	public EvaluatorInformation getInformation();

	/**
	 * This method returns a list with quality characteristics which might be
	 * evaluated by the evaluator.
	 * 
	 * @return
	 */
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics();

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

	public Boolean analyze(AnalysisRun analysisRun)
			throws InterruptedException, EvaluationStoreException;
}
