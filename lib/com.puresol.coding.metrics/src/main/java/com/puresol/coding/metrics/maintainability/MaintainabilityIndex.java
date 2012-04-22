/***************************************************************************
 *
 *   MaintainabilityIndex.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.evaluation.api.Result;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.metrics.mccabe.McCabeMetric;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.metrics.sloc.SLOCResult;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class MaintainabilityIndex extends CodeRangeEvaluator {

    private static final long serialVersionUID = 2789695185933616684L;

    public static final String NAME = "Maintainability Index";
    public static final String DESCRIPTION = "Maintainability Index calculation.";
    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new ArrayList<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.CHANGEABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.TESTABILITY);
    }

    private final AnalysisRun analysisRun;
    private final CodeRange codeRange;
    private final SLOCMetric slocMetric;
    private final McCabeMetric mcCabeMetric;
    private final HalsteadMetric halsteadMetric;
    private MaintainabilityIndexResult result;

    public MaintainabilityIndex(AnalysisRun analysisRun,
	    ProgrammingLanguage language, CodeRange codeRange) {
	super(NAME);
	this.analysisRun = analysisRun;
	this.codeRange = codeRange;
	slocMetric = new SLOCMetric(analysisRun, language, getCodeRange());
	mcCabeMetric = new McCabeMetric(analysisRun, language, getCodeRange());
	halsteadMetric = new HalsteadMetric(analysisRun, language,
		getCodeRange());
    }

    @Override
    public AnalysisRun getAnalysisRun() {
	return analysisRun;
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
    public IStatus run(IProgressMonitor monitor) {
	monitor.beginTask(NAME, 4);

	checkInput();

	slocMetric.schedule();
	monitor.worked(1);
	mcCabeMetric.schedule();
	monitor.worked(2);
	halsteadMetric.schedule();
	monitor.worked(3);

	SLOCResult sloc = slocMetric.getSLOCResult();
	double MIwoc = 171.0 - 5.2
		* Math.log(halsteadMetric.getHalsteadVolume()) - 0.23
		* mcCabeMetric.getCyclomaticNumber() - 16.2
		* Math.log(sloc.getPhyLOC() * 100.0 / 171.0);
	double MIcw = 50 * Math.sin(Math.sqrt(2.4 * sloc.getComLOC()
		/ sloc.getPhyLOC()));
	result = new MaintainabilityIndexResult(MIwoc, MIcw);
	monitor.done();
	return Status.OK_STATUS;
    }

    public void print() {
	System.out.println("MIwoc = " + result.getMIwoc());
	System.out.println("MIcw = " + result.getMIcw());
	System.out.println("MI = " + result.getMI());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIWoc()
     */
    public double getMIWoc() {
	return result.getMIwoc();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.MaintainabilityIndex#getMIcw()
     */
    public double getMIcw() {
	return result.getMIcw();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.puresol.coding.analysis.MaintainabilityIndex#getMI()
     */
    public double getMI() {
	return result.getMI();
    }

    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public SourceCodeQuality getQuality() {
	return MaintainabilityQuality.get(getCodeRange().getType(), result);
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
