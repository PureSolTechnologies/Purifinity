package com.puresoltechnologies.purifinity.evaluation.api;

/**
 * This enum contains the classifications for different evaluators.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public enum EvaluatorType {
	/**
	 * Classifies an evaluator as a defect scanner checking for bugs, bad code
	 * and inconsistencies.
	 */
	DEFECTS,
	/**
	 * Classifies an evaluator as a metrics calculator which provides
	 * measurements about the source code.
	 */
	METRICS,
	/**
	 * Classifies an evaluator as a style checker which checks the source code
	 * itself for clarity, documentation and indentation.
	 */
	STYLE;

}
