package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
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
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.metrics.AbstractMetricEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate.db.IntermediateCoCoMoEvaluatorDAO;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.server.metrics.sloc.db.SLOCMetricEvaluatorDAO;
import com.puresoltechnologies.versioning.Version;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateless
@Remote(Evaluator.class)
public class IntermediateCoCoMoEvaluator extends AbstractMetricEvaluator {

    public static final String ID = IntermediateCoCoMoEvaluator.class.getName();

    public static final String NAME = "Intermediate COst COnstruction MOdel";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "The Intermediate COst COnstruction MOdel is a simple way "
	    + "to estimate the construction costs of a "
	    + "software project by couting the physical lines of code.";

    public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<>();
    public static final Set<String> DEPENDENCIES = new HashSet<>();
    static {
	DEPENDENCIES.add(SLOCMetricCalculator.ID);
    }

    public static final Set<ConfigurationParameter<?>> PARAMETERS = new HashSet<>();

    @Inject
    private IntermediateCoCoMoEvaluatorDAO intermediateCoCoMoEvaluatorDAO;

    @Inject
    private SLOCMetricEvaluatorDAO slocMetricEvaluatorDAO;

    private SoftwareProject project = SoftwareProject.SEMI_DETACHED;
    private int averageSalary = 56286;
    private String currency = "USD";

    public IntermediateCoCoMoEvaluator() {
	super(ID, NAME, PLUGIN_VERSION, DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
	return PARAMETERS;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return IntermediateCoCoMoEvaluatorParameter.ALL;
    }

    public void setComplexity(SoftwareProject project) {
	this.project = project;
    }

    public void setAverageSalary(int averageSalary, String currency) {
	this.averageSalary = averageSalary;
	this.currency = currency;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	if (slocMetricEvaluatorDAO.hasFileResults(hashId)) {
	    List<SLOCResult> slocResults = slocMetricEvaluatorDAO
		    .readFileResults(hashId);
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
		    hashId).getSourceCodeLocation();
	    for (SLOCResult results : slocResults) {
		CodeRangeType codeRangeType = results.getCodeRangeType();
		if (codeRangeType.equals(CodeRangeType.FILE)) {
		    int phyLoc = results.getSLOCMetric().getPhyLOC();
		    IntermediateCoCoMoFileResults fileResults = new IntermediateCoCoMoFileResults(
			    IntermediateCoCoMoEvaluator.ID,
			    IntermediateCoCoMoEvaluator.PLUGIN_VERSION, hashId,
			    sourceCodeLocation, new Date());
		    fileResults.setAverageSalary(averageSalary, currency);
		    fileResults.setProject(project);
		    fileResults.setSloc(phyLoc);
		    CodeRange codeRange = new CodeRange(
			    results.getCodeRangeName(),
			    results.getCodeRangeName(),
			    results.getCodeRangeType(),
			    analysis.getUniversalSyntaxTree());
		    intermediateCoCoMoEvaluatorDAO.storeFileResults(hashId,
			    sourceCodeLocation, codeRange, fileResults);
		    return fileResults;
		}
	    }
	}
	return null;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	int phyLoc = 0;
	for (AnalysisFileTree child : directory.getChildren()) {
	    HashId hashId = child.getHashId();
	    if (child.isFile()) {
		if (slocMetricEvaluatorDAO.hasFileResults(hashId)) {
		    List<SLOCResult> fileResults = slocMetricEvaluatorDAO
			    .readFileResults(hashId);
		    for (SLOCResult metrics : fileResults) {
			if (metrics.getCodeRangeType().equals(
				CodeRangeType.FILE)) {
			    phyLoc += metrics.getSLOCMetric().getPhyLOC();
			    break;
			}
		    }
		}
	    } else {
		if (slocMetricEvaluatorDAO.hasDirectoryResults(hashId)) {
		    SLOCResult directoryResults = slocMetricEvaluatorDAO
			    .readDirectoryResults(hashId);
		    phyLoc += directoryResults.getSLOCMetric().getPhyLOC();
		}
	    }
	}
	HashId hashId = directory.getHashId();
	IntermediateCoCoMoDirectoryResults directoryResults = new IntermediateCoCoMoDirectoryResults(
		IntermediateCoCoMoEvaluator.ID,
		IntermediateCoCoMoEvaluator.PLUGIN_VERSION, hashId, new Date());
	directoryResults.setAverageSalary(averageSalary, currency);
	directoryResults.setProject(project);
	directoryResults.setSloc(phyLoc);
	intermediateCoCoMoEvaluatorDAO.storeDirectoryResults(hashId,
		directoryResults);
	return directoryResults;
    }

    @Override
    protected DirectoryMetrics processProject(AnalysisRun analysisRun,
	    boolean enableReevaluation) throws InterruptedException,
	    EvaluationStoreException {
	AnalysisFileTree directory = analysisRun.getFileTree();
	return processDirectory(analysisRun, directory);
    }
}
