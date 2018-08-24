package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetric;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadResult;
import com.puresoltechnologies.purifinity.server.metrics.halstead.db.HalsteadMetricsEvaluatorDAO;
import com.puresoltechnologies.purifinity.server.metrics.maintainability.db.MaintainabilityIndexEvaluatorDAO;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricResult;
import com.puresoltechnologies.purifinity.server.metrics.mccabe.db.McCabeMetricEvaluatorDAO;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.server.metrics.sloc.db.SLOCMetricEvaluatorDAO;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class MaintainabilityIndexEvaluator extends AbstractMetricEvaluator {

    public static final String ID = MaintainabilityIndexEvaluator.class.getName();
    public static final String NAME = "Maintainability Index";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "Maintainability Index calculation.";
    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {};
    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.ANALYSABILITY, QualityCharacteristic.CHANGEABILITY,
	    QualityCharacteristic.TESTABILITY };
    public static final Set<String> DEPENDENCIES = new HashSet<>();

    static {
	DEPENDENCIES.add(SLOCMetricCalculator.ID);
	DEPENDENCIES.add(McCabeMetric.ID);
	DEPENDENCIES.add(HalsteadMetric.ID);
    }

    @Inject
    private Logger logger;

    @Inject
    private SLOCMetricEvaluatorDAO slocMetricEvaluatorDAO;

    @Inject
    private McCabeMetricEvaluatorDAO mcCabeMetricEvaluatorDAO;

    @Inject
    private HalsteadMetricsEvaluatorDAO halsteadMetricsEvaluatorDAO;

    @Inject
    private MaintainabilityIndexEvaluatorDAO maintainabilityIndexEvaluatorDAO;

    public MaintainabilityIndexEvaluator() {
	super(ID, NAME, PLUGIN_VERSION, DESCRIPTION);
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return PARAMETERS;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return MaintainabilityIndexEvaluatorParameter.ALL;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, EvaluationStoreException {
	AnalysisInformation analysisInformation = analysis.getAnalysisInformation();
	AnalysisInformation analyzedFile = analysisInformation;
	HashId hashId = analyzedFile.getHashId();
	SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();

	MaintainabilityIndexFileResults results = new MaintainabilityIndexFileResults(MaintainabilityIndexEvaluator.ID,
		MaintainabilityIndexEvaluator.PLUGIN_VERSION, hashId, sourceCodeLocation, new Date());

	List<SLOCResult> slocFileResults = slocMetricEvaluatorDAO.readFileResults(hashId);
	List<McCabeMetricResult> mcCabeFileResults = mcCabeMetricEvaluatorDAO.readFileResults(hashId);
	List<HalsteadMetricResult> halsteadFileResults = halsteadMetricsEvaluatorDAO.readFileResults(hashId);

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCResult slocCodeRangeResult = findSLOCMetricResult(slocFileResults, codeRange);
	    if (slocCodeRangeResult == null) {
		logger.warn("No SLOC result available for '" + hashId + "', yet.");
		continue;
	    }
	    McCabeMetricResult mcCabeCodeRangeResult = findMcCabeMetricResult(mcCabeFileResults, codeRange);
	    if (mcCabeCodeRangeResult == null) {
		logger.warn("No McCabe Metric result available for '" + hashId + "', yet.");
		continue;
	    }
	    HalsteadMetricResult halsteadCodeRangeResult = findHalsteadMetricResult(halsteadFileResults, codeRange);
	    if (halsteadCodeRangeResult == null) {
		logger.warn("No Halstead Metric result available for '" + hashId + "', yet.");
		continue;
	    }

	    SLOCMetric slocMetric = slocCodeRangeResult.getSLOCMetric();
	    int phyLOC = slocMetric.getPhyLOC();
	    int comLOC = slocMetric.getComLOC();
	    int vG = mcCabeCodeRangeResult.getCyclomaticComplexity();
	    HalsteadResult halsteadResult = halsteadCodeRangeResult.getHalsteadResult();
	    double hv = halsteadResult.getHalsteadVolume();
	    double MIwoc = 171.0 - 5.2 * Math.log(hv) - 0.23 * vG - 16.2 * Math.log(phyLOC * 100.0 / 171.0);
	    double MIcw = 50 * Math.sin(Math.sqrt(2.4 * comLOC / phyLOC));
	    MaintainabilityIndexResult result = new MaintainabilityIndexResult(MIwoc, MIcw);
	    MaintainabilityIndexFileResult fileResult = new MaintainabilityIndexFileResult(sourceCodeLocation,
		    codeRange.getType(), codeRange.getCanonicalName(), result);
	    maintainabilityIndexEvaluatorDAO.storeFileResults(hashId, sourceCodeLocation, codeRange, fileResult);
	    results.add(fileResult);
	}
	return results;
    }

    private SLOCResult findSLOCMetricResult(List<SLOCResult> slocFileResults, CodeRange codeRange) {
	if (slocFileResults != null) {
	    for (SLOCResult t : slocFileResults) {
		if ((t.getCodeRangeType() == codeRange.getType())
			&& (t.getCodeRangeName().equals(codeRange.getSimpleName()))) {
		    return t;
		}
	    }
	}
	return null;
    }

    private McCabeMetricResult findMcCabeMetricResult(List<McCabeMetricResult> mcCabeFileResults, CodeRange codeRange) {
	if (mcCabeFileResults != null) {
	    for (McCabeMetricResult t : mcCabeFileResults) {
		if ((t.getCodeRangeType() == codeRange.getType())
			&& (t.getCodeRangeName().equals(codeRange.getSimpleName()))) {
		    return t;
		}
	    }
	}
	return null;
    }

    private HalsteadMetricResult findHalsteadMetricResult(List<HalsteadMetricResult> halsteadFileResults,
	    CodeRange codeRange) {
	if (halsteadFileResults != null) {
	    for (HalsteadMetricResult t : halsteadFileResults) {
		if ((t.getCodeRangeType() == codeRange.getType())
			&& (t.getCodeRangeName().equals(codeRange.getSimpleName()))) {
		    return t;
		}
	    }
	}
	return null;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	MaintainabilityIndexDirectoryResults finalResults = new MaintainabilityIndexDirectoryResults(
		MaintainabilityIndexEvaluator.ID, MaintainabilityIndexEvaluator.PLUGIN_VERSION, directory.getHashId(),
		new Date());
	return finalResults;
    }

    @Override
    protected DirectoryMetrics processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	return processDirectory(analysisRun, analysisRun.getFileTree());
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
