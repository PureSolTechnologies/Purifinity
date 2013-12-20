package com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.AbstractEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetricEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetricResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.sloc.SLOCResult;

public class MaintainabilityIndexEvaluator extends AbstractEvaluator {

	private static final long serialVersionUID = -5093217611195212999L;

	public static final String NAME = "Maintainability Index";
	public static final String DESCRIPTION = "Maintainability Index calculation.";
	public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
	static {
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.ANALYSABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.CHANGEABILITY);
		EVALUATED_QUALITY_CHARACTERISTICS
				.add(QualityCharacteristic.TESTABILITY);
	}

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private final EvaluatorStore store;
	private final EvaluatorStore slocStore;
	private final EvaluatorStore mcCabeStore;
	private final EvaluatorStore halsteadStore;

	public MaintainabilityIndexEvaluator(AnalysisRun analysisRun,
			HashIdFileTree path) {
		super(NAME, DESCRIPTION, analysisRun, path);
		store = createEvaluatorStore();
		EvaluatorStoreFactory factory = EvaluatorStoreFactory.getFactory();
		slocStore = factory.createInstance(SLOCEvaluator.class);
		mcCabeStore = factory.createInstance(McCabeMetricEvaluator.class);
		halsteadStore = factory.createInstance(HalsteadMetricEvaluator.class);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
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
					.getCanonicalName(), result, MaintainabilityQuality.get(
					codeRange.getType(), result)));
		}
		store.storeFileResults(hashId, results);
	}

	private McCabeMetricResult findFileResult(
			McCabeMetricFileResults mcCabeFileResults, CodeRange codeRange) {
		if (mcCabeFileResults != null) {
			for (McCabeMetricResult t : mcCabeFileResults.getResults()) {
				if ((t.getCodeRangeType() == codeRange.getType())
						&& (t.getCodeRangeName().equals(codeRange
								.getCanonicalName()))) {
					return t;
				}
			}
		}
		return null;
	}

	private HalsteadMetricResult findFileResult(
			HalsteadMetricFileResults halsteadFileResults, CodeRange codeRange) {
		for (HalsteadMetricResult t : halsteadFileResults.getResults()) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange
							.getCanonicalName()))) {
				return t;
			}
		}
		return null;
	}

	private SLOCResult findFileResult(SLOCFileResults slocFileResults,
			CodeRange codeRange) {
		if (slocFileResults != null) {
			for (SLOCResult t : slocFileResults.getResults()) {
				if ((t.getCodeRangeType() == codeRange.getType())
						&& (t.getCodeRangeName().equals(codeRange
								.getCanonicalName()))) {
					return t;
				}
			}
		}
		return null;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected void processDirectory(HashIdFileTree directory)
			throws InterruptedException {
		MaintainabilityIndexDirectoryResults finalResults = createDirectoryResults(directory);
		if (finalResults != null) {
			store.storeDirectoryResults(directory.getHashId(), finalResults);
		}
	}

	private MaintainabilityIndexDirectoryResults createDirectoryResults(
			HashIdFileTree directory) {
		QualityLevel qualityLevel = null;
		for (HashIdFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				MaintainabilityIndexFileResults results = (MaintainabilityIndexFileResults) store
						.readFileResults(child.getHashId());
				if (results != null) {
					for (MaintainabilityIndexFileResult result : results
							.getResults()) {
						qualityLevel = QualityLevel.combine(qualityLevel,
								new QualityLevel(result.getQuality()));
					}
				}
			} else {
				MaintainabilityIndexDirectoryResults results = (MaintainabilityIndexDirectoryResults) store
						.readDirectoryResults(child.getHashId());
				if (results != null) {
					qualityLevel = QualityLevel.combine(qualityLevel,
							results.getQualityLevel());
				}
			}
		}
		MaintainabilityIndexDirectoryResults finalResults = new MaintainabilityIndexDirectoryResults(
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
		finalResults.addQualityLevel(qualityLevel);
		return finalResults;
	}

	@Override
	protected void processProject() throws InterruptedException {
		if (store.hasProjectResults(getAnalysisRun())) {
			return;
		}
		MaintainabilityIndexDirectoryResults finalResults = createDirectoryResults(getAnalysisRun()
				.getFileTree());
		if (finalResults != null) {
			store.storeProjectResults(getAnalysisRun(), finalResults);
		}
	}
}
