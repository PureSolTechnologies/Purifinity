package com.puresol.coding.lang.c11;

import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;

public class C11ExpressionEvaluator {

    private final ParserTree expression;

    private C11EvaluationResult result;

    public C11ExpressionEvaluator(ParserTree expression) {
	this.expression = expression;
    }

    public C11EvaluationResult getResult() {
	return result;
    }

    public void evaluate() throws EvaluationException, TreeException {
	result = evaluate(expression);
    }

    private C11EvaluationResult evaluate(ParserTree expression)
	    throws EvaluationException, TreeException {
	String nodeName = expression.getName();
	if ("constant-expression".equals(nodeName)) {
	    return evaluateConstantExpression(expression);
	} else if ("conditional-expression".equals(nodeName)) {
	    return evaluateConditionalExpression(expression);
	} else {
	    throw new EvaluationException("Unknown expression type '"
		    + nodeName + "' (" + expression.toString() + ")");
	}
    }

    private C11EvaluationResult evaluateConditionalExpression(
	    ParserTree expression) throws EvaluationException, TreeException {
	C11EvaluationResult logicalOrExpressionResult = evaluate(expression
		.getChild("logical-OR-expression"));
	if (expression.hasChild("QUESTION_MARK")) {
	    if (logicalOrExpressionResult.getBooleanValue()) {
		return evaluate(expression.getChild("expression"));
	    } else {
		return evaluate(expression.getChild("conditional-expression"));
	    }
	} else {
	    return logicalOrExpressionResult;
	}
    }

    private C11EvaluationResult evaluateConstantExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	return evaluate(expression.getChild("conditional-expression"));
    }
}
