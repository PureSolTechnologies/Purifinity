package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.db.HalsteadMetricsEvaluatorDAO;

@Stateless
@Remote(Evaluator.class)
public class HalsteadMetricEvaluator extends AbstractMetricEvaluator {

	@EJB(lookup = AnalyzerServiceManagerRemote.JNDI_NAME)
	private AnalyzerServiceManagerRemote analyzerServiceManager;

	@Inject
	private HalsteadMetricsEvaluatorDAO halsteadMetricEvaluatorDAO;

	public HalsteadMetricEvaluator() {
		super(HalsteadMetric.ID, HalsteadMetric.NAME,
				HalsteadMetric.PLUGIN_VERSION, HalsteadMetric.DESCRIPTION);
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return HalsteadMetric.PARAMETERS;
	}

	@Override
	public Set<MetricParameter<?>> getParameters() {
		return HalsteadMetricEvaluatorParameter.ALL;
	}

	@Override
	protected GenericFileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		AnalysisInformation analysisInformation = analysis
				.getAnalysisInformation();
		AnalyzerServiceInformation analyzerServiceInformation = analyzerServiceManager
				.findByName(analysisInformation.getLanguageName(),
						analysisInformation.getLanguageVersion());
		ProgrammingLanguage language = analyzerServiceManager
				.createProxy(analyzerServiceInformation.getJndiName());

		HashId hashId = analysisInformation.getHashId();
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();
		GenericFileMetrics results = new GenericFileMetrics(HalsteadMetric.ID,
				HalsteadMetric.PLUGIN_VERSION, hashId, sourceCodeLocation,
				new Date(), HalsteadMetricEvaluatorParameter.ALL);
		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			HalsteadMetric metric = new HalsteadMetric(analysisRun, language,
					codeRange);
			metric.run();
			HalsteadResult halsteadResult = metric.getHalsteadResults();
			halsteadMetricEvaluatorDAO.storeFileResults(
					hashId,
					sourceCodeLocation,
					codeRange,
					new HalsteadMetricResult(sourceCodeLocation, codeRange
							.getType(), codeRange.getCanonicalName(),
							halsteadResult));
			results.addCodeRangeMetrics(new GenericCodeRangeMetrics(
					sourceCodeLocation, codeRange.getType(), codeRange
							.getCanonicalName(),
					HalsteadMetricEvaluatorParameter.ALL, halsteadResult
							.getResults()));
		}
		return results;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		HalsteadMetricResult metricResults = null;
		for (AnalysisFileTree child : directory.getChildren()) {
			if (child.isFile()) {
				List<HalsteadMetricResult> results = halsteadMetricEvaluatorDAO
						.readFileResults(child.getHashId());
				for (HalsteadMetricResult result : results) {
					if (result.getCodeRangeType() == CodeRangeType.FILE) {
						metricResults = combine(directory, metricResults,
								result.getHalsteadResult());
						break;
					}
				}
			} else {
				HalsteadMetricResult directoryResults = halsteadMetricEvaluatorDAO
						.readDirectoryResults(child.getHashId());
				if (directoryResults != null) {
					metricResults = combine(directory, metricResults,
							directoryResults.getHalsteadResult());
				}
			}
		}
		if (metricResults == null) {
			return null;
		}

		halsteadMetricEvaluatorDAO.storeDirectoryResults(directory.getHashId(),
				metricResults);

		HalsteadMetricDirectoryResults finalResults = new HalsteadMetricDirectoryResults(
				HalsteadMetric.ID, HalsteadMetric.PLUGIN_VERSION,
				directory.getHashId(), new Date(), metricResults);
		return finalResults;
	}

	private HalsteadMetricResult combine(AnalysisFileTree node,
			HalsteadMetricResult results, HalsteadResult result) {
		if (result != null) {
			CodeRangeType codeRangeType = node.isFile() ? CodeRangeType.FILE
					: CodeRangeType.DIRECTORY;
			if (results == null) {
				results = new HalsteadMetricResult(
						new UnspecifiedSourceCodeLocation(),
						CodeRangeType.DIRECTORY, node.getName(), result);
			} else {
				results = HalsteadMetricResult.combine(results,
						new HalsteadMetricResult(node.getSourceCodeLocation(),
								codeRangeType, node.getName(), result));
			}
		}
		return results;
	}

	@Override
	protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		AnalysisFileTree directory = analysisRun.getFileTree();
		return processDirectory(analysisRun, directory);
	}
}
