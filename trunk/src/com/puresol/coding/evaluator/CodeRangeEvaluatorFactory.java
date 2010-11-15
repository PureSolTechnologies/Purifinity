package com.puresol.coding.evaluator;

import com.puresol.uhura.ast.AST;

public interface CodeRangeEvaluatorFactory extends EvaluatorFactory {

	public CodeRangeEvaluator create(AST codeRange);

}
