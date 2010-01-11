/***************************************************************************
 *
 *   JavaTokenContent.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.java;

import java.util.ArrayList;

import com.puresol.coding.TokenContent;
import com.puresol.coding.TokenStreamScanner;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

/**
 * This class was inherited from TokenContent to support Java language
 * specialities and Java keywords.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaTokenContent extends TokenContent {

	public static final ArrayList<String> OPERATORS;
	static {
		OPERATORS = new ArrayList<String>();
		OPERATORS.add("+");
		OPERATORS.add("-");
		OPERATORS.add("*");
		OPERATORS.add("/");
		OPERATORS.add("%");
		OPERATORS.add("=");
		OPERATORS.add(">");
		OPERATORS.add("<");
		OPERATORS.add("|");
		OPERATORS.add("&");
		OPERATORS.add("||");
		OPERATORS.add("&&");
		OPERATORS.add("!");
		OPERATORS.add("?");
		OPERATORS.add("==");
		OPERATORS.add("!=");
		OPERATORS.add("+=");
		OPERATORS.add("-=");
		OPERATORS.add("*=");
		OPERATORS.add("/=");
		OPERATORS.add("++");
		OPERATORS.add("--");
		OPERATORS.add(">>");
		OPERATORS.add("<<");
		OPERATORS.add("(");
		OPERATORS.add(")");
		OPERATORS.add("[");
		OPERATORS.add("]");
		OPERATORS.add("{");
		OPERATORS.add("}");
		OPERATORS.add(".");
		OPERATORS.add(",");
		OPERATORS.add(";");
		OPERATORS.add(":");
		OPERATORS.add("@");

		OPERATORS.add("static");
		OPERATORS.add("public");
		OPERATORS.add("private");
		OPERATORS.add("protected");
		OPERATORS.add("final");
		OPERATORS.add("volatile");
		OPERATORS.add("transient");
		OPERATORS.add("class");
		OPERATORS.add("enum");
		OPERATORS.add("interface");

		OPERATORS.add("new");
		OPERATORS.add("extends");
		OPERATORS.add("implements");

		OPERATORS.add("package");
		OPERATORS.add("import");

		OPERATORS.add("return");
		OPERATORS.add("break");
		OPERATORS.add("continue");

		OPERATORS.add("for");
		OPERATORS.add("while");
		OPERATORS.add("do");
		OPERATORS.add("if");
		OPERATORS.add("else");
		OPERATORS.add("switch");
		OPERATORS.add("case");

		OPERATORS.add("try");
		OPERATORS.add("catch");
		OPERATORS.add("throw");
		OPERATORS.add("throws");
	}

	public JavaTokenContent(Token token, TokenStream tokenStream) {
		super(token.getStartLine());
		if (token.getPublicity() == TokenPublicity.HIDDEN) {
			throw new IllegalArgumentException("Token is hidden!");
		}
		register(token, tokenStream);
	}

	private void register(Token token, TokenStream tokenStream) {
		String text = token.getText();
		setText(text);
		if (OPERATORS.contains(text)) {
			setOperatorWithCorrections(token, tokenStream);
		} else {
			setOperand(1);
		}
	}

	private void setOperatorWithCorrections(Token token, TokenStream tokenStream) {
		setOperator(1);
		correctCount(token, tokenStream);
		correctText(token, tokenStream);
		setCyclomaticNumber(token, tokenStream);
	}

	private void correctCount(Token token, TokenStream tokenStream) {
		String text = getText();
		if (text.equals("(")) {
			TokenStreamScanner scanner = new TokenStreamScanner(tokenStream);
			int prevTextIndex = scanner.findPreviousToken(token.getTokenID());
			String prevText = tokenStream.get(prevTextIndex).getText();
			if (prevText.equals("for") || prevText.equals("if")
					|| prevText.equals("while") || prevText.equals("catch")) {
				setOperator(0);
			}
		} else if (text.equals(")")) {
			setOperator(0);
		} else if (text.equals("]")) {
			setOperator(0);
		} else if (text.equals("}")) {
			setOperator(0);
		}
	}

	private void correctText(Token token, TokenStream tokenStream) {
		String text = getText();
		if (text.equals("(")) {
			setText("()");
		} else if (text.equals("{")) {
			setText("{}");
		} else if (text.equals("[")) {
			setText("[]");
		} else if (text.equals(")")) {
			setText("()");
		} else if (text.equals("]")) {
			setText("[]");
		} else if (text.equals("}")) {
			setText("{}");
		} else if (text.equals("for")) {
			setText("for ()");
		} else if (text.equals("while")) {
			setText("while ()");
		} else if (text.equals("if")) {
			setText("if ()");
		} else if (text.equals("switch")) {
			setText("switch ()");
		} else if (text.equals("catch")) {
			setText("catch ()");
		}
	}

	private void setCyclomaticNumber(Token token, TokenStream tokenStream) {
		String text = getText();
		if (text.equals("for ()")) {
			setCyclomaticNumber(1);
		} else if (text.equals("while ()")) {
			setCyclomaticNumber(1);
		} else if (text.equals("if ()")) {
			setCyclomaticNumber(1);
		} else if (text.equals("switch ()")) {
			setCyclomaticNumber(1);
		} else if (text.equals("catch ()")) {
			setCyclomaticNumber(1);
		}
	}

}
