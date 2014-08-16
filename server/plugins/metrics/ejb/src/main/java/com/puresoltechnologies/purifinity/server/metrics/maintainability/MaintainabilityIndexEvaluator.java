package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricFileResults;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;

@Stateless
@Remote(Evaluator.class)
public class MaintainabilityIndexEvaluator extends AbstractMetricEvaluator {

	public static final String ID = MaintainabilityIndexEvaluator.class
			.getName();
	public static final String NAME = "Maintainability Index";
	public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
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
	public static final Set<String> DEPENDENCIES = new HashSet<>();
	static {
		DEPENDENCIES.add(SLOCMetricCalculator.ID);
		DEPENDENCIES.add(McCabeMetric.ID);
		DEPENDENCIES.add(HalsteadMetric.ID);
	}

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	public MaintainabilityIndexEvaluator() {
		super(ID, NAME, DESCRIPTION);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	protected FileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			EvaluationStoreException {
		AnalysisInformation analyzedFile = analysis.getAnalysisInformation();
		HashId hashId = analyzedFile.getHashId();
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();

		MaintainabilityIndexFileResults results = new MaintainabilityIndexFileResults(
				MaintainabilityIndexEvaluator.ID, hashId, sourceCodeLocation,
				new Date());

		EvaluatorStore evaluatorStore = getEvaluatorStore();
		GenericFileMetrics slocFileResults = evaluatorStore.readFileResults(
				hashId, SLOCMetricCalculator.ID);
		GenericFileMetrics mcCabeFileResults = evaluatorStore.readFileResults(
				hashId, McCabeMetric.ID);
		GenericFileMetrics halsteadFileResults = evaluatorStore
				.readFileResults(hashId, HalsteadMetric.ID);

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			GenericCodeRangeMetrics slocCodeRangeResult = findFileResult(
					slocFileResults, codeRange);
			GenericCodeRangeMetrics mcCabeCodeRangeResult = findFileResult(
					mcCabeFileResults, codeRange);
			GenericCodeRangeMetrics halsteadCodeRangeResult = findFileResult(
					halsteadFileResults, codeRange);

			int phyLOC = slocCodeRangeResult.getValue(
					SLOCEvaluatorParameter.PHY_LOC).getValue();
			int comLOC = slocCodeRangeResult.getValue(
					SLOCEvaluatorParameter.COM_LOC).getValue();
			int vG = mcCabeCodeRangeResult.getValue(
					McCabeMetricEvaluatorParameter.VG).getValue();
			double hv = halsteadCodeRangeResult.getValue(
					HalsteadMetricEvaluatorParameter.HALSTEAD_VOLUMNE)
					.getValue();
			double MIwoc = 171.0 - 5.2 * Math.log(hv) - 0.23 * vG - 16.2
					* Math.log(phyLOC * 100.0 / 171.0);
			double MIcw = 50 * Math.sin(Math.sqrt(2.4 * comLOC / phyLOC));
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

	private GenericCodeRangeMetrics findFileResult(
			GenericFileMetrics slocFileResults, CodeRange codeRange) {
		if (slocFileResults != null) {
			for (GenericCodeRangeMetrics t : slocFileResults.getValues()) {
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
	protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		EvaluatorStore evaluatorStore = getEvaluatorStore();
		QualityLevel qualityLevel = null;
		for (AnalysisFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				GenericFileMetrics results = evaluatorStore.readFileResults(
						child.getHashId(), getInformation().getId());
				if (results != null) {
					for (GenericCodeRangeMetrics result : results.getValues()) {
						qualityLevel = QualityLevel.combine(
								qualityLevel,
								new QualityLevel(result.getValue(
										SourceCodeQualityParameter
												.getInstance()).getValue()));
					}
				}
			} else {
				GenericDirectoryMetrics results = evaluatorStore
						.readDirectoryResults(child.getHashId(),
								getInformation().getId());
				if (results != null) {
					qualityLevel = QualityLevel.combine(qualityLevel,
							results.getQualityLevel());
				}
			}
		}
		MaintainabilityIndexDirectoryResults finalResults = new MaintainabilityIndexDirectoryResults(
				MaintainabilityIndexEvaluator.ID, directory.getHashId(),
				new Date());
		finalResults.addQualityLevel(qualityLevel);
		return finalResults;
	}

	@Override
	protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		return processDirectory(analysisRun, analysisRun.getFileTree());
	}
}
