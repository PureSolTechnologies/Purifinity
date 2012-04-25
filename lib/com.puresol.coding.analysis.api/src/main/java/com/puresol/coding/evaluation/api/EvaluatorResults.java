package com.puresol.coding.evaluation.api;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the central interface for evaluator results. Each evaluator
 * implements an own implementation. This interface just binds this results to
 * the evaluator and provides common functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorResults extends Serializable {

    /**
     * This method returns the name of the evaluator which was used to generate
     * the results.
     * 
     * @return
     */
    public String getEvaluatorName();

    /**
     * This method returns the start time of the evaluation.
     * 
     * @return
     */
    public Date getTime();

    /**
     * This method returns the time of the run in milliseconds.
     * 
     * @return
     */
    public long getTimeOfRun();

}
