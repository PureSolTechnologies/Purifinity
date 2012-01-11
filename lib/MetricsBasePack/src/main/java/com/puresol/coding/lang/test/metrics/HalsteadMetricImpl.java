package com.puresol.coding.lang.test.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.metrics.halstead.HalsteadSymbol;
import com.puresol.coding.metrics.halstead.LanguageDependedHalsteadMetric;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.parser.ParserTree;

/**
 * This is the actual implementation of the McCabe metric for Java.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HalsteadMetricImpl implements LanguageDependedHalsteadMetric {

	private static final long serialVersionUID = 3356388578844607601L;

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
		// operators.add("SUPER"); this is an operand!
		operators.add("SWITCH");
		operators.add("SYNCHRONIZED");
		// operators.add("THIS"); this is an operand!
		operators.add("THROW");
		operators.add("THROWS");
		operators.add("TRANSIENT");
		operators.add("TRY");
		// operators.add("VOID"); this is an operand!
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
	public HalsteadSymbol getHalsteadResult(ParserTree node) {
		Token token = node.getToken();
		if ((token == null) || (token.getVisibility() != Visibility.VISIBLE)) {
			return new HalsteadSymbol(false, false, "");
		}
		if (!operators.contains(node.getName())) {
			return new HalsteadSymbol(true, false, node.getText());
		}
		if ("RPAREN".equals(node.getName()) || "RCURLY".equals(node.getName())
				|| "RRECTANGULAR".equals(node.getName())) {
			/*
			 * these tokens are not counted due to pairwise appearance with the
			 * left part; double couting is not needed
			 */
			return new HalsteadSymbol(false, true, node.getText());
		}
		if ("LCURLY".equals(node.getName())) {
			/*
			 * these tokens are not counted due to pairwise appearance with the
			 * left part; double couting is not needed
			 */
			return new HalsteadSymbol(true, true, "{}");
		}
		if ("LRECTANGULAR".equals(node.getName())) {
			/*
			 * these tokens are not counted due to pairwise appearance with the
			 * left part; double couting is not needed
			 */
			return new HalsteadSymbol(true, true, "[]");
		}
		if ("LPAREN".equals(node.getName())) {
			if (lParenExceptions.contains(node.getParent().getName())) {
				/*
				 * The left parenthesis is always connected to another operator
				 * and therefore not counted again.
				 */
				return new HalsteadSymbol(false, true, node.getText());
			}
			return new HalsteadSymbol(true, true, "()");
		}
		if ("IF".equals(node.getName())) {
			return new HalsteadSymbol(true, true, "if()");
		}
		if ("SWITCH".equals(node.getName())) {
			return new HalsteadSymbol(true, true, "switch()");
		}
		if ("WHILE".equals(node.getName())) {
			return new HalsteadSymbol(true, true, "while()");
		}
		if ("FOR".equals(node.getName())) {
			if ("EnhancedForStatement".equals(node.getParent().getName())) {
				return new HalsteadSymbol(true, true, "for(:)");
			}
			return new HalsteadSymbol(true, true, "for(;;)");
		}
		if ("SYNCHRONIZED".equals(node.getName())) {
			return new HalsteadSymbol(true, true, "synchronized()");
		}
		if ("CATCH".equals(node.getName())) {
			return new HalsteadSymbol(true, true, "catch()");
		}
		return new HalsteadSymbol(true, true, node.getText());
	}
}
