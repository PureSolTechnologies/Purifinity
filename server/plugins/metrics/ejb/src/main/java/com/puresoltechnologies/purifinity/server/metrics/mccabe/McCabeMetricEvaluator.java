package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.server.core.api.analysis.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;

@Stateless
@Remote(Evaluator.class)
public class McCabeMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

    @Inject
    private EvaluatorStoreService store;

    public McCabeMetricEvaluator() {
	super(McCabeMetric.ID, McCabeMetric.NAME, McCabeMetric.DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
	return configurationParameters;
    }

    @Override
    protected MetricFileResults processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	try (ProgrammingLanguages programmingLanguages = ProgrammingLanguages
		.createInstance()) {
	    McCabeMetricFileResults results = new McCabeMetricFileResults();
	    HashId hashId = analysis.getAnalysisInformation().getHashId();
	    ProgrammingLanguage language = programmingLanguages.findByName(
		    analysis.getLanguageName(), analysis.getLanguageVersion());
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
		    hashId).getSourceCodeLocation();
	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		McCabeMetric metric = new McCabeMetric(analysisRun, language,
			codeRange);
		execute(metric);
		results.add(new McCabeMetricResult(sourceCodeLocation,
			codeRange.getType(), codeRange.getCanonicalName(),
			metric.getCyclomaticNumber(), metric.getQuality()));
	    }
	    return results;
	}
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	QualityLevel qualityLevel = null;
	McCabeMetricResult metricResults = null;
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		if (store.hasFileResults(McCabeMetricFileResults.class,
			child.getHashId())) {
		    McCabeMetricFileResults results = store.readFileResults(
			    McCabeMetricFileResults.class, child.getHashId());
		    for (McCabeMetricResult result : results.getResults()) {
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
		if (store.hasDirectoryResults(
			McCabeMetricDirectoryResults.class, child.getHashId())) {
		    McCabeMetricDirectoryResults results = store
			    .readDirectoryResults(
				    McCabeMetricDirectoryResults.class,
				    child.getHashId());
		    metricResults = combine(directory, metricResults,
			    results.getResult());
		    qualityLevel = QualityLevel.combine(qualityLevel,
			    results.getQualityLevel());
		}
	    }
	}
	if (metricResults == null) {
	    return null;
	}
	McCabeMetricDirectoryResults finalResults = new McCabeMetricDirectoryResults(
		metricResults);
	finalResults.addQualityLevel(qualityLevel);
	return finalResults;
    }

    private McCabeMetricResult combine(AnalysisFileTree directory,
	    McCabeMetricResult results, McCabeMetricResult result) {
	if (result != null) {
	    if (results == null) {
		results = new McCabeMetricResult(
			new UnspecifiedSourceCodeLocation(),
			CodeRangeType.DIRECTORY, directory.getName(),
			result.getCyclomaticComplexity(), result.getQuality());
	    } else {
		results = McCabeMetricDirectoryResults.combine(results, result);
	    }
	}
	return results;
    }

    @Override
    protected MetricDirectoryResults processProject(AnalysisRun analysisRun)
	    throws InterruptedException, EvaluationStoreException {
	AnalysisFileTree directory = analysisRun.getFileTree();
	return processDirectory(analysisRun, directory);
    }

    @Override
    protected MetricFileResults readFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.readFileResults(McCabeMetricFileResults.class, hashId);
    }

    @Override
    protected boolean hasFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasFileResults(McCabeMetricFileResults.class, hashId);
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun,
	    CodeAnalysis fileAnalysis, AbstractEvaluator evaluator,
	    MetricFileResults fileResults) throws EvaluationStoreException {
	store.storeFileResults(analysisRun, fileAnalysis, evaluator,
		fileResults);
    }

    @Override
    protected void storeMetricsInBigTable(AnalysisRun analysisRun,
	    CodeAnalysis fileAnalysis, AbstractEvaluator evaluator,
	    MetricFileResults fileResults) {
	store.storeMetricsInBigTable(analysisRun, fileAnalysis, evaluator,
		fileResults);
    }

    @Override
    protected MetricDirectoryResults readDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.readDirectoryResults(McCabeMetricDirectoryResults.class,
		hashId);
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasDirectoryResults(McCabeMetricDirectoryResults.class,
		hashId);
    }

    @Override
    protected void storeDirectoryResults(AnalysisRun analysisRun,
	    AnalysisFileTree directoryNode, AbstractEvaluator evaluator,
	    MetricDirectoryResults directoryResults)
	    throws EvaluationStoreException {
	store.storeDirectoryResults(analysisRun, directoryNode, evaluator,
		directoryResults);
    }

    @Override
    protected void storeMetricsInBigTable(AnalysisRun analysisRun,
	    AnalysisFileTree directoryNode, AbstractEvaluator evaluator,
	    MetricDirectoryResults directoryResults) {
	store.storeMetricsInBigTable(analysisRun, directoryNode, evaluator,
		directoryResults);
    }

}
