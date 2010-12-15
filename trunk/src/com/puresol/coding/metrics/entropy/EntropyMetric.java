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
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
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
public class EntropyMetric extends AbstractEvaluator implements
		CodeRangeEvaluator {

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

	private final CodeRange codeRange;
	private final ProgrammingLanguage language;
	private EntropyResult result;

	public EntropyMetric(ProgrammingLanguage language, CodeRange codeRange) {
		super();
		this.codeRange = codeRange;
		this.language = language;
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
			getMonitor().setRange(0, 2);
			getMonitor().setDescription(NAME);
		}
		HalsteadMetric halstead = new HalsteadMetric(language, getCodeRange());
		halstead.run();
		if (getMonitor() != null) {
			getMonitor().setStatus(1);
		}

		Hashtable<String, Integer> operands = halstead.getOperands();

		double maxEntropy = Math.log((double) halstead.getDifferentOperands())
				/ Math.log(2.0);
		double entropy = 0.0;
		for (String operant : operands.keySet()) {
			int count = operands.get(operant);
			entropy += (double) count
					/ (double) halstead.getTotalOperands()
					* Math.log((double) count
							/ (double) halstead.getTotalOperands())
					/ Math.log(2.0);
		}
		entropy *= -1.0;
		double normEntropy = entropy / maxEntropy;
		double entropyRedundancy = maxEntropy - entropy;
		double redundancy = entropyRedundancy * halstead.getDifferentOperands()
				/ maxEntropy;
		double normalizedRedundancy = redundancy
				/ halstead.getDifferentOperands();
		if (getMonitor() != null) {
			getMonitor().finish();
		}
		this.result = new EntropyResult(halstead.getVocabularySize(), halstead
				.getProgramLength(), entropy, maxEntropy, normEntropy,
				entropyRedundancy, redundancy, normalizedRedundancy);
	}

	public double getNTotal() {
		return result.getNTotal();
	}

	public double getNDiff() {
		return result.getNDiff();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getMaxEntropy()
	 */
	public double getMaxEntropy() {
		return result.getMaxEntropy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getEntropy()
	 */
	public double getEntropy() {
		return result.getEntropy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getNormEntropy()
	 */
	public double getNormEntropy() {
		return result.getNormEntropy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getEntropyRedundancy()
	 */
	public double getEntropyRedundancy() {
		return result.getEntropyRedundancy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getRedundancy()
	 */
	public double getRedundancy() {
		return result.getRedundancy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.coding.analysis.EntropyMetric#getNormRedundancy()
	 */
	public double getNormalizedRedundancy() {
		return result.getNormalizedRedundancy();
	}

	public String getResultsAsString() {
		String result = "number of symbols = " + getNTotal() + "\n";
		result += "max entropy = " + getMaxEntropy() + "\n";
		result += "entropy = " + getEntropy() + "\n";
		result += "normalized entropy = " + getNormEntropy() + "\n";
		result += "entropy redundance = " + getEntropyRedundancy() + "\n";
		result += "redundancy = " + getRedundancy() + "\n";
		result += "normalized redundancy = " + getNormalizedRedundancy() + "\n";
		return result;
	}

	public void print() {
		System.out.println(getResultsAsString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceCodeQuality getQuality() {
		if (getNormalizedRedundancy() > 0.40) {
			return SourceCodeQuality.LOW;
		}
		if (getNormalizedRedundancy() > 0.20) {
			return SourceCodeQuality.MEDIUM;
		}
		return SourceCodeQuality.HIGH;
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
		if (format == ReportingFormat.TEXT) {
			return getTextReport();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLReport();
		}
		throw new UnsupportedFormatException(format);
	}

	public String getTextReport() {
		String report = "n\t" + Math.round(getNTotal() * 100.0) / 100.0 + "\t"
				+ translator.i18n("Vocabulary size") + "\n";
		report += "N\t" + Math.round(getNDiff() * 100.0) / 100.0 + "\t"
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
		report += "normRedundancy\t"
				+ Math.round(getNormalizedRedundancy() * 100.0) / 100.0 + "\t"
				+ translator.i18n("ratio of redundand vocables") + "\n";
		return report;
	}

	public String getHTMLReport() {
		String report = Anchor.generate(getName(), "<h3>"
				+ translator.i18n("Entropy from Information Theory") + "</h3>");
		report += HTMLConverter.convertQualityLevelToHTML(getQuality());
		report += "<br/>";
		report += HTMLStandards.convertTSVToTable(getTextReport());
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
