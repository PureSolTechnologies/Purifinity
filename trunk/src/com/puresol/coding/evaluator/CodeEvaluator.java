package com.puresol.coding.evaluator;

import com.puresol.coding.CodeRange;
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

	public boolean isSuitable(CodeRange codeRange);

	public boolean isValid();

	public QualityLevel getQuality();

	public String getReport();
}
