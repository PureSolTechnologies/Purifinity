package com.puresol.coding.evaluator;

import com.puresol.uhura.ast.AST;

/**
 * This interface is meant for evaluators which perform operations on ASTs or in
 * another word: code range.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeRangeEvaluator extends Evaluator {

	public AST getCodeRange();

}
