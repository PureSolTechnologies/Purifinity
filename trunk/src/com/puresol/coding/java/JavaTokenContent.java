package com.puresol.coding.java;

import java.util.ArrayList;

import antlr.Token;

import com.puresol.coding.TokenContent;

public class JavaTokenContent extends TokenContent {

	public static final ArrayList<String> OPERATORS;
	static {
		OPERATORS = new ArrayList<String>();
		OPERATORS.add("+");
		OPERATORS.add("-");
		OPERATORS.add("*");
		OPERATORS.add("/");
		OPERATORS.add("=");
		OPERATORS.add("==");
		OPERATORS.add("!=");
		OPERATORS.add("+=");
		OPERATORS.add("-=");
		OPERATORS.add("*=");
		OPERATORS.add("/=");
		OPERATORS.add("(");
		OPERATORS.add(")");
		OPERATORS.add("[");
		OPERATORS.add("]");
		OPERATORS.add("{");
		OPERATORS.add("}");

		OPERATORS.add("static");
		OPERATORS.add("public");
		OPERATORS.add("private");
		OPERATORS.add("protected");
	}

	public JavaTokenContent(Token token) {
		super(token.getLine());
		register(token);
	}

	private void register(Token token) {
		setText(token.getText());
		if (OPERATORS.contains(getText())) {
			setOperator(1);
		} else {
			setOperand(1);
		}
		if (getText().contains("for") || getText().contains("if")
				|| getText().equals("while") || getText().equals("case")) {
			this.setCyclomaticNumber(1);
		}
	}
}
