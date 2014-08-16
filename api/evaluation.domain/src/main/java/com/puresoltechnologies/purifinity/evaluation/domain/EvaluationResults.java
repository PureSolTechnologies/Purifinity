package com.puresoltechnologies.purifinity.evaluation.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the base interface for all evaluation results. The main purpose for
 * Evaluators are the check of source code and providing feedback about the
 * quality. This interface reflects this and provides base methods for the main
 * purposes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluationResults extends Serializable {

    /**
     * This method returns the time of the result.
     * 
     * @return A {@link Date} object is returned containing the time of the
     *         evaluation.
     */
    public Date getTime();

    /**
     * This method returns the id of the evaluator which was responsible for the
     * results represented by this class.
     * 
     * @return A {@link String} with the evaluator id is returned.
     */
    public String getEvaluatorId();

}
