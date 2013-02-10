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

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.quality.QualityCharacteristic;
import com.puresol.coding.analysis.api.quality.SourceCodeQuality;
import com.puresol.coding.analysis.impl.evaluation.CodeRangeEvaluator;
import com.puresol.coding.analysis.impl.evaluation.Result;
import com.puresol.coding.lang.commons.ProgrammingLanguage;
import com.puresol.coding.metrics.halstead.HalsteadMetric;
import com.puresol.coding.metrics.mccabe.McCabeMetric;
import com.puresol.coding.metrics.sloc.SLOCMetricCalculator;
import com.puresol.coding.metrics.sloc.SLOCResult;

public class NormalizedMaintainabilityIndex extends CodeRangeEvaluator {

	private final AnalysisRun analysisRun;
	private final CodeRange codeRange;
	private final SLOCMetricCalculator slocMetric;
	private final McCabeMetric mcCabeMetric;
	private final HalsteadMetric halsteadMetric;
	private NormalizedMaintainabilityIndexResult result;

	public NormalizedMaintainabilityIndex(AnalysisRun analysisRun,
			ProgrammingLanguage language, CodeRange codeRange) {
		super(NormalizedMaintainabilityIndexEvaluator.NAME);
		this.analysisRun = analysisRun;
		this.codeRange = codeRange;
		slocMetric = new SLOCMetricCalculator(analysisRun, language,
				getCodeRange());
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
		monitor.beginTask(NormalizedMaintainabilityIndexEvaluator.NAME, 4);

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
		result = new NormalizedMaintainabilityIndexResult(MIwoc, MIcw);
		monitor.done();
		return Status.OK_STATUS;
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
	public String getDescription() {
		return NormalizedMaintainabilityIndexEvaluator.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	public List<Result> getResults() {
		return result.getResults();
	}

}
