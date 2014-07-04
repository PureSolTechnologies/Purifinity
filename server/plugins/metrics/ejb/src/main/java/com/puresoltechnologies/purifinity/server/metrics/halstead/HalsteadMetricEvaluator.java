package com.puresoltechnologies.purifinity.server.metrics.halstead;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

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
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;

@Stateless
@Remote(Evaluator.class)
public class HalsteadMetricEvaluator extends AbstractMetricEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

    public HalsteadMetricEvaluator() {
	super(HalsteadMetric.ID, HalsteadMetric.NAME,
		HalsteadMetric.DESCRIPTION);
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
	    ProgrammingLanguage language = programmingLanguages.findByName(
		    analysis.getLanguageName(), analysis.getLanguageVersion());

	    HalsteadMetricFileResults results = new HalsteadMetricFileResults();
	    HashId hashId = analysis.getAnalysisInformation().getHashId();
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
		    hashId).getSourceCodeLocation();
	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		HalsteadMetric metric = new HalsteadMetric(analysisRun,
			language, codeRange);
		execute(metric);
		results.add(new HalsteadMetricResult(sourceCodeLocation,
			codeRange.getType(), codeRange.getCanonicalName(),
			metric.getHalsteadResults(), metric.getQuality()));
	    }
	    return results;
	}
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	QualityLevel qualityLevel = null;
	HalsteadMetricResult metricResults = null;
	EvaluatorStore evaluatorStore = getEvaluatorStore();
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		if (evaluatorStore.hasFileResults(
			HalsteadMetricFileResults.class, child.getHashId())) {
		    HalsteadMetricFileResults results = evaluatorStore
			    .readFileResults(HalsteadMetricFileResults.class,
				    child.getHashId());
		    for (HalsteadMetricResult result : results.getResults()) {
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
		if (evaluatorStore
			.hasDirectoryResults(
				HalsteadMetricDirectoryResults.class,
				child.getHashId())) {
		    HalsteadMetricDirectoryResults results = evaluatorStore
			    .readDirectoryResults(
				    HalsteadMetricDirectoryResults.class,
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
	HalsteadMetricDirectoryResults finalResults = new HalsteadMetricDirectoryResults(
		metricResults);
	finalResults.addQualityLevel(qualityLevel);
	return finalResults;
    }

    private HalsteadMetricResult combine(AnalysisFileTree directory,
	    HalsteadMetricResult results, HalsteadMetricResult result) {
	if (result != null) {
	    if (results == null) {
		results = new HalsteadMetricResult(
			new UnspecifiedSourceCodeLocation(),
			CodeRangeType.DIRECTORY, directory.getName(),
			result.getHalsteadResult(), result.getQuality());
	    } else {
		results = HalsteadMetricResult.combine(results, result);
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
    protected Class<? extends MetricFileResults> getFileResultsClass() {
	return HalsteadMetricFileResults.class;
    }

    @Override
    protected Class<? extends MetricDirectoryResults> getDirectoryResultsClass() {
	return HalsteadMetricDirectoryResults.class;
    }

}
