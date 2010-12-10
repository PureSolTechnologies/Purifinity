package com.puresol.coding.lang.java.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.halstead.HalsteadResult;
import com.puresol.coding.metrics.halstead.LanguageDependedHalsteadMetric;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;

/**
 * This is the actual implementation of the McCabe metric for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HalsteadMetricImpl implements LanguageDependedHalsteadMetric {

	private static final List<String> operators = new ArrayList<String>();
	static {
		operators.add("ABSTRACT");
		operators.add("ASSERT");
		operators.add("BOOLEAN");
		operators.add("BREAK");
		operators.add("BYTE");
		operators.add("CASE");
		operators.add("CLASS");
		operators.add("CONTINUE");
		operators.add("CATCH");
		operators.add("CHAR");
		operators.add("CONST");
		operators.add("DEFAULT");
		operators.add("DO");
		operators.add("DOUBLE");
		operators.add("ELSE");
		operators.add("ENUM");
		operators.add("EXTENDS");
		operators.add("FINAL");
		operators.add("FINALLY");
		operators.add("FLOAT");
		operators.add("FOR");
		operators.add("GOTO");
		operators.add("IF");
		operators.add("IMPLEMENTS");
		operators.add("IMPORT");
		operators.add("INSTANCEOF");
		operators.add("INT");
		operators.add("INTERFACE");
		operators.add("LONG");
		operators.add("NATIVE");
		operators.add("NEW");
		operators.add("PACKAGE");
		operators.add("PRIVATE");
		operators.add("PROTECTED");
		operators.add("PUBLIC");
		operators.add("RETURN");
		operators.add("SHORT");
		operators.add("STATIC");
		operators.add("STRICTFP");
		operators.add("SUPER");
		operators.add("SWITCH");
		operators.add("SYNCHRONIZED");
		operators.add("THIS");
		operators.add("THROW");
		operators.add("THROWS");
		operators.add("TRANSIENT");
		operators.add("TRY");
		operators.add("VOID");
		operators.add("VOLATILE");
		operators.add("WHILE");

		operators.add("EQUALS");
		operators.add("LESS_THAN");
		operators.add("GREATER_THAN");
		operators.add("LPAREN");
		operators.add("RPAREN");
		operators.add("LCURLY");
		operators.add("RCURLY");
		operators.add("LRECTANGULAR");
		operators.add("RRECTANGULAR");
		operators.add("DOT");
		operators.add("COMMA");
		operators.add("COLON");
		operators.add("SEMICOLON");
		operators.add("DOLLAR");
		operators.add("CARET");
		operators.add("STAR");
		operators.add("SLASH");
		operators.add("PERCENT");
		operators.add("PLUS");
		operators.add("MINUS");
		operators.add("AMPERSAND");
		operators.add("AT");
		operators.add("EXCLAMATION_MARK");
		operators.add("QUESTION_MARK");
		operators.add("TILDE");
		operators.add("VERTICAL_BAR");
	}

	private static final List<String> lParenExceptions = new ArrayList<String>();
	static {
		lParenExceptions.add("IfThenStatement");
		lParenExceptions.add("IfThenElseStatement");
		lParenExceptions.add("IfThenElseStatementNoShortIf");
		lParenExceptions.add("SwitchStatement");
		lParenExceptions.add("WhileStatement");
		lParenExceptions.add("WhileStatementNoShortIf");
		lParenExceptions.add("DoStatement");
		lParenExceptions.add("BasicForStatement");
		lParenExceptions.add("ForStatementNoShortIf");
		lParenExceptions.add("EnhancedForStatement");
		lParenExceptions.add("SynchronizedStatement");
		lParenExceptions.add("CatchClause");
	}

	@Override
	public HalsteadResult getHalsteadResult(ParserTree node) {
		Token token = node.getToken();
		if ((token == null) || (token.getVisibility() != Visibility.VISIBLE)) {
			return new HalsteadResult(false, false, "");
		}
		if (!operators.contains(node.getName())) {
			return new HalsteadResult(true, false, node.getText());
		}
		if ("RPAREN".equals(node.getName()) || "RCURLY".equals(node.getName())
				|| "RRECTANGULAR".equals(node.getName())) {
			/*
			 * these tokens are not counted due to pairwise appearance with the
			 * left part; double couting is not needed
			 */
			return new HalsteadResult(false, true, node.getText());
		}
		if ("LCURLY".equals(node.getName())) {
			/*
			 * these tokens are not counted due to pairwise appearance with the
			 * left part; double couting is not needed
			 */
			return new HalsteadResult(true, true, "{}");
		}
		if ("LRECTANGULAR".equals(node.getName())) {
			/*
			 * these tokens are not counted due to pairwise appearance with the
			 * left part; double couting is not needed
			 */
			return new HalsteadResult(true, true, "[]");
		}
		if ("LPAREN".equals(node.getName())) {
			if (lParenExceptions.contains(node.getParent().getName())) {
				/*
				 * The left parenthesis is always connected to another operator
				 * and therefore not counted again.
				 */
				return new HalsteadResult(false, true, node.getText());
			}
			return new HalsteadResult(true, true, "()");
		}
		if ("IF".equals(node.getName())) {
			return new HalsteadResult(true, true, "if()");
		}
		if ("SWITCH".equals(node.getName())) {
			return new HalsteadResult(true, true, "switch()");
		}
		if ("WHILE".equals(node.getName())) {
			return new HalsteadResult(true, true, "while()");
		}
		if ("FOR".equals(node.getName())) {
			if ("EnhancedForStatement".equals(node.getParent().getName())) {
				return new HalsteadResult(true, true, "for(:)");
			}
			return new HalsteadResult(true, true, "for(;;)");
		}
		if ("SYNCHRONIZED".equals(node.getName())) {
			return new HalsteadResult(true, true, "synchronized()");
		}
		if ("CATCH".equals(node.getName())) {
			return new HalsteadResult(true, true, "catch()");
		}
		return new HalsteadResult(true, true, node.getText());
	}
}
