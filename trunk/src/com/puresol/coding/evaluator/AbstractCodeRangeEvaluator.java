package com.puresol.coding.evaluator;

import com.puresol.coding.CodeRange;

public abstract class AbstractCodeRangeEvaluator extends AbstractEvaluator
		implements CodeRangeEvaluator {

	private static final long serialVersionUID = -5689175506822461035L;

	private final CodeRange codeRange;

	public AbstractCodeRangeEvaluator(CodeRange codeRange) {
		this.codeRange = codeRange;
	}

	@Override
	public final CodeRange getCodeRange() {
		return codeRange;
	}

}
