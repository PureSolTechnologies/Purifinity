package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricFileResults;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluator;

@Stateless
@Remote(Evaluator.class)
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

	public MaintainabilityIndexEvaluator() {
		super(NAME, DESCRIPTION);
		store = getEvaluatorStore();
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
	protected MetricFileResults processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			EvaluationStoreException {
		MaintainabilityIndexFileResults results = new MaintainabilityIndexFileResults();

		AnalysisInformation analyzedFile = analysis.getAnalysisInformation();
		HashId hashId = analyzedFile.getHashId();
		SLOCFileResults slocFileResults = (SLOCFileResults) slocStore
				.readFileResults(hashId);
		McCabeMetricFileResults mcCabeFileResults = (McCabeMetricFileResults) mcCabeStore
				.readFileResults(hashId);
		HalsteadMetricFileResults halsteadFileResults = (HalsteadMetricFileResults) halsteadStore
				.readFileResults(hashId);
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();

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
			results.add(new MaintainabilityIndexFileResult(sourceCodeLocation,
					codeRange.getType(), codeRange.getCanonicalName(), result,
					MaintainabilityQuality.get(codeRange.getType(), result)));
		}
		return results;
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
	protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		QualityLevel qualityLevel = null;
		for (AnalysisFileTree child : directory.getChildren()) {
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
	protected MetricDirectoryResults processProject(AnalysisRun analysisRun)
			throws InterruptedException, EvaluationStoreException {
		return processDirectory(analysisRun, analysisRun.getFileTree());
	}
}