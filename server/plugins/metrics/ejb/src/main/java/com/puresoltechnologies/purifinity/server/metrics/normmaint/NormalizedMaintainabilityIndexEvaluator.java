package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class NormalizedMaintainabilityIndexEvaluator extends
	AbstractMetricEvaluator {

    public static final String ID = NormalizedMaintainabilityIndexEvaluator.class
	    .getName();
    public static final String NAME = "Normalized Maintainability Index";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "Normalized Maintainability Index calculation.";
    public static final Set<ConfigurationParameter<?>> PARAMETERS = new HashSet<>();

    public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<QualityCharacteristic>();
    static {
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.ANALYSABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.CHANGEABILITY);
	EVALUATED_QUALITY_CHARACTERISTICS
		.add(QualityCharacteristic.TESTABILITY);
    }
    public static final Set<String> DEPENDENCIES = new HashSet<>();
    static {
	DEPENDENCIES.add(MaintainabilityIndexEvaluator.ID);
    }

    public NormalizedMaintainabilityIndexEvaluator() {
	super(ID, NAME, DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
	return PARAMETERS;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();
	NormalizedMaintainabilityIndexFileResults results = new NormalizedMaintainabilityIndexFileResults(
		NormalizedMaintainabilityIndexEvaluator.ID, hashId,
		sourceCodeLocation, new Date());

	EvaluatorStore evaluatorStore = getEvaluatorStore();
	GenericFileMetrics maintainabilityFileResults = evaluatorStore
		.readFileResults(hashId, MaintainabilityIndexEvaluator.ID);

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    GenericCodeRangeMetrics maintainabilityIndexFileResult = findFileResult(
		    maintainabilityFileResults, codeRange);

	    double miWoc = maintainabilityIndexFileResult.getValue(
		    MaintainabilityIndexEvaluatorParameter.MI_WOC).getValue();
	    double miCw = maintainabilityIndexFileResult.getValue(
		    MaintainabilityIndexEvaluatorParameter.MI_CW).getValue();
	    NormalizedMaintainabilityIndexResult result = new NormalizedMaintainabilityIndexResult(
		    miWoc, miCw);

	    results.add(new NormalizedMaintainabilityIndexFileResult(
		    sourceCodeLocation, codeRange.getType(), codeRange
			    .getCanonicalName(), result,
		    NormalizedMaintainabilityQuality.get(codeRange.getType(),
			    result)));
	}
	return results;
    }

    private GenericCodeRangeMetrics findFileResult(
	    GenericFileMetrics maintainabilityIndexFileResults,
	    CodeRange codeRange) {
	for (GenericCodeRangeMetrics t : maintainabilityIndexFileResults
		.getValues()) {
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
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	NormalizedMaintainabilityIndexDirectoryResults finalResults = new NormalizedMaintainabilityIndexDirectoryResults(
		NormalizedMaintainabilityIndexEvaluator.ID,
		directory.getHashId(), new Date());
	return finalResults;
    }

    @Override
    protected DirectoryMetrics processProject(AnalysisRun analysisRun,
	    boolean enableReevaluation) throws InterruptedException,
	    EvaluationStoreException {
	return processDirectory(analysisRun, analysisRun.getFileTree());
    }
}
