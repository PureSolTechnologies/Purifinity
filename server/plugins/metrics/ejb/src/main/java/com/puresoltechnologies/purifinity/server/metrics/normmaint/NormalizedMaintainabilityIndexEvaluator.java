package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.Version;
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
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluator;

@Stateless
@Remote(Evaluator.class)
public class NormalizedMaintainabilityIndexEvaluator extends
	AbstractMetricEvaluator {

    private static final long serialVersionUID = -5093217611195212999L;

    public static final String ID = NormalizedMaintainabilityIndexEvaluator.class
	    .getName();
    public static final String NAME = "Normalized Maintainability Index";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "Normalized Maintainability Index calculation.";

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

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

    public NormalizedMaintainabilityIndexEvaluator() {
	super(ID, NAME, DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
	return configurationParameters;
    }

    @Override
    protected MetricFileResults processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws InterruptedException,
	    EvaluationStoreException {
	NormalizedMaintainabilityIndexFileResults results = new NormalizedMaintainabilityIndexFileResults();

	EvaluatorStore evaluatorStore = getEvaluatorStore();
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	MaintainabilityIndexFileResults maintainabilityFileResults = evaluatorStore
		.readFileResults(MaintainabilityIndexFileResults.class, hashId);
	SourceCodeLocation sourceCodeLocation = analysisRun
		.findTreeNode(hashId).getSourceCodeLocation();

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {

	    MaintainabilityIndexFileResult maintainabilityIndexFileResult = findFileResult(
		    maintainabilityFileResults, codeRange);

	    MaintainabilityIndexResult maintainabilityIndex = maintainabilityIndexFileResult
		    .getMaintainabilityIndexResult();
	    NormalizedMaintainabilityIndexResult result = new NormalizedMaintainabilityIndexResult(
		    maintainabilityIndex.getMIwoc(),
		    maintainabilityIndex.getMIcw());

	    results.add(new NormalizedMaintainabilityIndexFileResult(
		    sourceCodeLocation, codeRange.getType(), codeRange
			    .getCanonicalName(), result,
		    NormalizedMaintainabilityQuality.get(codeRange.getType(),
			    result)));
	}
	return results;
    }

    private MaintainabilityIndexFileResult findFileResult(
	    MaintainabilityIndexFileResults maintainabilityIndexFileResults,
	    CodeRange codeRange) {
	for (MaintainabilityIndexFileResult t : maintainabilityIndexFileResults
		.getResults()) {
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
    protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	QualityLevel qualityLevel = null;
	EvaluatorStore evaluatorStore = getEvaluatorStore();
	for (AnalysisFileTree child : directory.getChildren()) {
	    if (child.isFile()) {
		NormalizedMaintainabilityIndexFileResults results = evaluatorStore
			.readFileResults(
				NormalizedMaintainabilityIndexFileResults.class,
				child.getHashId());
		if (results != null) {
		    for (NormalizedMaintainabilityIndexFileResult result : results
			    .getResults()) {
			qualityLevel = QualityLevel.combine(qualityLevel,
				new QualityLevel(result.getQuality()));
		    }
		}
	    } else {
		NormalizedMaintainabilityIndexDirectoryResults results = evaluatorStore
			.readDirectoryResults(
				NormalizedMaintainabilityIndexDirectoryResults.class,
				child.getHashId());
		if (results != null) {
		    qualityLevel = QualityLevel.combine(qualityLevel,
			    results.getQualityLevel());
		}
	    }
	}
	NormalizedMaintainabilityIndexDirectoryResults finalResults = new NormalizedMaintainabilityIndexDirectoryResults(
		new UnspecifiedSourceCodeLocation(), CodeRangeType.DIRECTORY,
		directory.getName());
	finalResults.addQualityLevel(qualityLevel);
	return finalResults;
    }

    @Override
    protected MetricDirectoryResults processProject(AnalysisRun analysisRun)
	    throws InterruptedException, EvaluationStoreException {
	return processDirectory(analysisRun, analysisRun.getFileTree());
    }

    @Override
    protected Class<? extends MetricFileResults> getFileResultsClass() {
	return NormalizedMaintainabilityIndexFileResults.class;
    }

    @Override
    protected Class<? extends MetricDirectoryResults> getDirectoryResultsClass() {
	return NormalizedMaintainabilityIndexDirectoryResults.class;
    }
}
