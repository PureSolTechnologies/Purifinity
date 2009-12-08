package com.puresol.coding;

import java.util.Hashtable;

public class CodeRange {

	Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
	Hashtable<String, Integer> operants = new Hashtable<String, Integer>();
	int cyclomaticNumber = 1;

	private String type;
	private String name;
	private String text;

	public CodeRange(String type, String name, String text) {
		this.type = type;
		this.name = name;
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return getType() + ": " + getName() + "\n" + getText();
	}

	public void addOperator(String operator) {
		if (operators.containsKey(operator)) {
			operators.put(operator, operators.get(operator) + 1);
		} else {
			operators.put(operator, 1);
		}
	}

	public void addOperant(String operant) {
		if (operants.containsKey(operant)) {
			operants.put(operant, operants.get(operant) + 1);
		} else {
			operants.put(operant, 1);
		}
	}

	public void addCyclomaticNumber(int cyclomaticNumber) {
		this.cyclomaticNumber += cyclomaticNumber;
	}

	public Hashtable<String, Integer> getOperators() {
		return operators;
	}

	public Hashtable<String, Integer> getOperants() {
		return operants;
	}

	public int getCyclomaticNumber() {
		return cyclomaticNumber;
	}
}
