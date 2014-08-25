package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;

/**
 * This evaluator calculates the nesting depth of the source code. A too deep
 * nesting leads into a hard understandable code and a maintainability
 * nightmare.
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
@Remote(Evaluator.class)
public class CodeDepthMetricEvaluator extends AbstractMetricEvaluator {

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	@EJB(lookup = AnalyzerServiceManagerRemote.JNDI_NAME)
	private AnalyzerServiceManagerRemote analyzerServiceManager;

	public CodeDepthMetricEvaluator() {
		super(CodeDepthMetric.ID, CodeDepthMetric.NAME,
				CodeDepthMetric.DESCRIPTION);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return CodeDepthMetricEvaluatorParameter.ALL;
	}

	@Override
	protected FileMetrics processFile(AnalysisRun analysisRun,
			CodeAnalysis analysis) throws InterruptedException,
			UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
		AnalyzerServiceInformation analyzerServiceInformation = analyzerServiceManager
				.findByName(analysis.getLanguageName(),
						analysis.getLanguageVersion());
		ProgrammingLanguage language = analyzerServiceManager
				.createInstance(analyzerServiceInformation.getJndiName());

		HashId hashId = analysis.getAnalysisInformation().getHashId();
		SourceCodeLocation sourceCodeLocation = analysisRun
				.findTreeNode(hashId).getSourceCodeLocation();
		CodeDepthFileResults results = new CodeDepthFileResults(
				CodeDepthMetric.ID, hashId, sourceCodeLocation, new Date());
		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
			CodeDepthMetric metric = new CodeDepthMetric(analysisRun, language,
					codeRange);
			execute(metric);
			SourceCodeQuality quality = metric.getQuality();
			results.add(new CodeDepthResult(sourceCodeLocation, codeRange
					.getType(), codeRange.getCanonicalName(), metric
					.getMaxDepth(), quality));
		}
		return results;
	}

	@Override
	public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
	}

	@Override
	protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
			AnalysisFileTree directory) throws InterruptedException,
			EvaluationStoreException {
		CodeDepthDirectoryResults directoryResults = new CodeDepthDirectoryResults(
				CodeDepthMetric.ID, directory.getHashId(), new Date(),
				new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
				directory.getName());
		int maxDepth = 0;
		EvaluatorStore evaluatorStore = getEvaluatorStore();
		for (AnalysisFileTree child : directory.getChildren()) {
			QualityLevel childLevel;
			if (child.isFile()) {
				GenericFileMetrics fileResults = evaluatorStore
						.readFileResults(child.getHashId(), CodeDepthMetric.ID);
				if (fileResults == null) {
					continue;
				}
				childLevel = fileResults.getQualityLevel();
				for (GenericCodeRangeMetrics result : fileResults.getValues()) {
					maxDepth = Math
							.max(maxDepth,
									result.getValue(
											CodeDepthMetricEvaluatorParameter.MAX_DEPTH)
											.getValue());
				}
			} else {
				GenericDirectoryMetrics childDirectoryResults = evaluatorStore
						.readDirectoryResults(child.getHashId(),
								CodeDepthMetric.ID);
				if (childDirectoryResults == null) {
					continue;
				}
				childLevel = childDirectoryResults.getQualityLevel();
				maxDepth = Math
						.max(maxDepth,
								(Integer) childDirectoryResults
										.getValues()
										.get(CodeDepthMetricEvaluatorParameter.MAX_DEPTH
												.getName()).getValue());
			}
			if (childLevel != null) {
				directoryResults.addQualityLevel(childLevel);
				directoryResults.setMaxDepth(maxDepth);
			}
		}
		return directoryResults;
	}

	@Override
	protected DirectoryMetrics processProject(AnalysisRun analysisRun,
			boolean enableReevaluation) throws InterruptedException,
			EvaluationStoreException {
		AnalysisFileTree directory = analysisRun.getFileTree();
		return processDirectory(analysisRun, directory);
	}
}
