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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

/**
 * This class calculates entropy and redundancy for a code range. The entropy is
 * only calculated for operands due to the fact that only they are chosen by the
 * programmer. The entropy gives therefore a hint how well the programmed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EntropyMetric extends CodeRangeEvaluator {

    private static final long serialVersionUID = 1300404171923622327L;

    public static final String NAME = "Entropy Metric";

    public static final String DESCRIPTION = "Entropy Metric calculation.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
    }

    private final CodeRange codeRange;
    private final HalsteadMetric halstead;
    private EntropyResult result;

    public EntropyMetric(ProgrammingLanguage language, CodeRange codeRange) {
	super(NAME);
	this.codeRange = codeRange;
	halstead = new HalsteadMetric(language, getCodeRange());
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
    public IStatus run(IProgressMonitor monitor) {
	IStatus retVal = calculate(monitor);
	recreateResultsList();
	return retVal;
    }

    private IStatus calculate(IProgressMonitor monitor) {
	monitor.beginTask(NAME, 2);
	halstead.schedule();
	monitor.worked(1);

	Hashtable<String, Integer> operands = halstead.getOperands();

	double maxEntropy = Math.log(halstead.getDifferentOperands())
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
	result = new EntropyResult(halstead.getVocabularySize(),
		halstead.getProgramLength(), entropy, maxEntropy, normEntropy,
		entropyRedundancy, redundancy, normalizedRedundancy);
	monitor.done();
	return Status.OK_STATUS;
    }

    private void recreateResultsList() {

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
    public String getDescription() {
	return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<Result> getResults() {
	return result.getResults();
    }
}
