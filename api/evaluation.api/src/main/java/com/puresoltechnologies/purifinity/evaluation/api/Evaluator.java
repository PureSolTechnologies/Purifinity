package com.puresoltechnologies.purifinity.evaluation.api;

import java.util.Set;

import com.puresoltechnologies.commons.domain.Configurable;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;

/**
 * This is the central interface for an evaluator. An evaluator is responsible
 * to evaluate files, directories (summary of all files in directory) and the
 * project as a whole.
 * 
 * <b>Implementations of the interface must not have mutable state!</b>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator extends Configurable {

    /**
     * This method returns the evaluator meta data which describes the evaluator
     * in more detail.
     * 
     * @return An {@link EvaluatorInformation} object is returned containing the
     *         information about the {@link Evaluator}.
     */
    public EvaluatorInformation getInformation();

    /**
     * 
     * @return A {@link Set} of {@link MetricParameter} is returned which are
     *         calculated by this evaluator.
     */
    public Set<MetricParameter<?>> getParameters();

    /**
     * This method returns a list with quality characteristics which might be
     * evaluated by the evaluator.
     * 
     * @return A {@link Set} of {@link QualityCharacteristic} is returned
     *         describing what characteristics in term of quality are evaluated.
     */
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    /**
     * This method is run for the evaluation itself.
     * 
     * @param analysisRun
     *            is the analysis run which on which the evaluation is to be
     *            performed on.
     * @param enableReevaluation
     *            is set to <code>true</code> if an evaluation has to take place
     *            even in case of already existing results. <code>false</code>
     *            is returned otherwise.
     * @throws InterruptedException
     *             is thrown in case of a requested interruption.
     * @throws EvaluationStoreException
     *             is thrown case of issues with the evaluation store.
     */
    public void evaluate(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException;
}
