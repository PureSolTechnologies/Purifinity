package com.puresol.coding.evaluator;

import com.puresol.coding.analysis.CodeRange;

public interface CodeRangeEvaluatorFactory extends EvaluatorFactory {

	public CodeRangeEvaluator create(CodeRange codeRange);

}
