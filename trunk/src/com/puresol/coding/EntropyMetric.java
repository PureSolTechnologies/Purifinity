/***************************************************************************
 *
 *   EntropyMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import java.util.Hashtable;

import javax.i18n4j.Translator;

import com.puresol.html.HTMLStandards;

/**
 * This class calculates entropy and redundancy for a code range. The
 * entropy is only calculated for operands due to the fact that only they
 * are chosen by the programmer. The entropy gives therefore a hint how
 * well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class EntropyMetric extends HalsteadMetric {

    private static final Translator translator =
	    Translator.getTranslator(EntropyMetric.class);

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

    public EntropyMetric(CodeRange codeRange) {
	super(codeRange);
	calculate();
    }

    private void calculate() {
	Hashtable<String, Integer> operands = getOperants();

	maxEntropy = -Math.log(1 / (double) get_n2()) / Math.log(2.0);
	Entropy = 0.0;
	for (String operand : operands.keySet()) {
	    int count = operands.get(operand);
	    Entropy +=
		    (double) count / (double) get_N2()
			    * Math.log((double) count / (double) get_N2())
			    / Math.log(2.0);
	}
	Entropy *= -1.0;
	normEntropy = Entropy / maxEntropy;
	EntropyRedundancy = maxEntropy - Entropy;
	Redundancy = EntropyRedundancy * get_n2() / maxEntropy;
	normalizedRedundancy = Redundancy / get_n2();
    }

    public double getMaxEntropy() {
	return maxEntropy;
    }

    public double getEntropy() {
	return Entropy;
    }

    public double getNormEntropy() {
	return normEntropy;
    }

    public double getEntropyRedundancy() {
	return EntropyRedundancy;
    }

    public double getRedundancy() {
	return Redundancy;
    }

    public double getNormRedundancy() {
	return normalizedRedundancy;
    }

    public String getResultsAsString() {
	String result = "number of symbols = " + get_n() + "\n";
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

    public String getReport() {
	String report =
		"n\t" + Math.round(get_n() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Vocabulary size") + "\n";
	report +=
		"N\t" + Math.round(get_N() * 100.0) / 100.0 + "\t"
			+ translator.i18n("Program length") + "\n";
	report +=
		"Entropy\t" + Math.round(getEntropy() * 100.0) / 100.0
			+ "\t" + translator.i18n("entropy") + "\n";
	report +=
		"maxEntropy\t" + Math.round(getMaxEntropy() * 100.0)
			/ 100.0 + "\t"
			+ translator.i18n("maximized entropy") + "\n";
	report +=
		">normEntropy\t" + Math.round(getNormEntropy() * 100.0)
			/ 100.0 + "\t"
			+ translator.i18n("normalized entropy") + "\n";
	report +=
		"Entropy Redundance\t"
			+ Math.round(getEntropyRedundancy() * 100.0)
			/ 100.0 + "\t"
			+ translator.i18n("redundance in entropy") + "\n";
	report +=
		"Redundance\t" + Math.round(getRedundancy() * 100.0)
			/ 100.0 + "\t"
			+ translator.i18n("number of redundand vokables")
			+ "\n";
	report +=
		"normRedundancy\t"
			+ Math.round(getNormRedundancy() * 100.0) / 100.0
			+ "\t"
			+ translator.i18n("ratio of redundand vocables")
			+ "\n";
	return report;
    }

    public String getHTMLReport() {
	return HTMLStandards.convertTSVToTable(getReport());
    }
}
