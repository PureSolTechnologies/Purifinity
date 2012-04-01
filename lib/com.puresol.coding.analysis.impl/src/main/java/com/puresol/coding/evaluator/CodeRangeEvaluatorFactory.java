package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;

/**
 * This interface is meant for all factories which create code range evaluators.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeRangeEvaluatorFactory extends EvaluatorFactory {

	public CodeRangeEvaluator create(ProgrammingLanguage language,
			CodeRange codeRange);

	public Class<? extends CodeRangeEvaluator> getCodeRangeEvaluatorClass();

}
