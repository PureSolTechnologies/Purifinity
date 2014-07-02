package com.puresoltechnologies.purifinity.server.metrics.entropy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
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
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;

@Stateless
@Remote(Evaluator.class)
public class EntropyMetricEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

    @Inject
    private EvaluatorStoreService store;

    public EntropyMetricEvaluator() {
	super(EntropyMetric.ID, EntropyMetric.NAME, EntropyMetric.DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
	return configurationParameters;
    }

    @Override
    protected MetricFileResults processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	HalsteadMetricFileResults halsteadFileResults = store.readFileResults(
		HalsteadMetricFileResults.class, hashId);

	EntropyFileResults results = new EntropyFileResults();
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    HalsteadMetricResult halsteadFileResult = findFileResult(
		    halsteadFileResults, codeRange);
	    HalsteadResult halstead = halsteadFileResult.getHalsteadResult();
	    EntropyMetricResult result = calculateEntropyResult(halstead);
	    results.add(new EntropyResult(sourceCodeLocation, codeRange
		    .getType(), codeRange.getCanonicalName(), result,
		    EntropyQuality.get(codeRange.getType(), result)));
	}
	return results;
    }

    /**
     * This method calculates the entropy result from a {@link HalsteadResult}.
     * 
     * @param halstead
     *            is a {@link HalsteadResult} object containing the Halstead
     *            results.
     * @return An {@link EntropyMetricResult} object is returned containing the
     *         result calculation results.
     */
    private EntropyMetricResult calculateEntropyResult(HalsteadResult halstead) {
	Map<String, Integer> operands = halstead.getOperands();

	double maxEntropy = Math.log(halstead.getDifferentOperands())
		/ Math.log(2.0);
	double entropy = 0.0;
	for (String operant : operands.keySet()) {
	    int count = operands.get(operant);
	    entropy += count / (double) halstead.getTotalOperands()
		    * Math.log(count / (double) halstead.getTotalOperands())
		    / Math.log(2.0);
	}
	entropy *= -1.0;
	double normEntropy = entropy / maxEntropy;
	double entropyRedundancy = maxEntropy - entropy;
	double redundancy = entropyRedundancy * halstead.getDifferentOperands()
		/ maxEntropy;
	double normalizedRedundancy = redundancy
		/ halstead.getDifferentOperands();
	EntropyMetricResult result = new EntropyMetricResult(
		halstead.getVocabularySize(), halstead.getProgramLength(),
		entropy, maxEntropy, normEntropy, entropyRedundancy,
		redundancy, normalizedRedundancy);
	return result;
    }

    private HalsteadMetricResult findFileResult(
	    HalsteadMetricFileResults halsteadFileResults, CodeRange codeRange) {
	for (HalsteadMetricResult t : halsteadFileResults.getResults()) {
	    if ((t.getCodeRangeType() == codeRange.getType())
		    && (t.getCodeRangeName().equals(codeRange
			    .getCanonicalName()))) {
		return t;
	    }
	}
	return null;
    }

    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	HalsteadMetricDirectoryResults halsteadDirectoryResults = store
		.readDirectoryResults(HalsteadMetricDirectoryResults.class,
			directory.getHashId());
	if (halsteadDirectoryResults == null) {
	    return null;
	}
	EntropyMetricResult entropyResult = calculateEntropyResult(halsteadDirectoryResults
		.getResult().getHalsteadResult());
	EntropyDirectoryResults directoryResults = new EntropyDirectoryResults(
		new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
		directory.getName());
	for (AnalysisFileTree child : directory.getChildren()) {
	    QualityLevel childLevel;
	    if (child.isFile()) {
		EntropyFileResults fileResults = store.readFileResults(
			EntropyFileResults.class, child.getHashId());
		if (fileResults == null) {
		    continue;
		}
		childLevel = fileResults.getQualityLevel();
	    } else {
		EntropyDirectoryResults childDirectoryResults = store
			.readDirectoryResults(EntropyDirectoryResults.class,
				child.getHashId());
		if (childDirectoryResults == null) {
		    continue;
		}
		childLevel = childDirectoryResults.getQualityLevel();
	    }
	    if (childLevel != null) {
		directoryResults.addQualityLevel(childLevel);
	    }
	}
	directoryResults.setEntropyResult(entropyResult);
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
	return store.readFileResults(EntropyFileResults.class, hashId);
    }

    @Override
    protected boolean hasFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasFileResults(EntropyFileResults.class, hashId);
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
	return store
		.readDirectoryResults(EntropyDirectoryResults.class, hashId);
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasDirectoryResults(EntropyDirectoryResults.class, hashId);
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
