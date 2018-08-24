package com.puresoltechnologies.purifinity.evaluation.api;

/**
 * This enum contains the classifications for different evaluators.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public enum EvaluatorType {

    /**
     * Classifies an evaluator as an architecture evaluator checking for
     * architectural issues like wrong dependencies.
     */
    ARCHITECTURE,
    /**
     * Classifies an evaluator as a design evaluator checking for design issues
     * like usage of wrong patterns.
     */
    DESGIN,
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
