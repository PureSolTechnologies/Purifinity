/***************************************************************************
 *
 *   EntropyMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.metrics;

import java.util.Hashtable;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.QualityLevel;

/**
 * This class calculates entropy and redundancy for a code range. The
 * entropy is only calculated for operands due to the fact that only they
 * are chosen by the programmer. The entropy gives therefore a hint how
 * well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric implements Metric {

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
	Hashtable<String, Integer> operands = halstead.getOperants();

	maxEntropy =
		-Math.log(1 / (double) halstead.get_n2()) / Math.log(2.0);
	Entropy = 0.0;
	for (String operand : operands.keySet()) {
	    int count = operands.get(operand);
	    Entropy +=
		    (double) count
			    / (double) halstead.get_N2()
			    * Math.log((double) count
				    / (double) halstead.get_N2())
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
     * @see
     * com.puresol.coding.analysis.EntropyMetric#getEntropyRedundancy()
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
}
