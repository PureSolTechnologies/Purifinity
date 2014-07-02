package com.puresoltechnologies.purifinity.server.metrics.codedepth;

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
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthResult;
import com.puresoltechnologies.purifinity.server.core.api.analysis.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;

/**
 * This evaluator calculates the nesting depth of the source code. A too deep
 * nesting leads into a hard understandable code and a maintainability
 * nightmare.
 * 
 * @author Rick-Rainer Ludwig
 */
@Stateless
@Remote(Evaluator.class)
public class CodeDepthMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

    @Inject
    private EvaluatorStoreService store;

    public CodeDepthMetricEvaluator() {
	super(CodeDepthMetric.ID, CodeDepthMetric.NAME,
		CodeDepthMetric.DESCRIPTION);
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

	    CodeDepthFileResults results = new CodeDepthFileResults();
	    HashId hashId = analysis.getAnalysisInformation().getHashId();
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
		    hashId).getSourceCodeLocation();
	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		CodeDepthMetric metric = new CodeDepthMetric(analysisRun,
			language, codeRange);
		execute(metric);
		SourceCodeQuality quality = metric.getQuality();
		results.add(new CodeDepthResult(sourceCodeLocation, codeRange
			.getType(), codeRange.getCanonicalName(), metric
			.getMaxDepth(), quality));
	    }
	    return results;
	}
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	CodeDepthDirectoryResults directoryResults = new CodeDepthDirectoryResults(
		new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
		directory.getName());
	int maxDepth = 0;
	for (AnalysisFileTree child : directory.getChildren()) {
	    QualityLevel childLevel;
	    if (child.isFile()) {
		CodeDepthFileResults fileResults = store.readFileResults(
			CodeDepthFileResults.class, child.getHashId());
		if (fileResults == null) {
		    continue;
		}
		childLevel = fileResults.getQualityLevel();
		for (CodeDepthResult result : fileResults.getResults()) {
		    maxDepth = Math.max(maxDepth, result.getMaxDepth());
		}
	    } else {
		CodeDepthDirectoryResults childDirectoryResults = store
			.readDirectoryResults(CodeDepthDirectoryResults.class,
				child.getHashId());
		if (childDirectoryResults == null) {
		    continue;
		}
		childLevel = childDirectoryResults.getQualityLevel();
		maxDepth = Math.max(maxDepth,
			childDirectoryResults.getMaxDepth());
	    }
	    if (childLevel != null) {
		directoryResults.addQualityLevel(childLevel);
		directoryResults.setMaxDepth(maxDepth);
	    }
	}
	return directoryResults;
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
	return store.readFileResults(CodeDepthFileResults.class, hashId);
    }

    @Override
    protected boolean hasFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasFileResults(CodeDepthFileResults.class, hashId);
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
	return store.readDirectoryResults(CodeDepthDirectoryResults.class,
		hashId);
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasDirectoryResults(CodeDepthDirectoryResults.class,
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
