package com.puresoltechnologies.purifinity.coding.lang.c11;

import com.puresoltechnologies.commons.trees.TreeException;
import com.puresoltechnologies.parser.impl.parser.ParserTree;
import com.puresoltechnologies.parser.impl.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.parser.impl.ust.eval.Value;
import com.puresoltechnologies.parser.impl.ust.eval.ValueTypeException;
import com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor.DefinedMacros;
import com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor.internal.Macro;

public class C11PreprocessorExpressionEvaluator {

	private final ParserTree expression;

	private final DefinedMacros definedMacros;
	private Value result;

	public C11PreprocessorExpressionEvaluator(ParserTree expression,
			DefinedMacros definedMacros) {
		this.expression = expression;
		this.definedMacros = definedMacros;
	}

	public Value getResult() {
		return result;
	}

	public void evaluate() throws UniversalSyntaxTreeEvaluationException, TreeException {
		result = evaluate(expression);
	}

	private Value evaluate(ParserTree expression) throws UniversalSyntaxTreeEvaluationException,
			TreeException {
		String nodeName = expression.getName();
		if ("expression".equals(nodeName)) {
			return evaluateExpression(expression);
		} else if ("assignment-expression".equals(nodeName)) {
			return evaluateAssignmentExpression(expression);
		} else if ("constant-expression".equals(nodeName)) {
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
		} else if ("cast-expression".equals(nodeName)) {
			return evaluateCastExpression(expression);
		} else if ("unary-expression".equals(nodeName)) {
			return evaluateUnaryExpression(expression);
		} else if ("postfix-expression".equals(nodeName)) {
			return evaluatePostfixExpression(expression);
		} else if ("primary-expression".equals(nodeName)) {
			return evaluatePrimaryExpression(expression);
		} else if ("constant".equals(nodeName)) {
			return evaluateConstant(expression);
		} else {
			throw new UniversalSyntaxTreeEvaluationException("Unknown expression type '"
					+ nodeName + "' (" + expression.toString() + ")");
		}
	}

	private Value evaluateConstant(ParserTree expression)
			throws NumberFormatException, TreeException {
		if (expression.hasChild("integer-constant")) {
			return new Value("int", Integer.valueOf(expression.getChild(
					"integer-constant").getText()));
		} else if (expression.hasChild("floating-constant")) {
			return new Value("double", Double.valueOf(expression.getChild(
					"floating-constant").getText()));
		} else {
			throw new RuntimeException("Constants like '"
					+ expression.toString() + "' are not implemented, yet!");
		}
	}

	private Value evaluatePrimaryExpression(ParserTree expression)
			throws TreeException, UniversalSyntaxTreeEvaluationException {
		if (expression.hasChild("identifier")) {
			ParserTree identifier = expression.getChild("identifier");
			String identifierName = identifier.getText();
			if (definedMacros.isDefined(identifierName)) {
				Value value = generateValue(identifierName);
				return value;
			} else {
				return new Value("int", 0);
			}
		} else if (expression.hasChild("constant")) {
			return evaluate(expression.getChild("constant"));
		} else if (expression.hasChild("expression")) {
			return evaluate(expression.getChild("expression"));
		} else {
			throw new RuntimeException("Primary expressions like '"
					+ expression.toString() + "' are not implemented, yet!");
		}
	}

	private Value evaluatePostfixExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		if (expression.hasChild("primary-expression")) {
			return evaluate(expression.getChild("primary-expression"));
		} else {
			throw new RuntimeException(
					"Cast expressions are not implemented, yet!");
		}
	}

	private Value evaluateUnaryExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		if (expression.hasChild("postfix-expression")) {
			return evaluate(expression.getChild("postfix-expression"));
		} else {
			throw new RuntimeException(
					"Cast expressions are not implemented, yet!");
		}
	}

	private Value evaluateCastExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		if (expression.hasChild("unary-expression")) {
			return evaluate(expression.getChild("unary-expression"));
		} else {
			throw new RuntimeException("Cast expressions like '"
					+ expression.toString() + "' are not implemented, yet!");
		}
	}

	private Value evaluateMultiplicativeExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		Value result = evaluate(expression.getChild("equality-expression"));
		if (expression.hasChild("AND-expression")) {
			Value left = evaluate(expression.getChild("AND-expression"));
			result = left.and(result);
		}
		return result;
	}

	private Value evaluateExclusiveOrExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		Value result = evaluate(expression.getChild("AND-expression"));
		if (expression.hasChild("exclusive-OR-expression")) {
			Value left = evaluate(expression
					.getChild("exclusive-OR-expression"));
			result = left.exclusiveOr(result);
		}
		return result;
	}

	private Value evaluateInclusiveOrExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		Value result = evaluate(expression.getChild("exclusive-OR-expression"));
		if (expression.hasChild("inclusive-OR-expression")) {
			Value left = evaluate(expression
					.getChild("inclusive-OR-expression"));
			result = left.inclusiveOr(result);
		}
		return result;
	}

	private Value evaluateLogicalAndExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
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
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		return evaluate(expression.getChild("conditional-expression"));
	}

	private Value evaluateAssignmentExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		if (expression.hasChild("conditional-expression")) {
			return evaluate(expression.getChild("conditional-expression"));
		} else {
			throw new RuntimeException("Assignment expressions like '"
					+ expression.toString() + "' are not implemented, yet!");
		}
	}

	private Value evaluateExpression(ParserTree expression)
			throws UniversalSyntaxTreeEvaluationException, TreeException {
		if (expression.hasChild("assignment-expression")) {
			return evaluate(expression.getChild("assignment-expression"));
		} else {
			throw new RuntimeException("Expressions like '"
					+ expression.toString() + "' are not implemented, yet!");
		}
	}

	private Value generateValue(String identifierName)
			throws UniversalSyntaxTreeEvaluationException {
		Macro macro = definedMacros.getMacro(identifierName);
		if ((macro.getParameters().size() > 0)
				|| (macro.isOptionalParameters())) {
			throw new UniversalSyntaxTreeEvaluationException("Macro '" + identifierName
					+ "' is a function like macro. This is not supported.");
		}
		if (macro.getReplacement().size() > 1) {
			throw new UniversalSyntaxTreeEvaluationException("Macro '" + identifierName
					+ "' has the complex replacement '"
					+ macro.getReplacement().toString()
					+ "'. This is not supported.");
		}
		if (macro.getReplacement().size() == 0) {
			return new Value("int", 1);
		}
		String macroString = definedMacros.getMacro(identifierName)
				.getReplacement().get(0).getText();
		try {
			return new Value("int", Integer.valueOf(macroString));
		} catch (NumberFormatException e) {
			try {
				return new Value("double", Double.valueOf(macroString));
			} catch (NumberFormatException e1) {
				return new Value("String", macroString);
			}
		}
	}

}
