package com.puresoltechnologies.purifinity.server.metrics.sloc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.CodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetricsImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetricsImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.db.SLOCMetricEvaluatorDAO;

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

    @Inject
    private Logger logger;

    @Inject
    private SLOCMetricEvaluatorDAO slocEvaluatorDAO;

    public SLOCEvaluator() {
	super(SLOCMetricCalculator.ID, SLOCMetricCalculator.NAME, SLOCMetricCalculator.PLUGIN_VERSION,
		SLOCMetricCalculator.DESCRIPTION);
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return SLOCMetricCalculator.PARAMETERS;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return SLOCEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	AnalysisInformation analysisInformation = analysis.getAnalysisInformation();
	HashId hashId = analysisInformation.getHashId();
	AnalysisFileTree analysisRunNode = analysisRun.findTreeNode(hashId);
	SourceCodeLocation sourceCodeLocation = analysisRunNode.getSourceCodeLocation();
	FileMetricsImpl results = new FileMetricsImpl(SLOCMetricCalculator.ID, SLOCMetricCalculator.PLUGIN_VERSION,
		hashId, sourceCodeLocation, analysis.getStartTime());
	logger.info("Process file '" + sourceCodeLocation.getHumanReadableLocationString() + "'...");
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCMetricCalculator metric = new SLOCMetricCalculator(analysisRun, codeRange);
	    metric.run();

	    Map<String, MetricValue<?>> values = new HashMap<>();
	    for (MetricValue<?> result : metric.getSLOCResult().getResults()) {
		values.put(result.getParameter().getName(), result);
	    }

	    results.addCodeRangeMetrics(new CodeRangeMetrics(sourceCodeLocation, codeRange.getType(),
		    codeRange.getCanonicalName(), SLOCEvaluatorParameter.ALL, values));

	    SLOCResult result = new SLOCResult(sourceCodeLocation, codeRange.getType(), codeRange.getCanonicalName(),
		    metric.getSLOCResult());
	    slocEvaluatorDAO.storeFileResults(hashId, sourceCodeLocation, codeRange, result);
	}
	logger.info("Finished file '" + sourceCodeLocation.getHumanReadableLocationString() + "'.");
	return results;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	HashId hashId = directory.getHashId();
	SLOCResult metricResults = null;
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		if (slocEvaluatorDAO.hasFileResults(child.getHashId())) {
		    List<SLOCResult> results = slocEvaluatorDAO.readFileResults(child.getHashId());
		    for (SLOCResult result : results) {
			if (result.getCodeRangeType() == CodeRangeType.FILE) {
			    metricResults = combine(directory, metricResults, result);
			    break;
			}
		    }
		}
	    } else {
		if (slocEvaluatorDAO.hasDirectoryResults(child.getHashId())) {
		    SLOCResult slocResult = slocEvaluatorDAO.readDirectoryResults(child.getHashId());
		    metricResults = combine(directory, metricResults, slocResult);
		}
	    }
	}
	if (metricResults == null) {
	    return null;
	}

	slocEvaluatorDAO.storeDirectoryResults(hashId, metricResults);

	Map<String, MetricValue<?>> metrics = SLOCResult.toGenericMetrics(metricResults);
	DirectoryMetricsImpl finalResults = new DirectoryMetricsImpl(SLOCMetricCalculator.ID,
		SLOCMetricCalculator.PLUGIN_VERSION, hashId, new Date(), SLOCEvaluatorParameter.ALL, metrics);
	return finalResults;
    }

    private SLOCResult combine(AnalysisFileTree directory, SLOCResult results, SLOCResult result) {
	if (result != null) {
	    if (results == null) {
		results = result;
	    } else {
		results = SLOCResult.combine(results, result);
	    }
	}
	return results;
    }

    @Override
    protected DirectoryMetrics processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	AnalysisFileTree directory = analysisRun.getFileTree();
	return processDirectory(analysisRun, directory);
    }

    @Override
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value) {
	// Intentionally left empty.
	throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	// Intentionally left empty.
	throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
    }
}
