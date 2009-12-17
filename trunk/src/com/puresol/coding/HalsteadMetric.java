package com.puresol.coding;

import java.util.Hashtable;

public class HalsteadMetric extends AbstractMetric {

	private Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
	private Hashtable<String, Integer> operands = new Hashtable<String, Integer>();

	/**
	 * number of different operators
	 */
	private int n1;
	/**
	 * number of different operands
	 */
	private int n2;
	/**
	 * total number of operators
	 */
	private int N1;
	/**
	 * total number of operands
	 */
	private int N2;
	/**
	 * Vocabulary size
	 */
	private double n;
	/**
	 * Program length
	 */
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
		super(codeRange);
		createHashtables();
		calculate();
	}

	private void createHashtables() {
		CodeRange codeRange = getCodeRange();
		Hashtable<Integer, TokenContent> tokenContents = codeRange
				.getTokenContents();
		for (int index = codeRange.getStart(); index <= codeRange.getStop(); index++) {
			if (tokenContents.containsKey(index)) {
				TokenContent content = tokenContents.get(index);
				if (content.isOperand()) {
					addOperand(content.getText());
				}
				if (content.isOperator()) {
					addOperator(content.getText());
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

	private void addOperand(String operand) {
		if (operands.containsKey(operand)) {
			operands.put(operand, operands.get(operand) + 1);
		} else {
			operands.put(operand, 1);
		}
	}

	private void calculate() {
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
		for (String operand : operands.keySet()) {
			System.out.println(operand + "\t"
					+ operands.get(operand).toString());
		}
	}

	public Hashtable<String, Integer> getOperators() {
		return operators;
	}

	public Hashtable<String, Integer> getOperands() {
		return operands;
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

	public void print() {
		System.out.println("n1 = " + n1);
		System.out.println("n2 = " + n2);
		System.out.println("N1 = " + N1);
		System.out.println("N2 = " + N2);
		System.out.println("n = " + n);
		System.out.println("N = " + N);
		System.out.println("HL = " + HL);
		System.out.println("HV = " + HV);
		System.out.println("D = " + D);
		System.out.println("L = " + L);
		System.out.println("E = " + E);
		System.out.println("T = " + T);
		System.out.println("B = " + B);
	}

	@Override
	public QualityLevel getQualityLevel() {
		CodeRange range = getCodeRange();
		if ((range.getType() == CodeRangeType.FILE)
				|| (range.getType() == CodeRangeType.CLASS)
				|| (range.getType() == CodeRangeType.ENUMERATION)) {
			if (HV < 80) {
				return QualityLevel.MEDIUM;
			}
			if (HV > 8000) {
				return QualityLevel.MEDIUM;
			}
			if (HV > 10000) {
				return QualityLevel.LOW;
			}
			return QualityLevel.HIGH;
		} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getType() == CodeRangeType.METHOD)
				|| (range.getType() == CodeRangeType.FUNCTION)
				|| (range.getType() == CodeRangeType.INTERFACE)) {
			if (HV < 10) {
				return QualityLevel.MEDIUM;
			}
			if (HV > 1000) {
				return QualityLevel.MEDIUM;
			}
			if (HV > 1250) {
				return QualityLevel.LOW;
			}
			return QualityLevel.HIGH;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	@Override
	public boolean isSuitable(CodeRange codeRange) {
		return true;
	}
}
