package com.puresol.coding;

public class TokenContent {

	private int line;
	private String operator = "";
	private String operant = "";
	private int cyclomaticNumber = 0;

	public TokenContent(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperant() {
		return operant;
	}

	public void setOperant(String operant) {
		this.operant = operant;
	}

	public int getCyclomaticNumber() {
		return cyclomaticNumber;
	}

	public void setCyclomaticNumber(int cyclomaticNumber) {
		this.cyclomaticNumber = cyclomaticNumber;
	}
}
