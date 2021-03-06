package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.Date;
import java.util.List;

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
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetricsImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.CommonEvaluatorMetricsStore;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.db.McCabeMetricEvaluatorDAO;

@Stateless
@Remote(Evaluator.class)
public class McCabeMetricEvaluator extends AbstractMetricEvaluator {

    @Inject
    private McCabeMetricEvaluatorDAO mcCabeMetricEvaluatorDAO;

    public McCabeMetricEvaluator() {
	super(McCabeMetric.ID, McCabeMetric.NAME, McCabeMetric.PLUGIN_VERSION, McCabeMetric.DESCRIPTION);
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return McCabeMetric.PARAMETERS;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return McCabeMetricEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	AnalysisInformation analysisInformation = analysis.getAnalysisInformation();
	HashId hashId = analysisInformation.getHashId();
	AnalysisFileTree analysisFileNode = analysisRun.findTreeNode(hashId);
	SourceCodeLocation sourceCodeLocation = analysisFileNode.getSourceCodeLocation();
	McCabeMetricFileResults results = new McCabeMetricFileResults(McCabeMetric.ID, McCabeMetric.PLUGIN_VERSION,
		hashId, sourceCodeLocation, new Date());
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    McCabeMetric metric = new McCabeMetric(analysisRun, codeRange);
	    metric.run();
	    McCabeMetricResult mcCabeResult = new McCabeMetricResult(sourceCodeLocation, codeRange.getType(),
		    codeRange.getCanonicalName(), metric.getCyclomaticNumber());
	    results.add(mcCabeResult);
	    mcCabeMetricEvaluatorDAO.storeFileResults(hashId, sourceCodeLocation, codeRange, mcCabeResult);
	}
	return results;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected DirectoryMetricsImpl processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	CommonEvaluatorMetricsStore evaluatorStore = getMetricStore();
	McCabeMetricResult metricResults = null;
	for (AnalysisFileTree child : directory.getChildren()) {
	    HashId childHashId = child.getHashId();
	    if (child.isFile()) {
		if (evaluatorStore.hasFileResults(childHashId, getInformation().getId())) {
		    List<McCabeMetricResult> results = mcCabeMetricEvaluatorDAO.readFileResults(childHashId);
		    for (McCabeMetricResult result : results) {
			if (result.getCodeRangeType() == CodeRangeType.FILE) {
			    metricResults = combine(directory, metricResults, result);
			    break;
			}
		    }
		}
	    } else {
		if (evaluatorStore.hasDirectoryResults(childHashId, getInformation().getId())) {
		    McCabeMetricResult results = mcCabeMetricEvaluatorDAO.readDirectoryResults(childHashId);
		    metricResults = combine(directory, metricResults, results);
		}
	    }
	}
	if (metricResults == null) {
	    return null;
	}
	DirectoryMetricsImpl finalResults = new DirectoryMetricsImpl(McCabeMetric.ID, McCabeMetric.PLUGIN_VERSION,
		directory.getHashId(), new Date(), McCabeMetricEvaluatorParameter.ALL, metricResults.getValues());
	mcCabeMetricEvaluatorDAO.storeDirectoryResults(directory.getHashId(), metricResults);
	return finalResults;
    }

    private McCabeMetricResult combine(AnalysisFileTree directory, McCabeMetricResult results,
	    McCabeMetricResult result) {
	if (result != null) {
	    if (results == null) {
		results = new McCabeMetricResult(new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
			directory.getName(), result.getCyclomaticComplexity());
	    } else {
		results = McCabeMetricDirectoryResults.combine(results, result);
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
