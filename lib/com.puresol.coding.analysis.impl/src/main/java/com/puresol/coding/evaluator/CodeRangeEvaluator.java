package com.puresol.coding.evaluator;

import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.evaluation.api.Result;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

/**
 * This interface is meant for evaluators which perform operations on ASTs or in
 * another word: code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class CodeRangeEvaluator extends Job {

    private static final long serialVersionUID = 342860258552477405L;

    private final Date timeStamp;

    public CodeRangeEvaluator(String name) {
	super(name);
	timeStamp = new Date();
    }

    /**
     * This method returns the name of the time stamp of the evaluation. This is
     * used to track the need for a re-evaluation.
     * 
     * @return
     */
    public final Date getTimeStamp() {
	return timeStamp;
    }

    /**
     * This method returns the code range which was analyzed.
     * 
     * @return
     */
    public abstract CodeRange getCodeRange();

    /**
     * This method returns the description of the evaluator which might be
     * displayed in reports or within applications.
     * 
     * @return
     */
    public abstract String getDescription();

    /**
     * This method returns a list of results from the evaluator. This values are
     * used for creating a report and for storing and tracking them over a
     * longer time (SPC).
     * 
     * @return
     */
    public abstract List<Result> getResults();

    /**
     * This method returns the quality level after an evalutation was performed.
     * 
     * @return
     */
    public abstract SourceCodeQuality getQuality();

    /**
     * This method returns a list with quality characteristics which might be
     * evaluated by the evaluator.
     * 
     * @return
     */
    public abstract List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    /**
     * This method returns the analysis run which is base for the evaluation.
     * 
     * @return
     */
    public abstract AnalysisRun getAnalysisRun();

}
