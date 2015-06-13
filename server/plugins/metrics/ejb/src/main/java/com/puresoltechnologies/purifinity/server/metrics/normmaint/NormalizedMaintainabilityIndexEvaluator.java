package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexResult;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.db.MaintainabilityIndexEvaluatorDAO;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class NormalizedMaintainabilityIndexEvaluator extends
		AbstractMetricEvaluator {

	public static final String ID = NormalizedMaintainabilityIndexEvaluator.class
			.getName();
	public static final String NAME = "Normalized Maintainability Index";
	public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
	public static final String DESCRIPTION = "Normalized Maintainability Index calculation.";
	public static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();

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
		DEPENDENCIES.add(MaintainabilityIndexEvaluator.ID);
	}

	@Inject
	private Logger logger;

	@Inject
	private MaintainabilityIndexEvaluatorDAO maintainabilityIndexEvaluatorDAO;

	public NormalizedMaintainabilityIndexEvaluator() {
		super(ID, NAME, PLUGIN_VERSION, DESCRIPTION);
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return PARAMETERS;
	}

	@Override
	public Set<MetricParameter<?>> getParameters() {
		return NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
	}

	@Override
	protected FileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			EvaluationStoreException {
		AnalysisInformation analysisInformation = analysis
				.getAnalysisInformation();
		HashId hashId = analysisInformation.getHashId();
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();
		NormalizedMaintainabilityIndexFileResults results = new NormalizedMaintainabilityIndexFileResults(
				NormalizedMaintainabilityIndexEvaluator.ID,
				NormalizedMaintainabilityIndexEvaluator.PLUGIN_VERSION, hashId,
				sourceCodeLocation, new Date());

		List<MaintainabilityIndexFileResult> maintainabilityFileResults = maintainabilityIndexEvaluatorDAO
				.readFileResults(hashId);
		if (maintainabilityFileResults == null) {
			logger.warn("No Maintainability Index result available for '"
					+ hashId + "', yet.");
			return results;
		}

		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			MaintainabilityIndexFileResult maintainabilityIndexFileResult = findFileResult(
					maintainabilityFileResults, codeRange);

			MaintainabilityIndexResult maintainabilityIndexResult = maintainabilityIndexFileResult
					.getMaintainabilityIndexResult();
			double miWoc = maintainabilityIndexResult.getMIwoc();
			double miCw = maintainabilityIndexResult.getMIcw();
			NormalizedMaintainabilityIndexResult result = new NormalizedMaintainabilityIndexResult(
					miWoc, miCw);

			results.add(new NormalizedMaintainabilityIndexFileResult(
					sourceCodeLocation, codeRange.getType(), codeRange
							.getCanonicalName(), result,
					NormalizedMaintainabilityQuality.get(codeRange.getType(),
							result)));
		}
		return results;
	}

	private MaintainabilityIndexFileResult findFileResult(
			List<MaintainabilityIndexFileResult> maintainabilityFileResults,
			CodeRange codeRange) {
		for (MaintainabilityIndexFileResult t : maintainabilityFileResults) {
			if ((t.getCodeRangeType() == codeRange.getType())
					&& (t.getCodeRangeName().equals(codeRange.getSimpleName()))) {
				return t;
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
		NormalizedMaintainabilityIndexDirectoryResults finalResults = new NormalizedMaintainabilityIndexDirectoryResults(
				NormalizedMaintainabilityIndexEvaluator.ID,
				NormalizedMaintainabilityIndexEvaluator.PLUGIN_VERSION,
				directory.getHashId(), new Date());
		return finalResults;
	}

	@Override
	protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		return processDirectory(analysisRun, analysisRun.getFileTree());
	}

	@Override
	public void setConfigurationParameter(ConfigurationParameter<?> parameter,
			Object value) {
		// Intentionally left empty.
		throw new IllegalArgumentException("Parameter '" + parameter
				+ "' is unknown.");
	}

	@Override
	public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
		// Intentionally left empty.
		throw new IllegalArgumentException("Parameter '" + parameter
				+ "' is unknown.");
	}
}
