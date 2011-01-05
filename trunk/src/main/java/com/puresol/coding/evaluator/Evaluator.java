package com.puresol.coding.evaluator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.swingx.progress.ProgressObservable;

import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

/**
 * This interface is the main interface for all evaluators and the general
 * behavior of them. All evaluators should have a name and a description as well
 * as some standard output like the report, the quality level and the evaluated
 * quality characteristics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator extends ProgressObservable, Runnable, Serializable {

	/**
	 * This method returns the name of the time stamp of the evaluation. This is
	 * used to track the need for a re-evaluation.
	 * 
	 * @return
	 */
	public Date getTimeStamp();

	/**
	 * This method returns the name of the evaluator which might be displayed in
	 * reports or within applications.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns the description of the evaluator which might be
	 * displayed in reports or within applications.
	 * 
	 * @return
	 */
	public String getDescription();

	/**
	 * This method returns a list of results from the evaluator. This values are
	 * used for creating a report and for storing and tracking them over a
	 * longer time (SPC).
	 * 
	 * @return
	 */
	public List<Result> getResults();

	/**
	 * This method returns the quality level after an evalutation was performed.
	 * 
	 * @return
	 */
	public SourceCodeQuality getQuality();

	/**
	 * This method returns a list with quality characteristics which might be
	 * evaluated by the evaluator.
	 * 
	 * @return
	 */
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

}
