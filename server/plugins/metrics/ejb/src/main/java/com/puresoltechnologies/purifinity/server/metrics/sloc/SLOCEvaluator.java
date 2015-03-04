package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;

/**
 * This evaluator evaluates the Source Lines Of Code metrics which counts the
 * lines in source files and distinguishes between physical lines (all lines),
 * productive lines (lines which contain compilable code), comment lines (lines
 * which contain at least a bit of comment) and blank lines which neither
 * contain comments nor productive code.
 * 
 * The different counts of line types need to have a certain ratio between each
 * other to represent a well balance amount of comments, blank lines and
 * productive lines.
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
@Remote(Evaluator.class)
public class SLOCEvaluator extends AbstractMetricEvaluator {

    @EJB(lookup = AnalyzerServiceManagerRemote.JNDI_NAME)
    private AnalyzerServiceManagerRemote analyzerServiceManager;

    public SLOCEvaluator() {
	super(SLOCMetricCalculator.ID, SLOCMetricCalculator.NAME,
		SLOCMetricCalculator.DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
	return SLOCMetricCalculator.PARAMETERS;
    }

    @Override
    public Set<Parameter<?>> getParameters() {
	return SLOCEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	AnalysisInformation analyzedFile = analysis.getAnalysisInformation();
	HashId hashId = analyzedFile.getHashId();
	EvaluatorStore evaluatorStore = getEvaluatorStore();
	if (evaluatorStore.hasFileResults(hashId, SLOCMetricCalculator.ID)) {
	    return null;
	}
	AnalyzerServiceInformation analyzerServiceInformation = analyzerServiceManager
		.findByName(analysis.getLanguageName(),
			analysis.getLanguageVersion());
	ProgrammingLanguage language = analyzerServiceManager
		.createInstance(analyzerServiceInformation.getJndiName());
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();
	GenericFileMetrics results = new GenericFileMetrics(
		SLOCMetricCalculator.ID, hashId, sourceCodeLocation,
		analysis.getStartTime(), SLOCEvaluatorParameter.ALL);
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCMetricCalculator metric = new SLOCMetricCalculator(analysisRun,
		    language, codeRange);
	    execute(metric);

	    Map<String, MetricValue<?>> values = new HashMap<>();
	    for (MetricValue<?> result : metric.getSLOCResult().getResults()) {
		values.put(result.getParameter().getName(), result);
	    }

	    results.addCodeRangeMetrics(new GenericCodeRangeMetrics(
		    sourceCodeLocation, codeRange.getType(), codeRange
			    .getCanonicalName(), SLOCEvaluatorParameter.ALL,
		    values));
	}
	return results;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	QualityLevel qualityLevel = null;
	SLOCResult metricResults = null;
	EvaluatorStore evaluatorStore = getEvaluatorStore();
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		if (evaluatorStore.hasFileResults(child.getHashId(),
			getInformation().getId())) {
		    GenericFileMetrics results = evaluatorStore
			    .readFileResults(child.getHashId(),
				    getInformation().getId());
		    for (GenericCodeRangeMetrics result : results.getValues()) {
			if (result.getCodeRangeType() == CodeRangeType.FILE) {
			    metricResults = combine(directory, metricResults,
				    result);
			    break;
			}
		    }
		    qualityLevel = QualityLevel.combine(qualityLevel,
			    results.getQualityLevel());
		}
	    } else {
		if (evaluatorStore.hasDirectoryResults(child.getHashId(),
			getInformation().getId())) {
		    GenericDirectoryMetrics results = evaluatorStore
			    .readDirectoryResults(child.getHashId(),
				    getInformation().getId());
		    metricResults = combine(directory, metricResults, results);
		    qualityLevel = QualityLevel.combine(qualityLevel,
			    results.getQualityLevel());
		}
	    }
	}
	if (metricResults == null) {
	    return null;
	}
	Map<String, MetricValue<?>> metrics = SLOCResult
		.toGenericMetrics(metricResults);
	GenericDirectoryMetrics finalResults = new GenericDirectoryMetrics(
		SLOCMetricCalculator.ID, directory.getHashId(), new Date(),
		SLOCEvaluatorParameter.ALL, metrics);
	finalResults.addQualityLevel(qualityLevel);
	return finalResults;
    }

    private SLOCResult combine(AnalysisFileTree directory, SLOCResult results,
	    GenericCodeRangeMetrics result) {
	if (result != null) {
	    if (results == null) {
		results = SLOCResult.valueOf(result);
	    } else {
		results = SLOCResult.combine(results,
			SLOCResult.valueOf(result));
	    }
	}
	return results;
    }

    private SLOCResult combine(AnalysisFileTree directory, SLOCResult results,
	    GenericDirectoryMetrics result) {
	if (result != null) {
	    if (results == null) {
		results = SLOCResult.valueOf(result);
	    } else {
		results = SLOCResult.combine(results,
			SLOCResult.valueOf(result));
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
