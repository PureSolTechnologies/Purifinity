package com.puresol.coding.evaluator;

import com.puresol.uhura.ast.AST;

public abstract class AbstractCodeRangeEvaluator extends AbstractEvaluator
		implements CodeRangeEvaluator {

	private static final long serialVersionUID = -5689175506822461035L;

	private final AST ast;

	public AbstractCodeRangeEvaluator(AST ast) {
		this.ast = ast;
	}

	@Override
	public final AST getCodeRange() {
		return ast;
	}

}

