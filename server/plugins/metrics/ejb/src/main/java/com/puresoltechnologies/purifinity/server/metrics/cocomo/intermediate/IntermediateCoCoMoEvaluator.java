/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.IntermediateCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.IntermediateCoCoMoFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.IntermediateCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.intermediate.SoftwareProject;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateless
@Remote(Evaluator.class)
public class IntermediateCoCoMoEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = 5098378023541671490L;

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

    private static final Set<ConfigurationParameter<?>> CONFIGURATION_PARAMETERS = new HashSet<>();

    @Inject
    private EvaluatorStoreService store;

    private SoftwareProject project = SoftwareProject.SEMI_DETACHED;
    private int averageSalary = 56286;
    private String currency = "USD";

    public IntermediateCoCoMoEvaluator() {
	super(ID, NAME, DESCRIPTION);
    }

    @Override
    public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
	return CONFIGURATION_PARAMETERS;
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
    protected MetricFileResults processFile(AnalysisRun analysisRun,
	    CodeAnalysis analysis) throws EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	if (store.hasFileResults(SLOCFileResults.class, hashId)) {
	    SLOCFileResults slocResults = store.readFileResults(
		    SLOCFileResults.class, hashId);
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
		    hashId).getSourceCodeLocation();
	    for (SLOCResult results : slocResults.getResults()) {
		if (results.getCodeRangeType() == CodeRangeType.FILE) {
		    int phyLoc = results.getSLOCMetric().getPhyLOC();
		    IntermediateCoCoMoFileResults fileResults = new IntermediateCoCoMoFileResults(
			    sourceCodeLocation);
		    fileResults.setAverageSalary(averageSalary, currency);
		    fileResults.setProject(project);
		    fileResults.setSloc(phyLoc);
		    return fileResults;
		}
	    }
	}
	return null;
    }

    @Override
    protected MetricDirectoryResults processDirectory(AnalysisRun analysisRun,
	    AnalysisFileTree directory) throws InterruptedException,
	    EvaluationStoreException {
	int phyLoc = 0;
	for (AnalysisFileTree child : directory.getChildren()) {
	    HashId hashId = child.getHashId();
	    if (child.isFile()) {
		if (store.hasFileResults(IntermediateCoCoMoResults.class,
			hashId)) {
		    IntermediateCoCoMoResults fileResults = store
			    .readFileResults(IntermediateCoCoMoResults.class,
				    hashId);
		    phyLoc += fileResults.getPhyLOC();
		}
	    } else {
		if (store.hasDirectoryResults(
			IntermediateCoCoMoDirectoryResults.class, hashId)) {
		    IntermediateCoCoMoDirectoryResults directoryResults = store
			    .readDirectoryResults(
				    IntermediateCoCoMoDirectoryResults.class,
				    hashId);
		    phyLoc += directoryResults.getPhyLOC();
		}
	    }
	}
	IntermediateCoCoMoDirectoryResults directoryResults = new IntermediateCoCoMoDirectoryResults(
		new SourceFileLocation(new File(""),
			directory.getPathFile(false)));
	directoryResults.setAverageSalary(averageSalary, currency);
	directoryResults.setProject(project);
	directoryResults.setSloc(phyLoc);
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
	return store.readFileResults(IntermediateCoCoMoFileResults.class,
		hashId);
    }

    @Override
    protected boolean hasFileResults(HashId hashId)
	    throws EvaluationStoreException {
	return store
		.hasFileResults(IntermediateCoCoMoFileResults.class, hashId);
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
	return store.readDirectoryResults(
		IntermediateCoCoMoDirectoryResults.class, hashId);
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId)
	    throws EvaluationStoreException {
	return store.hasDirectoryResults(
		IntermediateCoCoMoDirectoryResults.class, hashId);
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
