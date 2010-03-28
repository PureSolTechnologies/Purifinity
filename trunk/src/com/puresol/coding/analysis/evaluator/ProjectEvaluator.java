package com.puresol.coding.analysis.evaluator;

import com.puresol.coding.analysis.QualityLevel;

/**
 * This interface is the standard interface for all project evaluators used
 * within coding analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProjectEvaluator {

	public String getName();

	public boolean isValid();

	public QualityLevel getQuality();

	public String getReport();
}
