package com.puresol.coding.evaluator;

import com.puresol.coding.CodeRange;

/**
 * This interface is meant for evaluators which perform operations on ASTs or in
 * another word: code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class CodeRangeEvaluator extends Evaluator {

    private static final long serialVersionUID = 342860258552477405L;

    public CodeRangeEvaluator(String name) {
	super(name);
    }

    public abstract CodeRange getCodeRange();

}
