package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;

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
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

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

    private AnalyzerServiceManagerRemote analyzerServiceManager;

    public CodeDepthMetricEvaluator() {
	super(CodeDepthMetric.ID, CodeDepthMetric.NAME, CodeDepthMetric.PLUGIN_VERSION, CodeDepthMetric.DESCRIPTION);
    }

    @PostConstruct
    public void initialize() {
	analyzerServiceManager = JndiUtils.createRemoteEJBInstance(AnalyzerServiceManagerRemote.class,
		AnalyzerServiceManagerRemote.JNDI_NAME);
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return CodeDepthMetric.PARAMETERS;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return CodeDepthMetricEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	AnalysisInformation analysisInformation = analysis.getAnalysisInformation();
	AnalyzerServiceInformation analyzerServiceInformation = analyzerServiceManager
		.findByName(analysisInformation.getLanguageName(), analysisInformation.getLanguageVersion());
	ProgrammingLanguage language = analyzerServiceManager.createProxy(analyzerServiceInformation.getJndiName());

	HashId hashId = analysisInformation.getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	CodeDepthFileResults results = new CodeDepthFileResults(CodeDepthMetric.ID, CodeDepthMetric.PLUGIN_VERSION,
		hashId, sourceCodeLocation, new Date());
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    CodeDepthMetric metric = new CodeDepthMetric(analysisRun, language, codeRange);
	    metric.run();
	    Severity quality = metric.getQuality();
	    results.add(new CodeDepthResult(sourceCodeLocation, codeRange.getType(), codeRange.getCanonicalName(),
		    metric.getMaxDepth(), quality));
	}
	return results;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	CodeDepthDirectoryResults directoryResults = new CodeDepthDirectoryResults(CodeDepthMetric.ID,
		CodeDepthMetric.PLUGIN_VERSION, directory.getHashId(), new Date(), new UnspecifiedSourceCodeLocation(),
		CodeRangeType.DIRECTORY, directory.getName());
	int maxDepth = 0;
	EvaluatorStore evaluatorStore = getEvaluatorStore();
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		GenericFileMetrics fileResults = evaluatorStore.readFileResults(child.getHashId(), CodeDepthMetric.ID);
		if (fileResults == null) {
		    continue;
		}
		for (GenericCodeRangeMetrics result : fileResults.getCodeRangeMetrics()) {
		    maxDepth = Math.max(maxDepth,
			    result.getValue(CodeDepthMetricEvaluatorParameter.MAX_DEPTH).getValue());
		}
	    } else {
		GenericDirectoryMetrics childDirectoryResults = evaluatorStore.readDirectoryResults(child.getHashId(),
			CodeDepthMetric.ID);
		if (childDirectoryResults == null) {
		    continue;
		}
		maxDepth = Math.max(maxDepth, (Integer) childDirectoryResults.getValues()
			.get(CodeDepthMetricEvaluatorParameter.MAX_DEPTH.getName()).getValue());
	    }
	    directoryResults.setMaxDepth(maxDepth);
	}
	return directoryResults;
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
