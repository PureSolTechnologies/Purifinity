/***************************************************************************
 *
 *   EntropyMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.versioning.Version;

/**
 * This class calculates entropy and redundancy for a code range. The entropy is
 * only calculated for operands due to the fact that only they are chosen by the
 * programmer. The entropy gives therefore a hint how well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric extends AbstractCodeRangeEvaluator {

    public static final String ID = EntropyMetric.class.getName();

    public static final String NAME = "Entropy Metric";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "Entropy Metric calculation.";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {};

    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.ANALYSABILITY };

    public static final Set<String> DEPENDENCIES = new HashSet<>();

    static {
	DEPENDENCIES.add(HalsteadMetric.ID);
    }

    private final HalsteadMetric halstead;
    private EntropyMetricResult result;

    public EntropyMetric(AnalysisRun analysisRun, CodeRange codeRange) {
	super(NAME, analysisRun, codeRange);
	halstead = new HalsteadMetric(analysisRun, getCodeRange());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean run() {
	boolean retVal = calculate();
	recreateResultsList();
	return retVal;
    }

    private boolean calculate() {
	halstead.run();

	Hashtable<String, Integer> operands = halstead.getOperands();

	double maxEntropy = Math.log(halstead.getDifferentOperands()) / Math.log(2.0);
	double entropy = 0.0;
	for (String operant : operands.keySet()) {
	    int count = operands.get(operant);
	    entropy += count / (double) halstead.getTotalOperands()
		    * Math.log(count / (double) halstead.getTotalOperands()) / Math.log(2.0);
	}
	entropy *= -1.0;
	double normEntropy = entropy / maxEntropy;
	double entropyRedundancy = maxEntropy - entropy;
	double redundancy = entropyRedundancy * halstead.getDifferentOperands() / maxEntropy;
	double normalizedRedundancy = redundancy / halstead.getDifferentOperands();
	result = new EntropyMetricResult(halstead.getVocabularySize(), halstead.getProgramLength(), entropy, maxEntropy,
		normEntropy, entropyRedundancy, redundancy, normalizedRedundancy);
	return true;
    }

    private void recreateResultsList() {

    }

    public double getNTotal() {
	return result.getNTotal();
    }

    public double getNDiff() {
	return result.getNDiff();
    }

    public double getMaxEntropy() {
	return result.getMaxEntropy();
    }

    public double getEntropy() {
	return result.getEntropy();
    }

    public double getNormEntropy() {
	return result.getNormEntropy();
    }

    public double getEntropyRedundancy() {
	return result.getEntropyRedundancy();
    }

    public double getRedundancy() {
	return result.getRedundancy();
    }

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
    public Severity getQuality() {
	if (getNormalizedRedundancy() > 0.40) {
	    return Severity.CRITICAL;
	}
	if (getNormalizedRedundancy() > 0.20) {
	    return Severity.MAJOR;
	}
	return Severity.NONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
	return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<MetricValue<?>> getResults() {
	return result.getResults();
    }
}
