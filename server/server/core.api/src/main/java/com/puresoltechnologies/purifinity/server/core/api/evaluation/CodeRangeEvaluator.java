package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

/**
 * This interface is meant for evaluators which perform operations on ASTs or in
 * another word: code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class CodeRangeEvaluator {

    private final Date timeStamp;
    private final String name;

    public CodeRangeEvaluator(String name) {
	super();
	this.name = name;
	timeStamp = new Date();
    }

    /**
     * Returns the name of the evaluator.
     * 
     * @return A {@link String} is returned containing the name.
     */
    public final String getName() {
	return name;
    }

    /**
     * This method returns the name of the time stamp of the evaluation. This is
     * used to track the need for a re-evaluation.
     * 
     * @return A {@link Date} object is returned containing the time.
     */
    public final Date getTimeStamp() {
	return timeStamp;
    }

    /**
     * This method returns the code range which was analyzed.
     * 
     * @return The {@link CodeRange} is returned.
     */
    public abstract CodeRange getCodeRange();

    /**
     * This method returns the description of the evaluator which might be
     * displayed in reports or within applications.
     * 
     * @return A description {@link String} is returned.
     */
    public abstract String getDescription();

    /**
     * This method returns a list of results from the evaluator. This values are
     * used for creating a report and for storing and tracking them over a
     * longer time (SPC).
     * 
     * @return A {@link List} of {@link MetricValue} is returned.
     */
    public abstract List<MetricValue<?>> getResults();

    /**
     * This method returns the quality level after an evalutation was performed.
     * 
     * @return The quality is returned as {@link Severity}.
     */
    public abstract Severity getQuality();

    /**
     * This method returns a list with quality characteristics which might be
     * evaluated by the evaluator.
     * 
     * @return A {@link Set} of {@link QualityCharacteristic} is returned.
     */
    public abstract Set<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    /**
     * This method returns the analysis run which is base for the evaluation.
     * 
     * @return A {@link AnalysisRun} object is returned.
     */
    public abstract AnalysisRun getAnalysisRun();

    /**
     * This method is called to run the actual evaluations.
     * 
     * @return <code>true</code> is returned after successful finish.
     *         <code>false</code> is returned otherwise.
     */
    public abstract boolean run();
}
