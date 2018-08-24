/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import java.util.List;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractCodeRangeEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;

public class NormalizedMaintainabilityIndex extends AbstractCodeRangeEvaluator {

    private final SLOCMetricCalculator slocMetric;
    private final McCabeMetric mcCabeMetric;
    private final HalsteadMetric halsteadMetric;
    private NormalizedMaintainabilityIndexResult result;

    public NormalizedMaintainabilityIndex(AnalysisRun analysisRun, CodeRange codeRange) {
	super(NormalizedMaintainabilityIndexEvaluator.NAME, analysisRun, codeRange);
	slocMetric = new SLOCMetricCalculator(analysisRun, getCodeRange());
	mcCabeMetric = new McCabeMetric(analysisRun, getCodeRange());
	halsteadMetric = new HalsteadMetric(analysisRun, getCodeRange());
    }

    private void checkInput() {
	if (getCodeRange() != slocMetric.getCodeRange()) {
	    throw new IllegalArgumentException("Code ranges must be same!!!");
	}
	if (getCodeRange() != mcCabeMetric.getCodeRange()) {
	    throw new IllegalArgumentException("Code ranges must be same!!!");
	}
	if (getCodeRange() != halsteadMetric.getCodeRange()) {
	    throw new IllegalArgumentException("Code ranges must be same!!!");
	}
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public boolean run() {
	checkInput();

	slocMetric.run();
	mcCabeMetric.run();
	halsteadMetric.run();

	SLOCMetric sloc = slocMetric.getSLOCResult();
	double MIwoc = 171.0 - 5.2 * Math.log(halsteadMetric.getHalsteadVolume())
		- 0.23 * mcCabeMetric.getCyclomaticNumber() - 16.2 * Math.log(sloc.getPhyLOC() * 100.0 / 171.0);
	double MIcw = 50 * Math.sin(Math.sqrt(2.4 * sloc.getComLOC() / sloc.getPhyLOC()));
	result = new NormalizedMaintainabilityIndexResult(MIwoc, MIcw);
	return true;
    }

    public void print() {
	System.out.println("NMIwoc = " + result.getNMIwoc());
	System.out.println("NMIcw = " + result.getNMIcw());
	System.out.println("NMI = " + result.getNMI());
    }

    public double getMIWoc() {
	return result.getNMIwoc();
    }

    public double getMIcw() {
	return result.getNMIcw();
    }

    public double getMI() {
	return result.getNMI();
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public Severity getQuality() {
	return NormalizedMaintainabilityQuality.get(getCodeRange().getType(), result);
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public String getDescription() {
	return NormalizedMaintainabilityIndexEvaluator.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<MetricValue<?>> getResults() {
	return result.getResults();
    }

}
