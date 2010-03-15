package com.puresol.coding.analysis.evaluator;

import com.puresol.coding.analysis.QualityLevel;

/**
 * This interface is the standard interface for all code evaluators used within
 * coding analysis. All evaluators are registered within the project analyzer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeEvaluator {

	public String getName();

	public boolean isValid();

	public QualityLevel getQuality();

	public String getReport();
}
