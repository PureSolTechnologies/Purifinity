package com.puresol.coding.metrics.maintainability;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.QualityCharacteristic;
import com.puresol.coding.evaluation.impl.AbstractEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluator;
import com.puresol.coding.metrics.halstead.HalsteadMetricResult;
import com.puresol.coding.metrics.halstead.HalsteadMetricResults;
import com.puresol.coding.metrics.halstead.HalsteadResult;
import com.puresol.coding.metrics.mccabe.McCabeMetricEvaluator;
import com.puresol.coding.metrics.mccabe.McCabeMetricResult;
import com.puresol.coding.metrics.mccabe.McCabeMetricResults;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.metrics.sloc.SLOCResult;
import com.puresol.coding.metrics.sloc.SLOCResults;
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

	public MaintainabilityIndexEvaluator(AnalysisRun analysisRun,
			HashIdFileTree path) {
		super(NAME, DESCRIPTION, analysisRun, path);
		store = getEvaluatorStore();
		EvaluatorStoreFactory factory = EvaluatorStoreFactory.getFactory();
		slocStore = factory.createInstance(SLOCEvaluator.class);
		mcCabeStore = factory.createInstance(McCabeMetricEvaluator.class);
		halsteadStore = factory.createInstance(HalsteadMetricEvaluator.class);
	}

	@Override
	protected void processFile(CodeAnalysis analysis)
			throws InterruptedException {
		MaintainabilityIndexFileResults results = new MaintainabilityIndexFileResults();

		AnalyzedCode analyzedFile = analysis.getAnalyzedFile();
		HashId hashId = analyzedFile.getHashId();
		SLOCResults slocFileResults = (SLOCResults) slocStore
				.readFileResults(hashId);
		McCabeMetricResults mcCabeFileResults = (McCabeMetricResults) mcCabeStore
				.readFileResults(hashId);
		HalsteadMetricResults halsteadFileResults = (HalsteadMetricResults) halsteadStore
				.readFileResults(hashId);

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			SLOCResult slocCodeRangeResult = findFileResult(slocFileResults,
					codeRange);
			McCabeMetricResult mcCabeCodeRangeResult = findFileResult(
					mcCabeFileResults, codeRange);
			HalsteadMetricResult halsteadCodeRangeResult = findFileResult(
					halsteadFileResults, codeRange);

			SLOCMetric sloc = slocCodeRangeResult.getSLOCMetric();
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
					.getSourceLocation(), codeRange.getType(), codeRange
					.getName(), result, MaintainabilityQuality.get(
					codeRange.getType(), result)));
		}
		store.storeFileResults(hashId, results);
	}

	private McCabeMetricResult findFileResult(
			McCabeMetricResults mcCabeFileResults, CodeRange codeRange) {
		for (McCabeMetricResult t : mcCabeFileResults.getResults()) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange.getName()))) {
				return t;
			}
		}
		return null;
	}

	private HalsteadMetricResult findFileResult(
			HalsteadMetricResults halsteadFileResults, CodeRange codeRange) {
		for (HalsteadMetricResult t : halsteadFileResults.getResults()) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange.getName()))) {
				return t;
			}
		}
		return null;
	}

	private SLOCResult findFileResult(SLOCResults slocFileResults,
			CodeRange codeRange) {
		for (SLOCResult t : slocFileResults.getResults()) {
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
