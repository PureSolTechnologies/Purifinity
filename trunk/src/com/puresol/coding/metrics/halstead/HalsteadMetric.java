/***************************************************************************
 *
 *   HalsteadMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.trees.TreeIterator;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.Property;

public class HalsteadMetric extends AbstractEvaluator implements
		CodeRangeEvaluator {

	private static final long serialVersionUID = -7823038852668468658L;

	private static final Translator translator = Translator
			.getTranslator(HalsteadMetric.class);

	public static final String NAME = translator.i18n("Halstead Metric");
	public static final String DESCRIPTION = translator
			.i18n("Halstead Metric calculation.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(HalsteadMetric.class, "enabled",
				"Switches calculation of Halstead Metric on and off.",
				Boolean.class, "true"));
	}
	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
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

	private final CodeRange codeRange;
	private final LanguageDependedHalsteadMetric langDepended;

	public HalsteadMetric(ProgrammingLanguage language, CodeRange codeRange) {
		super();
		this.codeRange = codeRange;
		langDepended = language
				.getImplementation(LanguageDependedHalsteadMetric.class);
		if (langDepended == null) {
			throw new RuntimeException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CodeRange getCodeRange() {
		return codeRange;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		if (getMonitor() != null) {
			getMonitor().setRange(0, 1);
			getMonitor().setDescription(NAME);
		}
		createHashtables();
		calculateValues();
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	private void createHashtables() {
		TreeIterator<ParserTree> iterator = new TreeIterator<ParserTree>(
				codeRange.getParserTree());
		do {
			ParserTree node = iterator.getCurrentNode();
			HalsteadResult result = langDepended.getHalsteadResult(node);
			if (result.isCountable()) {
				if (result.isOperator()) {
					addOperator(result.getSymbol());
				} else {
					addOperant(result.getSymbol());
				}
			}
		} while (iterator.goForward());
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QualityLevel getQuality() {
		if ((codeRange.getType() == CodeRangeType.FILE)
				|| (codeRange.getType() == CodeRangeType.CLASS)
				|| (codeRange.getType() == CodeRangeType.ENUMERATION)) {
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
		} else if ((codeRange.getType() == CodeRangeType.CONSTRUCTOR)
				|| (codeRange.getType() == CodeRangeType.METHOD)
				|| (codeRange.getType() == CodeRangeType.FUNCTION)
				|| (codeRange.getType() == CodeRangeType.INTERFACE)) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		} else {
			throw new UnsupportedFormatException(format);
		}
	}

	public String getNumberReport() {
		String report = "n1\t" + get_n1() + "\t"
				+ translator.i18n("Number of different operators") + "\n";
		report += "N1\t" + get_N1() + "\t"
				+ translator.i18n("Total number operators") + "\n";
		report += "n2\t" + get_n2() + "\t"
				+ translator.i18n("Number of different operands") + "\n";
		report += "N2\t" + get_N2() + "\t"
				+ translator.i18n("Total number of operands") + "\n";
		report += "n\t" + Math.round(get_n() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Vocabulary size") + "\n";
		report += "N\t" + Math.round(get_N() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Program length") + "\n";
		report += "HL\t" + Math.round(get_HL() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Halstead length") + "\n";
		report += "HV\t" + Math.round(get_HV() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Halstead volume") + "\n";
		report += "D\t" + Math.round(get_D() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Difficulty level") + "\n";
		report += "L\t" + Math.round(get_L() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Program level") + "\n";
		report += "E\t" + Math.round(get_E() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Effort to implement") + "\n";
		report += "T\t" + Math.round(get_T() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Implementatiom time [s]") + "\n";
		report += "B\t" + Math.round(get_B() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Number of delivered bugs") + "\n";
		return report;
	}

	public String getOperatorReport() {
		String report = "";
		for (String operator : getOperators().keySet()) {
			int number = getOperators().get(operator);
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
			int number = getOperants().get(operant);
			report += operant + "\t" + number + "\n";
		}
		return report;
	}

	public String getHTMLOperantReport() {
		return HTMLStandards.convertTSVToTable(getOperantReport());
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("Halstead Metric") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += HTMLStandards.convertTSVToTable(getNumberReport());

		report += "<b>" + translator.i18n("Operators") + "</b>";
		report += getHTMLOperatorReport();

		report += "<b>" + translator.i18n("Operands") + "</b>";
		report += getHTMLOperantReport();
		return report;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
