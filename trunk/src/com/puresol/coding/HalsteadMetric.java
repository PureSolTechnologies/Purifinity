package com.puresol.coding;

import java.util.Hashtable;

public class HalsteadMetric {

	Hashtable<String, Integer> operators = null;
	Hashtable<String, Integer> operands = null;

	private int n1;
	private int n2;
	private int N1;
	private int N2;
	private double n;
	private double N;
	private double HL;
	private double HV;
	private double D;

	public HalsteadMetric(Hashtable<String, Integer> operators,
			Hashtable<String, Integer> operands) {
		this.operators = operators;
		this.operands = operands;
		calucate();
	}

	private void calucate() {
		n1 = operators.keySet().size();
		n2 = operands.keySet().size();
		N1 = 0;
		for (String key : operators.keySet()) {
			N1 += operators.get(key);
		}
		N2 = 0;
		for (String key : operands.keySet()) {
			N2 += operands.get(key);
		}
		n = n1 + n2;
		N = N1 + N2;
		HL = n1 * Math.log(n1) / Math.log(2) + n2 * Math.log(n2) / Math.log(2);
		HV = N * Math.log(n) / Math.log(2);
		D = n1 / 2.0 * N2 / n2;
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
		result += "d = " + D + "\n";
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
		for (String operand : operands.keySet()) {
			System.out.println(operand + "\t"
					+ operands.get(operand).toString());
		}
	}

}
