package com.puresol.coding.lang.c11;

import com.puresol.trees.TreeException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.ust.eval.EvaluationException;
import com.puresol.uhura.ust.eval.Value;
import com.puresol.uhura.ust.eval.ValueTypeException;

public class C11ExpressionEvaluator {

    private final ParserTree expression;

    private Value result;

    public C11ExpressionEvaluator(ParserTree expression) {
	this.expression = expression;
    }

    public Value getResult() {
	return result;
    }

    public void evaluate() throws EvaluationException, TreeException {
	result = evaluate(expression);
    }

    private Value evaluate(ParserTree expression) throws EvaluationException,
	    TreeException {
	String nodeName = expression.getName();
	if ("constant-expression".equals(nodeName)) {
	    return evaluateConstantExpression(expression);
	} else if ("conditional-expression".equals(nodeName)) {
	    return evaluateConditionalExpression(expression);
	} else if ("logical-OR-expression".equals(nodeName)) {
	    return evaluateLogicalOrExpression(expression);
	} else if ("logical-AND-expression".equals(nodeName)) {
	    return evaluateLogicalAndExpression(expression);
	} else if ("inclusive-OR-expression".equals(nodeName)) {
	    return evaluateInclusiveOrExpression(expression);
	} else if ("exclusive-OR-expression".equals(nodeName)) {
	    return evaluateExclusiveOrExpression(expression);
	} else if ("AND-expression".equals(nodeName)) {
	    return evaluateAndExpression(expression);
	} else if ("equality-expression".equals(nodeName)) {
	    return evaluateEqualityExpression(expression);
	} else if ("relational-expression".equals(nodeName)) {
	    return evaluateRelationalExpression(expression);
	} else if ("shift-expression".equals(nodeName)) {
	    return evaluateShiftExpression(expression);
	} else if ("additive-expression".equals(nodeName)) {
	    return evaluateAdditiveExpression(expression);
	} else if ("multiplicative-expression".equals(nodeName)) {
	    return evaluateMultiplicativeExpression(expression);
	} else {
	    throw new EvaluationException("Unknown expression type '"
		    + nodeName + "' (" + expression.toString() + ")");
	}
    }

    private Value evaluateMultiplicativeExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("cast-expression"));
	if (expression.hasChild("multiplicative-expression")) {
	    Value left = evaluate(expression
		    .getChild("multiplicative-expression"));
	    if (expression.hasChild("STAR")) {
		result = left.multiply(result);
	    } else if (expression.hasChild("SLASH")) {
		result = left.divide(result);
	    } else {
		result = left.remainder(result);
	    }
	}
	return result;
    }

    private Value evaluateAdditiveExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression
		.getChild("multiplicative-expression"));
	if (expression.hasChild("additive-expression")) {
	    Value left = evaluate(expression.getChild("additive-expression"));
	    if (expression.hasChild("PLUS")) {
		result = left.add(result);
	    } else {
		result = left.subtract(result);
	    }
	}
	return result;
    }

    private Value evaluateShiftExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("additive-expression"));
	if (expression.hasChild("shift-expression")) {
	    Value left = evaluate(expression.getChild("shift-expression"));
	    if (expression.hasChild("LESS_THAN")) {
		result = left.shiftLeft(result);
	    } else {
		result = left.shiftRight(result);
	    }
	}
	return result;
    }

    private Value evaluateRelationalExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("shift-expression"));
	if (expression.hasChild("relational-expression")) {
	    Value left = evaluate(expression.getChild("relational-expression"));
	    if (expression.hasChild("LESS_THAN")) {
		if (expression.hasChild("EQUALS")) {
		    result = left.lessThanOrEquals(result);
		} else {
		    result = left.lessThan(result);
		}
	    } else {
		if (expression.hasChild("EQUALS")) {
		    result = left.greaterThanOrEquals(result);
		} else {
		    result = left.greaterThan("", result);
		}
	    }
	}
	return result;
    }

    private Value evaluateEqualityExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("relational-expression"));
	if (expression.hasChild("equality-expression")) {
	    Value left = evaluate(expression.getChild("equality-expression"));
	    if (expression.hasChild("EXCLAMATION_MARK")) {
		result = left.unequals("byte", result);
	    } else {
		result = left.equals("byte", result);
	    }
	}
	return result;
    }

    private Value evaluateAndExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("equality-expression"));
	if (expression.hasChild("AND-expression")) {
	    Value left = evaluate(expression.getChild("AND-expression"));
	    result = left.and(result);
	}
	return result;
    }

    private Value evaluateExclusiveOrExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("AND-expression"));
	if (expression.hasChild("exclusive-OR-expression")) {
	    Value left = evaluate(expression
		    .getChild("exclusive-OR-expression"));
	    result = left.exclusiveOr(result);
	}
	return result;
    }

    private Value evaluateInclusiveOrExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	Value result = evaluate(expression.getChild("exclusive-OR-expression"));
	if (expression.hasChild("inclusive-OR-expression")) {
	    Value left = evaluate(expression
		    .getChild("inclusive-OR-expression"));
	    result = left.inclusiveOr(result);
	}
	return result;
    }

    private Value evaluateLogicalAndExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	try {
	    if (expression.hasChild("logical-AND-expression")) {
		Value result = evaluate(expression
			.getChild("logical-AND-expression"));
		if (!result.getBooleanValue()) {
		    return result;
		}
	    }
	    return evaluate(expression.getChild("inclusive-OR-expression"));
	} catch (ValueTypeException e) {
	    throw new RuntimeException("Implementation issue.", e);
	}
    }

    private Value evaluateLogicalOrExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	try {
	    if (expression.hasChild("logical-OR-expression")) {
		Value result = evaluate(expression
			.getChild("logical-OR-expression"));
		if (result.getBooleanValue()) {
		    return result;
		}
	    }
	    return evaluate(expression.getChild("logical-AND-expression"));
	} catch (ValueTypeException e) {
	    throw new RuntimeException("Implementation issue.", e);
	}
    }

    private Value evaluateConditionalExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	try {
	    Value logicalOrExpressionResult = evaluate(expression
		    .getChild("logical-OR-expression"));
	    if (expression.hasChild("QUESTION_MARK")) {
		if (logicalOrExpressionResult.getBooleanValue()) {
		    return evaluate(expression.getChild("expression"));
		} else {
		    return evaluate(expression
			    .getChild("conditional-expression"));
		}
	    } else {
		return logicalOrExpressionResult;
	    }
	} catch (ValueTypeException e) {
	    throw new RuntimeException("Implementation issue.", e);
	}
    }

    private Value evaluateConstantExpression(ParserTree expression)
	    throws EvaluationException, TreeException {
	return evaluate(expression.getChild("conditional-expression"));
    }
}
