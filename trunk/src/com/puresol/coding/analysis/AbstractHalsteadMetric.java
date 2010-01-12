/***************************************************************************
 *
 *   HalsteadMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.util.Hashtable;

import javax.i18n4j.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.coding.tokentypes.SymbolType;
import com.puresol.html.HTMLStandards;
import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

abstract public class AbstractHalsteadMetric extends AbstractMetric {

    private static final Translator translator =
	    Translator.getTranslator(AbstractHalsteadMetric.class);

    private Hashtable<String, Integer> operators =
	    new Hashtable<String, Integer>();
    private Hashtable<String, Integer> operants =
	    new Hashtable<String, Integer>();

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

    public AbstractHalsteadMetric(CodeRange codeRange) {
	super(codeRange);
	createHashtables();
	calculate();
    }

    private void createHashtables() {
	CodeRange codeRange = getCodeRange();
	TokenStream tokenStream = codeRange.getTokenStream();
	for (int index = codeRange.getStart(); index <= codeRange
		.getStop(); index++) {
	    Token token = tokenStream.get(index);
	    if (token.getPublicity() != TokenPublicity.HIDDEN) {
		SourceTokenDefinition def =
			(SourceTokenDefinition) token
				.getDefinitionInstance();
		if (def.countForHalstead(token, tokenStream)) {
		    if (def.getSymbolType() == SymbolType.OPERANT) {
			addOperand(def.getHalsteadSymbol());
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

    private void addOperand(String operand) {
	if (operants.containsKey(operand)) {
	    operants.put(operand, operants.get(operand) + 1);
	} else {
	    operants.put(operand, 1);
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
	HL =
		n1 * Math.log(n1) / Math.log(2) + n2 * Math.log(n2)
			/ Math.log(2);
	HV = N * Math.log(n) / Math.log(2);
	D = n1 / 2.0 * N2 / n2;
	L = 1 / D;
	E = HV * D;
	T = E / 18.0;
	B = Math.exp(2 / 3 * Math.log(E)) / 3000.0;
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

    public String getReport() {
	String report =
		"n1\t" + get_n1() + "\t"
			+ translator.i18n("Number of different operators")
			+ "\n";
	report +=
		"N1\t" + get_N1() + "\t"
			+ translator.i18n("Total number operators") + "\n";
	report +=
		"n2\t" + get_n2() + "\t"
			+ translator.i18n("Number of different operands")
			+ "\n";
	report +=
		"N2\t" + get_N2() + "\t"
			+ translator.i18n("Total number of operands")
			+ "\n";
	report +=
		"n\t" + Math.round(get_n() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Vocabulary size") + "\n";
	report +=
		"N\t" + Math.round(get_N() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Program length") + "\n";
	report +=
		"HL\t" + Math.round(get_HL() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Halstead length") + "\n";
	report +=
		"HV\t" + Math.round(get_HV() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Halstead volume") + "\n";
	report +=
		"D\t" + Math.round(get_D() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Difficulty level") + "\n";
	report +=
		"L\t" + Math.round(get_L() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Program level") + "\n";
	report +=
		"E\t" + Math.round(get_E() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Effort to implement") + "\n";
	report +=
		"T\t" + Math.round(get_T() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Implementatiom time [s]")
			+ "\n";
	report +=
		"B\t" + Math.round(get_B() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Number of delivered bugs")
			+ "\n";
	return report;
    }

    public String getHTMLReport() {
	return HTMLStandards.convertTSVToTable(getReport());
    }

    public String getOperatorReport() {
	String report = "";
	for (String operator : getOperators().keySet()) {
	    int number = operators.get(operator);
	    report += operator + "\t" + number + "\n";
	}
	return report;
    }

    public String getHTMLOperatorReport() {
	return HTMLStandards.convertTSVToTable(getOperatorReport());
    }

    public String getOperantReport() {
	String report = "";
	for (String operant : getOperants().keySet()) {
	    int number = operants.get(operant);
	    report += operant + "\t" + number + "\n";
	}
	return report;
    }

    public String getHTMLOperantReport() {
	return HTMLStandards.convertTSVToTable(getOperantReport());
    }
}
