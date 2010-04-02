/***************************************************************************
 *
 *   HalsteadMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.evaluator.metric;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.AbstractMetric;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.coding.tokentypes.SymbolType;
import com.puresol.parser.Token;
import com.puresol.parser.TokenException;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.utils.Property;

public class HalsteadMetric extends AbstractMetric {

	private static final Logger logger = Logger.getLogger(HalsteadMetric.class);
	private static final Translator translator = Translator
			.getTranslator(HalsteadMetric.class);

	public static final String NAME = "Halstead Metric"; 
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(HalsteadMetric.class, "enabled",
				"Switches calculation of Halstead Metric on and off.", Boolean.class,
				"true"));
	}

	private final Hashtable<String, Integer> operators = new Hashtable<String, Integer>();
	private final Hashtable<String, Integer> operants = new Hashtable<String, Integer>();

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
		calculate();
	}

	private void calculate() {
		try {
			createHashtables();
			calculateValues();
		} catch (TokenException e) {
			logger.error(e);
		}
	}

	private void createHashtables() throws TokenException {
		CodeRange codeRange = getCodeRange();
		TokenStream tokenStream = codeRange.getTokenStream();
		for (int index = codeRange.getStart(); index <= codeRange.getStop(); index++) {
			Token token = tokenStream.get(index);
			if (token.getPublicity() != TokenPublicity.HIDDEN) {
				SourceTokenDefinition def;
				def = (SourceTokenDefinition) token.getDefinitionInstance();
				if (def.countForHalstead(token, tokenStream)) {
					if (def.getSymbolType() == SymbolType.OPERANT) {
						addOperant(token.getText());
					}
					if (def.getSymbolType() == SymbolType.OPERATOR) {
						addOperator(def.getHalsteadSymbol());
					}
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

	private void calculateValues() {
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
		B = Math.exp(2.0 / 3.0 * Math.log(E)) / 3000.0;
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

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	@Override
	public QualityLevel getQualityLevel() {
		CodeRange range = getCodeRange();
		if ((range.getType() == CodeRangeType.FILE)
				|| (range.getType() == CodeRangeType.CLASS)
				|| (range.getType() == CodeRangeType.ENUMERATION)) {
			if (get_HV() < 80) {
				return QualityLevel.MEDIUM;
			}
			if (get_HV() > 10000) {
				return QualityLevel.LOW;
			}
			if (get_HV() > 8000) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		} else if ((range.getType() == CodeRangeType.CONSTRUCTOR)
				|| (range.getType() == CodeRangeType.METHOD)
				|| (range.getType() == CodeRangeType.FUNCTION)
				|| (range.getType() == CodeRangeType.INTERFACE)) {
			if (get_HV() < 10) {
				return QualityLevel.MEDIUM;
			}
			if (get_HV() > 1250) {
				return QualityLevel.LOW;
			}
			if (get_HV() > 1000) {
				return QualityLevel.MEDIUM;
			}
			return QualityLevel.HIGH;
		}
		return QualityLevel.HIGH; // not evaluated...
	}

	@Override
	public String getName() {
		return translator.i18n("Halstead metric");
	}
}
