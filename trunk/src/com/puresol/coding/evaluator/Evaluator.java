package com.puresol.coding.evaluator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.swingx.progress.ProgressObservable;

import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedReportingFormatException;

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

	public Date getTimeStamp();

	public String getName();

	public String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException;

	public String getReport(ReportingFormat format)
			throws UnsupportedReportingFormatException;

	public QualityLevel getQuality();

	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

}
