/***************************************************************************
 *
 *   EntropyMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.entropy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4java.Translator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractCodeRangeEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.QualityLevel;
import com.puresol.coding.reporting.HTMLConverter;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedFormatException;
import com.puresol.reporting.html.Anchor;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

/**
 * This class calculates entropy and redundancy for a code range. The entropy is
 * only calculated for operands due to the fact that only they are chosen by the
 * programmer. The entropy gives therefore a hint how well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric extends AbstractCodeRangeEvaluator {

	private static final long serialVersionUID = 1300404171923622327L;

	private static final Translator translator = Translator
			.getTranslator(EntropyMetric.class);

	public static final String NAME = translator.i18n("Entropy Metric");

	public static final String DESCRIPTION = translator
			.i18n("Entropy Metric calculation.");

	public static final List<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(EntropyMetric.class, "enabled",
				"Switches calculation of EntropyMetric on and off.",
				Boolean.class, "true"));
	}

	public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
	}

	/**
	 * entropy
	 */
	private double Entropy;
	/**
	 * maximum entropy
	 */
	private double maxEntropy;
	/**
	 * normalized entropy
	 */
	private double normEntropy;
	private double EntropyRedundancy;
	private double Redundancy;
	private double normalizedRedundancy;
	private HalsteadMetric halstead = null;
	private final ProgrammingLanguage language;

	public EntropyMetric(ProgrammingLanguage language, CodeRange codeRange) {
		super(codeRange);
		this.language = language;
	}

	@Override
	public void run() {
		if (getMonitor() != null) {
			getMonitor().setRange(0, 2);
			getMonitor().setDescription(NAME);
		}
		halstead = new HalsteadMetric(language, getCodeRange());
		halstead.run();
		if (getMonitor() != null) {
			getMonitor().setStatus(1);
		}

		Hashtable<String, Integer> operants = halstead.getOperants();

		maxEntropy = Math.log((double) halstead.get_n2()) / Math.log(2.0);
		Entropy = 0.0;
		for (String operant : operants.keySet()) {
			int count = operants.get(operant);
			Entropy += (double) count / (double) halstead.get_N2()
					* Math.log((double) count / (double) halstead.get_N2())
					/ Math.log(2.0);
		}
		Entropy *= -1.0;
		normEntropy = Entropy / maxEntropy;
		EntropyRedundancy = maxEntropy - Entropy;
		Redundancy = EntropyRedundancy * halstead.get_n2() / maxEntropy;
		normalizedRedundancy = Redundancy / halstead.get_n2();
		if (getMonitor() != null) {
			getMonitor().finish();
		}
	}

	public double get_n() {
		return halstead.get_n();
	}

	public double get_N() {
		return halstead.get_N();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getMaxEntropy()
	 */
	public double getMaxEntropy() {
		return maxEntropy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getEntropy()
	 */
	public double getEntropy() {
		return Entropy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getNormEntropy()
	 */
	public double getNormEntropy() {
		return normEntropy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getEntropyRedundancy()
	 */
	public double getEntropyRedundancy() {
		return EntropyRedundancy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getRedundancy()
	 */
	public double getRedundancy() {
		return Redundancy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getNormRedundancy()
	 */
	public double getNormRedundancy() {
		return normalizedRedundancy;
	}

	public String getResultsAsString() {
		String result = "number of symbols = " + halstead.get_n() + "\n";
		result += "max entropy = " + maxEntropy + "\n";
		result += "entropy = " + Entropy + "\n";
		result += "normalized entropy = " + normEntropy + "\n";
		result += "entropy redundance = " + EntropyRedundancy + "\n";
		result += "redundancy = " + Redundancy + "\n";
		result += "normalized redundancy = " + normalizedRedundancy + "\n";
		return result;
	}

	public void print() {
		System.out.println(getResultsAsString());
	}

	@Override
	public QualityLevel getQuality() {
		if (getNormRedundancy() > 0.40) {
			return QualityLevel.LOW;
		}
		if (getNormRedundancy() > 0.20) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getDescription(ReportingFormat format)
			throws UnsupportedFormatException {
		return DESCRIPTION;
	}

	@Override
	public String getReport(ReportingFormat format)
			throws UnsupportedFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextReport();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		}
		throw new UnsupportedFormatException(format);
	}

	public String getTextReport() {
		String report = "n\t" + Math.round(get_n() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Vocabulary size") + "\n";
		report += "N\t" + Math.round(get_N() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Program length") + "\n";
		report += "Entropy\t" + Math.round(getEntropy() * 100.0) / 100.0 + "\t"
				+ translator.i18n("entropy") + "\n";
		report += "maxEntropy\t" + Math.round(getMaxEntropy() * 100.0) / 100.0
				+ "\t" + translator.i18n("maximized entropy") + "\n";
		report += "normEntropy\t" + Math.round(getNormEntropy() * 100.0)
				/ 100.0 + "\t" + translator.i18n("normalized entropy") + "\n";
		report += "Entropy Redundance\t"
				+ Math.round(getEntropyRedundancy() * 100.0) / 100.0 + "\t"
				+ translator.i18n("redundance in entropy") + "\n";
		report += "Redundance\t" + Math.round(getRedundancy() * 100.0) / 100.0
				+ "\t" + translator.i18n("number of redundand vokables") + "\n";
		report += "normRedundancy\t" + Math.round(getNormRedundancy() * 100.0)
				/ 100.0 + "\t" + translator.i18n("ratio of redundand vocables")
				+ "\n";
		return report;
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(),
				"<h3>" + translator.i18n("Entropy from Information Theory")
						+ "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += HTMLStandards.convertTSVToTable(getTextReport());
		return report;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
