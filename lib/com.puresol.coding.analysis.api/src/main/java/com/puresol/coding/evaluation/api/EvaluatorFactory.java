package com.puresol.coding.evaluation.api;

import java.util.List;

import com.puresol.coding.quality.QualityCharacteristic;

/**
 * This interface is the central interface for all factories for evaluators. The
 * central attributes of the created evaluators can be checked here before
 * creating them.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorFactory {

    /**
     * This method returns a name for the evaluator which is created with this
     * factory.
     * 
     * @return
     */
    public String getName();

    /**
     * This method returns a description for the evaluator which is created with
     * this factory.
     * 
     * @return
     */
    public String getDescription();

    /**
     * This method returns a list with all quality characteristics which are
     * evaluated with the evaluator created with this factory.
     * 
     * @return
     */
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    /**
     * This method actually creates the evaluator.
     * 
     * @param analysisRun
     * @return
     */
    // TODO public Evaluator create(AnalysisRun analysisRun);
}
