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

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.utils.Property;

/**
 * This class calculates entropy and redundancy for a code range. The entropy is
 * only calculated for operands due to the fact that only they are chosen by the
 * programmer. The entropy gives therefore a hint how well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric implements Metric {

	private static final long serialVersionUID = 1300404171923622327L;

	private static final Translator translator = Translator
			.getTranslator(EntropyMetric.class);

	public static final String NAME = translator.i18n("Entropy Metric");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();
	static {
		SUPPORTED_PROPERTIES.add(new Property(EntropyMetric.class, "enabled",
				"Switches calculation of EntropyMetric on and off.",
				Boolean.class, "true"));
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
	private final HalsteadMetric halstead;

	public EntropyMetric(CodeRange codeRange) {
		halstead = new HalsteadMetric(codeRange);
		calculate();
	}

	private void calculate() {
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
	public QualityLevel getQualityLevel() {
		if (getNormRedundancy() > 0.40) {
			return QualityLevel.LOW;
		}
		if (getNormRedundancy() > 0.20) {
			return QualityLevel.MEDIUM;
		}
		return QualityLevel.HIGH;
	}

	@Override
	public CodeRange getCodeRange() {
		return halstead.getCodeRange();
	}

	@Override
	public String getName() {
		return NAME;
	}
}
