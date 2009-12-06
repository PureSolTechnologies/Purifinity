package com.puresol.coding;

import java.util.Hashtable;

public class LexerHelper {

	// private static final Logger logger = Logger.getLogger(LexerHelper.class);

	private int slocCount = 0;
	private int blockLayer = 0;
	private Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> operands = new Hashtable<String, Integer>();

	public int getSlocCount() {
		return slocCount;
	}

	public void setSlocCount(int slocCount) {
		this.slocCount = slocCount;
	}

	public void incSlocCount() {
		slocCount++;
		// logger.debug("sloc " + slocCount);
	}

	public void addBlockBegin() {
		blockLayer++;
		// logger
		// .debug("block begin: " + blockLayer + " (line " + slocCount
		// + ")");
	}

	public void addBlockEnd() {
		blockLayer--;
		// logger
		// .debug("block begin: " + blockLayer + " (line " + slocCount
		// + ")");
	}

	public void addOperator(String operator) {
		if (!operators.containsKey(operator)) {
			operators.put(operator, 1);
		} else {
			operators.put(operator, operators.get(operator) + 1);
		}
	}

	public Hashtable<String, Integer> getOperators() {
		return operators;
	}

	public void addOperand(String operand) {
		if (!operands.containsKey(operand)) {
			operands.put(operand, 1);
		} else {
			operands.put(operand, operands.get(operand) + 1);
		}
	}

	public Hashtable<String, Integer> getOperands() {
		return operands;
	}
}
