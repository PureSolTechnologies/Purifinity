package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.util.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
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
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;

@Stateless
@Remote(Evaluator.class)
public class HalsteadMetricEvaluator extends AbstractMetricEvaluator {

    @EJB(lookup = AnalyzerServiceManagerRemote.JNDI_NAME)
    private AnalyzerServiceManagerRemote analyzerServiceManager;

    @Inject
    private HalsteadMetricEvaluatorStore halsteadMetricEvaluatorStore;

    public HalsteadMetricEvaluator() {
	super(HalsteadMetric.ID, HalsteadMetric.NAME,
		HalsteadMetric.DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
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
	AnalyzerServiceInformation analyzerServiceInformation = analyzerServiceManager
		.findByName(analysis.getLanguageName(),
			analysis.getLanguageVersion());
	ProgrammingLanguage language = analyzerServiceManager
		.createInstance(analyzerServiceInformation.getJndiName());

	HashId hashId = analysis.getAnalysisInformation().getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();
	GenericFileMetrics results = new GenericFileMetrics(HalsteadMetric.ID,
		hashId, sourceCodeLocation, new Date(),
		HalsteadMetricEvaluatorParameter.ALL);
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    HalsteadMetric metric = new HalsteadMetric(analysisRun, language,
		    codeRange);
	    execute(metric);
	    HalsteadResult halsteadResults = metric.getHalsteadResults();
	    halsteadMetricEvaluatorStore.storeCodeRangeResults(hashId,
		    codeRange, halsteadResults);
	    results.addCodeRangeMetrics(new GenericCodeRangeMetrics(
		    sourceCodeLocation, codeRange.getType(), codeRange
			    .getCanonicalName(),
		    HalsteadMetricEvaluatorParameter.ALL, halsteadResults
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
	QualityLevel qualityLevel = null;
	HalsteadMetricResult metricResults = null;
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		HalsteadResult fileResults = halsteadMetricEvaluatorStore
			.readCodeRangeResults(child.getHashId(),
				CodeRangeType.FILE, child.getName());
		if (fileResults != null) {
		    metricResults = combine(directory, metricResults,
			    fileResults);
		    qualityLevel = QualityLevel.combine(
			    qualityLevel,
			    new QualityLevel(HalsteadQuality.get(
				    CodeRangeType.FILE, fileResults)));
		}
	    } else {
		HalsteadResult directoryResults = halsteadMetricEvaluatorStore
			.readDirectoryResults(child.getHashId());
		if (directoryResults != null) {
		    metricResults = combine(directory, metricResults,
			    directoryResults);
		    qualityLevel = QualityLevel
			    .combine(
				    qualityLevel,
				    new QualityLevel(HalsteadQuality.get(
					    CodeRangeType.DIRECTORY,
					    directoryResults)));
		}
	    }
	}
	if (metricResults == null) {
	    return null;
	}
	HalsteadMetricDirectoryResults finalResults = new HalsteadMetricDirectoryResults(
		HalsteadMetric.ID, directory.getHashId(), new Date(),
		metricResults);
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
			CodeRangeType.DIRECTORY, node.getName(), result,
			HalsteadQuality.get(codeRangeType, result));
	    } else {
		results = HalsteadMetricResult.combine(results,
			new HalsteadMetricResult(node.getSourceCodeLocation(),
				codeRangeType, node.getName(), result,
				HalsteadQuality.get(codeRangeType, result)));
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
