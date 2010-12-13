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
import com.puresol.coding.quality.SourceCodeQuality;
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
	private int differentOperators;
	/**
	 * number of different operands
	 */
	private int differentOperands;
	/**
	 * total number of operators
	 */
	private int totalOperators;
	/**
	 * total number of operands
	 */
	private int totalOperands;
	/**
	 * Vocabulary size
	 */
	private double vocabularySize;
	/**
	 * Program length
	 */
	private double programLength;
	/**
	 * Halstead Length
	 */
	private double halsteadLength;
	/**
	 * Halstead Volume
	 */
	private double halsteadVolume;
	/**
	 * Difficulty
	 */
	private double difficulty;
	/**
	 * Program Level
	 */
	private double programLevel;
	/**
	 * Implementation Effort.
	 */
	private double implementationEffort;
	/**
	 * Implementation Time [s]
	 */
	private double implementationTime;
	/**
	 * Estimated Bugs
	 */
	private double estimatedBugs;

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
		differentOperators = operators.keySet().size();
		differentOperands = operants.keySet().size();
		totalOperators = 0;
		for (String key : operators.keySet()) {
			totalOperators += operators.get(key);
		}
		totalOperands = 0;
		for (String key : operants.keySet()) {
			totalOperands += operants.get(key);
		}
		vocabularySize = differentOperators + differentOperands;
		programLength = totalOperators + totalOperands;
		halsteadLength = differentOperators * Math.log(differentOperators)
				/ Math.log(2) + differentOperands * Math.log(differentOperands)
				/ Math.log(2);
		halsteadVolume = programLength * Math.log(vocabularySize) / Math.log(2);
		difficulty = differentOperators / 2.0 * totalOperands
				/ differentOperands;
		programLevel = 1 / difficulty;
		implementationEffort = halsteadVolume * difficulty;
		implementationTime = implementationEffort / 18.0;
		estimatedBugs = Math.exp(2.0 / 3.0 * Math.log(implementationEffort)) / 3000.0;
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

	public Hashtable<String, Integer> getOperands() {
		return operants;
	}

	public int getDifferentOperators() {
		return differentOperators;
	}

	public int getDifferendOperands() {
		return differentOperands;
	}

	public int getTotalOperators() {
		return totalOperators;
	}

	public int getTotalOperands() {
		return totalOperands;
	}

	public double getVocabularySize() {
		return vocabularySize;
	}

	public double getProgramLength() {
		return programLength;
	}

	public double getHalsteadLength() {
		return halsteadLength;
	}

	public double getHalsteadVolume() {
		return halsteadVolume;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public double getProgramLevel() {
		return programLevel;
	}

	public double getImplementationEffort() {
		return implementationEffort;
	}

	public double getImplementationTime() {
		return implementationTime;
	}

	public double getEstimatedBugs() {
		return estimatedBugs;
	}

	public void print() {
		System.out.println("n1 = " + differentOperators);
		System.out.println("n2 = " + differentOperands);
		System.out.println("N1 = " + totalOperators);
		System.out.println("N2 = " + totalOperands);
		System.out.println("n = " + vocabularySize);
		System.out.println("N = " + programLength);
		System.out.println("HL = " + halsteadLength);
		System.out.println("HV = " + halsteadVolume);
		System.out.println("D = " + difficulty);
		System.out.println("L = " + programLevel);
		System.out.println("E = " + implementationEffort);
		System.out.println("T = " + implementationTime);
		System.out.println("B = " + estimatedBugs);
	}

	public static boolean isSuitable(CodeRange codeRange) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		if ((codeRange.getType() == CodeRangeType.FILE)
				|| (codeRange.getType() == CodeRangeType.CLASS)
				|| (codeRange.getType() == CodeRangeType.ENUMERATION)) {
			if (getHalsteadVolume() < 80) {
				return SourceCodeQuality.MEDIUM;
			}
			if (getHalsteadVolume() > 10000) {
				return SourceCodeQuality.LOW;
			}
			if (getHalsteadVolume() > 8000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		} else if ((codeRange.getType() == CodeRangeType.CONSTRUCTOR)
				|| (codeRange.getType() == CodeRangeType.METHOD)
				|| (codeRange.getType() == CodeRangeType.FUNCTION)
				|| (codeRange.getType() == CodeRangeType.INTERFACE)) {
			if (getHalsteadVolume() < 10) {
				return SourceCodeQuality.MEDIUM;
			}
			if (getHalsteadVolume() > 1250) {
				return SourceCodeQuality.LOW;
			}
			if (getHalsteadVolume() > 1000) {
				return SourceCodeQuality.MEDIUM;
			}
			return SourceCodeQuality.HIGH;
		}
		return SourceCodeQuality.HIGH; // not evaluated...
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
		String report = "n1\t" + getDifferentOperators() + "\t"
				+ translator.i18n("Number of different operators") + "\n";
		report += "N1\t" + getTotalOperators() + "\t"
				+ translator.i18n("Total number operators") + "\n";
		report += "n2\t" + getDifferendOperands() + "\t"
				+ translator.i18n("Number of different operands") + "\n";
		report += "N2\t" + getTotalOperands() + "\t"
				+ translator.i18n("Total number of operands") + "\n";
		report += "n\t" + Math.round(getVocabularySize() * 100.0) / 100.0
				+ "\t" + translator.i18n("Vocabulary size") + "\n";
		report += "N\t" + Math.round(getProgramLength() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Program length") + "\n";
		report += "HL\t" + Math.round(getHalsteadLength() * 100.0) / 100.0
				+ "\t" + translator.i18n("Halstead length") + "\n";
		report += "HV\t" + Math.round(getHalsteadVolume() * 100.0) / 100.0
				+ "\t" + translator.i18n("Halstead volume") + "\n";
		report += "D\t" + Math.round(getDifficulty() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Difficulty level") + "\n";
		report += "L\t" + Math.round(getProgramLevel() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Program level") + "\n";
		report += "E\t" + Math.round(getImplementationEffort() * 100.0) / 100.0
				+ "\t" + translator.i18n("Effort to implement") + "\n";
		report += "T\t" + Math.round(getImplementationTime() * 100.0) / 100.0
				+ "\t" + translator.i18n("Implementatiom time [s]") + "\n";
		report += "B\t" + Math.round(getEstimatedBugs() * 100.0) / 100.0 + "\t"
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
		for (String operant : getOperands().keySet()) {
			int number = getOperands().get(operant);
			report += operant + "\t" + number + "\n";
		}
		return report;
	}

	public String getHTMLOperantReport() {
		return HTMLStandards.convertTSVToTable(getOperantReport());
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(), "<h3>"
				+ translator.i18n("Halstead Metric") + "</h3>");
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
