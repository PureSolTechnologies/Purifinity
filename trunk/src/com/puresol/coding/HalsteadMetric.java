package com.puresol.coding;

import java.util.Hashtable;

public class HalsteadMetric {

	private Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> operants = new Hashtable<String, Integer>();

	private int n1;
	private int n2;
	private int N1;
	private int N2;
	private double n;
	private double N;
	/**
	 * Halstead Length
	 */
	private double HL;
	/**
	 * Halstead Volume
	 */
	private double HV;
	/**
	 * Difficulty
	 */
	private double D;
	/**
	 * Program Level
	 */
	private double L;
	/**
	 * Implementation Effort.
	 */
	private double E;
	/**
	 * Implementation Time [s]
	 */
	private double T;
	/**
	 * Estimated Bugs
	 */
	private double B;

	public HalsteadMetric(CodeRange codeRange) {
		createHashtables(codeRange);
		calculate();
	}

	private void createHashtables(CodeRange codeRange) {
		Hashtable<Integer, TokenContent> tokenContents = codeRange
				.getTokenContents();
		for (int index = codeRange.getStart(); index <= codeRange.getStop(); index++) {
			if (tokenContents.containsKey(index)) {
				TokenContent content = tokenContents.get(index);
				if (!content.getOperant().isEmpty()) {
					addOperant(content.getOperant());
				}
				if (!content.getOperator().isEmpty()) {
					addOperator(content.getOperator());
				}
			}
		}
	}

	private void addOperator(String operator) {
		if (operators.containsKey(operator)) {
			operators.put(operator, operators.get(operator) + 1);
		} else {
			operators.put(operator, 1);
		}
	}

	private void addOperant(String operant) {
		if (operants.containsKey(operant)) {
			operants.put(operant, operants.get(operant) + 1);
		} else {
			operants.put(operant, 1);
		}
	}

	private void calculate() {
		n1 = operators.keySet().size();
		n2 = operants.keySet().size();
		N1 = 0;
		for (String key : operators.keySet()) {
			N1 += operators.get(key);
		}
		N2 = 0;
		for (String key : operants.keySet()) {
			N2 += operants.get(key);
		}
		n = n1 + n2;
		N = N1 + N2;
		HL = n1 * Math.log(n1) / Math.log(2) + n2 * Math.log(n2) / Math.log(2);
		HV = N * Math.log(n) / Math.log(2);
		D = n1 / 2.0 * N2 / n2;
		L = 1 / D;
		E = HV * D;
		T = E / 18.0;
		B = Math.exp(2 / 3 * Math.log(E)) / 3000.0;
	}

	public String getResultsAsString() {
		String result = "n1 = " + n1 + "\n";
		result += "n2 = " + n2 + "\n";
		result += "n = " + n + "\n";
		result += "N1 = " + N1 + "\n";
		result += "N2 = " + N2 + "\n";
		result += "N = " + N + "\n";
		result += "HL = " + HL + "\n";
		result += "HV = " + HV + "\n";
		result += "D = " + D + "\n";
		result += "L = " + L + "\n";
		result += "E = " + E + "\n";
		result += "T = " + T + "\n";
		result += "B = " + B + "\n";
		return result;
	}

	public void printOperators() {
		System.out.println("===============");
		System.out.println("   OPERATORS   ");
		System.out.println("===============");
		for (String operator : operators.keySet()) {
			System.out.println(operator + "\t"
					+ operators.get(operator).toString());
		}
	}

	public void printOperands() {
		System.out.println("==============");
		System.out.println("   OPERANDS   ");
		System.out.println("==============");
		for (String operand : operants.keySet()) {
			System.out.println(operand + "\t"
					+ operants.get(operand).toString());
		}
	}

	public Hashtable<String, Integer> getOperators() {
		return operators;
	}

	public Hashtable<String, Integer> getOperants() {
		return operants;
	}

	public int get_n1() {
		return n1;
	}

	public int get_n2() {
		return n2;
	}

	public int get_N1() {
		return N1;
	}

	public int get_N2() {
		return N2;
	}

	public double get_n() {
		return n;
	}

	public double get_N() {
		return N;
	}

	public double get_HL() {
		return HL;
	}

	public double get_HV() {
		return HV;
	}

	public double get_D() {
		return D;
	}

	public double get_L() {
		return L;
	}

	public double get_E() {
		return E;
	}

	public double get_T() {
		return T;
	}

	public double get_B() {
		return B;
	}
}
