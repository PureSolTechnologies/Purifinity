/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.normmaint;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.metrics.mccabe.McCabeMetric;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.metrics.sloc.SLOCResult;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class NormalizedMaintainabilityIndex extends AbstractEvaluator implements
	CodeRangeEvaluator {

    private static final long serialVersionUID = 2789695185933616684L;

    public static final String NAME = "Normalized Maintainability Index";
    public static final String DESCRIPTION = "Normalized Maintainability Index calculation.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.CHANGEABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.TESTABILITY);
    }

    private final CodeRange codeRange;
    private final SLOCMetric slocMetric;
    private final McCabeMetric mcCabeMetric;
    private final HalsteadMetric halsteadMetric;
    private NormalizedMaintainabilityIndexResult result;

    public NormalizedMaintainabilityIndex(ProgrammingLanguage language,
	    CodeRange codeRange) {
	super();
	this.codeRange = codeRange;
	slocMetric = new SLOCMetric(language, getCodeRange());
	mcCabeMetric = new McCabeMetric(language, getCodeRange());
	halsteadMetric = new HalsteadMetric(language, getCodeRange());
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public CodeRange getCodeRange() {
	return codeRange;
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
    public void run() {
	if (getMonitor() != null) {
	    getMonitor().setRange(0, 4);
	    getMonitor().setTitle(NAME);
	}

	checkInput();

	slocMetric.run();
	if (getMonitor() != null) {
	    getMonitor().setStatus(1);
	}
	mcCabeMetric.run();
	if (getMonitor() != null) {
	    getMonitor().setStatus(2);
	}
	halsteadMetric.run();
	if (getMonitor() != null) {
	    getMonitor().setStatus(3);
	}

	SLOCResult sloc = slocMetric.getResult();
	double MIwoc = 171.0 - 5.2
		* Math.log(halsteadMetric.getHalsteadVolume()) - 0.23
		* mcCabeMetric.getCyclomaticNumber() - 16.2
		* Math.log(sloc.getPhyLOC() * 100.0 / 171.0);
	double MIcw = 50 * Math.sin(Math.sqrt(2.4 * sloc.getComLOC()
		/ sloc.getPhyLOC()));
	result = new NormalizedMaintainabilityIndexResult(MIwoc, MIcw);
	if (getMonitor() != null) {
	    getMonitor().finished(this);
	}
    }

    public void print() {
	System.out.println("NMIwoc = " + result.getNMIwoc());
	System.out.println("NMIcw = " + result.getNMIcw());
	System.out.println("NMI = " + result.getNMI());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIWoc()
     */
    public double getMIWoc() {
	return result.getNMIwoc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIcw()
     */
    public double getMIcw() {
	return result.getNMIcw();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.MaintainabilityIndex#getMI()
     */
    public double getMI() {
	return result.getNMI();
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public SourceCodeQuality getQuality() {
	return NormalizedMaintainabilityQuality.get(getCodeRange().getType(),
		result);
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public String getName() {
	return NAME;
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public String getDescription() {
	return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     * 
     * @return
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
