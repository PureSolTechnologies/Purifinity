package com.puresol.coding.evaluator;

import java.util.List;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.utils.Property;

/**
 * This interface is the central interface for all factories for evaluators. The
 * central attributes of the created evaluators can be checked here before
 * creating them.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorFactory {

	public String getEvaluatorName();

	public String getEvaluatorDescription();

	public List<Property> getEvaluatorProperties();

	public boolean isCodeRangeEvaluator();

	public boolean isFileEvaluator();

	public boolean isProjectEvaluator();

	public Evaluator create(CodeRange codeRange) throws NotSupportedException;

	public Evaluator create(Analyzer analyser) throws NotSupportedException;

	public Evaluator create(ProjectAnalyzer projectAnalyser)
			throws NotSupportedException;
}
