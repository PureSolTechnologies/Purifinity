package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.Date;
import java.util.List;
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
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.db.McCabeMetricEvaluatorDAO;

@Stateless
@Remote(Evaluator.class)
public class McCabeMetricEvaluator extends AbstractMetricEvaluator {

    @EJB(lookup = AnalyzerServiceManagerRemote.JNDI_NAME)
    private AnalyzerServiceManagerRemote analyzerServiceManager;

    @Inject
    private McCabeMetricEvaluatorDAO mcCabeMetricEvaluatorDAO;

    public McCabeMetricEvaluator() {
	super(McCabeMetric.ID, McCabeMetric.NAME, McCabeMetric.DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
	return McCabeMetric.PARAMETERS;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return McCabeMetricEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();
	McCabeMetricFileResults results = new McCabeMetricFileResults(
		McCabeMetric.ID, hashId, sourceCodeLocation, new Date());
	AnalyzerServiceInformation analyzerServiceInformation = analyzerServiceManager
		.findByName(analysis.getLanguageName(),
			analysis.getLanguageVersion());
	ProgrammingLanguage language = analyzerServiceManager
		.getInstance(analyzerServiceInformation.getJndiName());
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    McCabeMetric metric = new McCabeMetric(analysisRun, language,
		    codeRange);
	    metric.run();
	    McCabeMetricResult mcCabeResult = new McCabeMetricResult(
		    sourceCodeLocation, codeRange.getType(),
		    codeRange.getCanonicalName(), metric.getCyclomaticNumber());
	    results.add(mcCabeResult);
	    mcCabeMetricEvaluatorDAO.storeFileResults(hashId,
		    sourceCodeLocation, codeRange, mcCabeResult);
	}
	return results;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected GenericDirectoryMetrics processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	EvaluatorStore evaluatorStore = getEvaluatorStore();
	McCabeMetricResult metricResults = null;
	for (AnalysisFileTree child : directory.getChildren()) {
	    HashId childHashId = child.getHashId();
	    if (child.isFile()) {
		if (evaluatorStore.hasFileResults(childHashId, getInformation()
			.getId())) {
		    List<McCabeMetricResult> results = mcCabeMetricEvaluatorDAO
			    .readFileResults(childHashId);
		    for (McCabeMetricResult result : results) {
			if (result.getCodeRangeType() == CodeRangeType.FILE) {
			    metricResults = combine(directory, metricResults,
				    result);
			    break;
			}
		    }
		}
	    } else {
		if (evaluatorStore.hasDirectoryResults(childHashId,
			getInformation().getId())) {
		    McCabeMetricResult results = mcCabeMetricEvaluatorDAO
			    .readDirectoryResults(childHashId);
		    metricResults = combine(directory, metricResults, results);
		}
	    }
	}
	if (metricResults == null) {
	    return null;
	}
	GenericDirectoryMetrics finalResults = new GenericDirectoryMetrics(
		McCabeMetric.ID, directory.getHashId(), new Date(),
		McCabeMetricEvaluatorParameter.ALL, metricResults.getValues());
	mcCabeMetricEvaluatorDAO.storeDirectoryResults(directory.getHashId(),
		metricResults);
	return finalResults;
    }

    private McCabeMetricResult combine(AnalysisFileTree directory,
	    McCabeMetricResult results, McCabeMetricResult result) {
	if (result != null) {
	    if (results == null) {
		results = new McCabeMetricResult(
			new UnspecifiedSourceCodeLocation(),
			CodeRangeType.DIRECTORY, directory.getName(),
			result.getCyclomaticComplexity());
	    } else {
		results = McCabeMetricDirectoryResults.combine(results,
			result);
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
