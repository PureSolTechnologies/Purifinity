package com.puresol.coding.java;

import java.util.ArrayList;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import com.puresol.coding.TokenContent;

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
	}

	public JavaTokenContent(Token token, TokenStream tokenStream) {
		super(token.getLine());
		if (token.getChannel() == Token.HIDDEN_CHANNEL) {
			throw new IllegalArgumentException("Token is hidden!");
		}
		register(token, tokenStream);
	}

	private void register(Token token, TokenStream tokenStream) {
		String text = token.getText();

		String prevText = "";
		int index = token.getTokenIndex();
		if (token.getTokenIndex() > 0) {
			index--;
			while ((index > 0)
					&& (tokenStream.get(index).getChannel() == Token.HIDDEN_CHANNEL)) {
				index--;
			}
			prevText = tokenStream.get(index).getText();
		}

		setText(text);
		if (OPERATORS.contains(text)) {
			setOperator(1);
			if (text.equals("(")) {
				if (prevText.equals("for") || prevText.equals("if")
						|| prevText.equals("while") || prevText.equals("catch")) {
					setOperator(0);
				}
				setText("()");
			} else if (text.equals("{")) {
				setText("{}");
			} else if (text.equals("[")) {
				setText("[]");
			} else if (text.equals(")")) {
				setText("()");
				setOperator(0);
			} else if (text.equals("]")) {
				setText("[]");
				setOperator(0);
			} else if (text.equals("}")) {
				setText("{}");
				setOperator(0);
			} else if (text.equals("for")) {
				setText("for()");
				setCyclomaticNumber(1);
			} else if (text.equals("while")) {
				setText("while()");
				setCyclomaticNumber(1);
			} else if (text.equals("if")) {
				setText("if()");
				setCyclomaticNumber(1);
			} else if (text.equals("switch")) {
				setText("switch()");
				setCyclomaticNumber(1);
			} else if (text.equals("catch")) {
				setText("catch()");
				setCyclomaticNumber(1);
			}
		} else {
			setOperand(1);
		}
	}
}
