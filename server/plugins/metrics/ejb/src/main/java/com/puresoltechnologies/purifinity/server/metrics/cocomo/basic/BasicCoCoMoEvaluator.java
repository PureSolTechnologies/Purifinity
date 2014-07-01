/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.HashId;
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
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoDirectoryResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.BasicCoCoMoResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.cocomo.basic.SoftwareProject;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluator;
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
public class BasicCoCoMoEvaluator extends AbstractEvaluator {

    private static final long serialVersionUID = 5098378023541671490L;

    public static final String ID = BasicCoCoMoEvaluator.class.getName();

    public static final String NAME = "Basic COst COnstruction MOdel";

    public static final String DESCRIPTION = "The Basic COst COnstruction MOdel is a simple way "
	    + "to estimate the construction costs of a "
	    + "software project by couting the physical lines of code.";

    public static final Set<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new HashSet<>();

    public static final Set<String> DEPENDENCIES = new HashSet<>();
    static {
	DEPENDENCIES.add(SLOCMetricCalculator.ID);
    }

    private static final Set<ConfigurationParameter<?>> CONFIGURATION_PARAMETERS = new HashSet<>();

    private final EvaluatorStore store;
    private final EvaluatorStore slocStore;
    private SoftwareProject complexity = SoftwareProject.LOW;
    private int averageSalary = 56286;
    private String currency = "USD";

    public BasicCoCoMoEvaluator() {
	super(NAME, DESCRIPTION);
	store = getEvaluatorStore();
	slocStore = EvaluatorStoreFactory.getFactory().createInstance(
		SLOCEvaluator.class);
    }

    @Override
    public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
	return CONFIGURATION_PARAMETERS;
    }

    public void setComplexity(SoftwareProject complexity) {
	this.complexity = complexity;
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
	if (slocStore.hasFileResults(hashId)) {
	    SLOCFileResults slocResults = (SLOCFileResults) slocStore
		    .readFileResults(hashId);
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(
		    hashId).getSourceCodeLocation();
	    for (SLOCResult results : slocResults.getResults()) {
		if (results.getCodeRangeType() == CodeRangeType.FILE) {
		    int phyLoc = results.getSLOCMetric().getPhyLOC();
		    BasicCoCoMoFileResults fileResults = new BasicCoCoMoFileResults(
			    sourceCodeLocation);
		    fileResults.setAverageSalary(averageSalary, currency);
		    fileResults.setComplexity(complexity);
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
		if (store.hasFileResults(hashId)) {
		    BasicCoCoMoResults fileResults = (BasicCoCoMoResults) store
			    .readFileResults(hashId);
		    phyLoc += fileResults.getPhyLOC();
		}
	    } else {
		if (store.hasDirectoryResults(hashId)) {
		    BasicCoCoMoDirectoryResults directoryResults = (BasicCoCoMoDirectoryResults) store
			    .readDirectoryResults(hashId);
		    phyLoc += directoryResults.getPhyLOC();
		}
	    }
	}
	BasicCoCoMoDirectoryResults directoryResults = new BasicCoCoMoDirectoryResults(
		new SourceFileLocation(new File(""),
			directory.getPathFile(false)));
	directoryResults.setAverageSalary(averageSalary, currency);
	directoryResults.setComplexity(complexity);
	directoryResults.setSloc(phyLoc);
	return directoryResults;
    }

    @Override
    protected MetricDirectoryResults processProject(AnalysisRun analysisRun)
	    throws InterruptedException, EvaluationStoreException {
	AnalysisFileTree directory = analysisRun.getFileTree();
	return processDirectory(analysisRun, directory);
    }

}
