package com.puresol.coding.java.antlr;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;

public class TokenContent {

	private static Logger logger = Logger.getLogger(TokenContent.class);

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
