package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.evaluation.AbstractEvaluator;
import com.puresol.coding.analysis.impl.evaluation.EvaluatorStore;
import com.puresol.coding.analysis.impl.quality.QualityCharacteristic;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetricFileResult;
import com.puresol.coding.metrics.halstead.HalsteadMetricFileResults;
import com.puresol.coding.metrics.halstead.HalsteadResult;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricFileResult;
import com.puresol.coding.metrics.mccabe.McCabeMetricFileResults;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.metrics.sloc.SLOCFileResult;
import com.puresol.coding.metrics.sloc.SLOCFileResults;
import com.puresol.coding.metrics.sloc.SLOCResult;
import com.puresol.utils.HashId;

public class MaintainabilityIndexEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

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

    private final EvaluatorStore store;
    private final EvaluatorStore slocStore;
    private final EvaluatorStore mcCabeStore;
    private final EvaluatorStore halsteadStore;

    public MaintainabilityIndexEvaluator(AnalysisRun analysisRun) {
	super(NAME, DESCRIPTION, analysisRun);
	store = getEvaluatorStore();
	slocStore = AbstractEvaluator.createEvaluatorStore(SLOCEvaluator.class);
	mcCabeStore = AbstractEvaluator
		.createEvaluatorStore(McCabeMetricEvaluator.class);
	halsteadStore = AbstractEvaluator
		.createEvaluatorStore(HalsteadMetricEvaluator.class);
    }

    @Override
    protected void processFile(CodeAnalysis analysis)
	    throws InterruptedException {
	MaintainabilityIndexFileResults results = new MaintainabilityIndexFileResults();

	AnalyzedCode analyzedFile = analysis.getAnalyzedFile();
	HashId hashId = analyzedFile.getHashId();
	SLOCFileResults slocFileResults = (SLOCFileResults) slocStore
		.readFileResults(hashId);
	McCabeMetricFileResults mcCabeFileResults = (McCabeMetricFileResults) mcCabeStore
		.readFileResults(hashId);
	HalsteadMetricFileResults halsteadFileResults = (HalsteadMetricFileResults) halsteadStore
		.readFileResults(hashId);

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCFileResult slocCodeRangeResult = findFileResult(
		    slocFileResults, codeRange);
	    McCabeMetricFileResult mcCabeCodeRangeResult = findFileResult(
		    mcCabeFileResults, codeRange);
	    HalsteadMetricFileResult halsteadCodeRangeResult = findFileResult(
		    halsteadFileResults, codeRange);

	    SLOCResult sloc = slocCodeRangeResult.getSLOCResult();
	    HalsteadResult halsteadResult = halsteadCodeRangeResult
		    .getHalsteadResult();
	    double MIwoc = 171.0 - 5.2
		    * Math.log(halsteadResult.getHalsteadVolume()) - 0.23
		    * mcCabeCodeRangeResult.getCyclomaticComplexity() - 16.2
		    * Math.log(sloc.getPhyLOC() * 100.0 / 171.0);
	    double MIcw = 50 * Math.sin(Math.sqrt(2.4 * sloc.getComLOC()
		    / sloc.getPhyLOC()));
	    MaintainabilityIndexResult result = new MaintainabilityIndexResult(
		    MIwoc, MIcw);
	    results.add(new MaintainabilityIndexFileResult(analyzedFile
		    .getLocation(), codeRange.getType(), codeRange.getName(),
		    result, MaintainabilityQuality.get(codeRange.getType(),
			    result)));
	}
	store.storeFileResults(hashId, results);
    }

    private McCabeMetricFileResult findFileResult(
	    McCabeMetricFileResults mcCabeFileResults, CodeRange codeRange) {
	for (McCabeMetricFileResult t : mcCabeFileResults) {
	    if ((t.getCodeRangeType() == codeRange.getType())
		    && (t.getCodeRangeName().equals(codeRange.getName()))) {
		return t;
	    }
	}
	return null;
    }

    private HalsteadMetricFileResult findFileResult(
	    HalsteadMetricFileResults halsteadFileResults, CodeRange codeRange) {
	for (HalsteadMetricFileResult t : halsteadFileResults) {
	    if ((t.getCodeRangeType() == codeRange.getType())
		    && (t.getCodeRangeName().equals(codeRange.getName()))) {
		return t;
	    }
	}
	return null;
    }

    private SLOCFileResult findFileResult(SLOCFileResults slocFileResults,
	    CodeRange codeRange) {
	for (SLOCFileResult t : slocFileResults) {
	    if ((t.getCodeRangeType() == codeRange.getType())
		    && (t.getCodeRangeName().equals(codeRange.getName()))) {
		return t;
	    }
	}
	return null;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	// intentionally left blank
    }

    @Override
    protected void processProject() throws InterruptedException {
	// intentionally left blank
    }
}
