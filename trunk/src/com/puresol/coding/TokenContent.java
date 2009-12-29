/***************************************************************************
 *
 *   TokenContent.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

public class TokenContent {

	private int line = 0;
	private String text = "";
	private int operator = 0;
	private int operand = 0;
	private boolean isKeyword = false;
	private boolean isSymbol = false;
	private int cyclomaticNumber = 0;

	public TokenContent(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCyclomaticNumber() {
		return cyclomaticNumber;
	}

	public void setCyclomaticNumber(int cyclomaticNumber) {
		this.cyclomaticNumber = cyclomaticNumber;
	}

	public void setOperator(int num) {
		operator = num;
	}

	public boolean isOperator() {
		return (operator != 0);
	}

	public void setOperand(int num) {
		operand = num;
	}

	public boolean isOperand() {
		return (operand != 0);
	}

	public void setKeyword(boolean isKeyword) {
		this.isKeyword = isKeyword;
	}

	public boolean isKeyword() {
		return this.isKeyword;
	}

	public void setSymbolc(boolean isSymbol) {
		this.isSymbol = isSymbol;
	}

	public boolean isSymbol() {
		return this.isSymbol;
	}
}
