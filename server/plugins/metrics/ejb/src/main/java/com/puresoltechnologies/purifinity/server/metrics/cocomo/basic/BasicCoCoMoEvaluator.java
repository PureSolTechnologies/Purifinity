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

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
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
import com.puresoltechnologies.purifinity.server.metrics.cocomo.basic.db.BasicCoCoMoEvaluatorDAO;
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
public class BasicCoCoMoEvaluator extends AbstractMetricEvaluator {

    public static final String ID = BasicCoCoMoEvaluator.class.getName();

    public static final String NAME = "Basic COst COnstruction MOdel";

    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String DESCRIPTION = "The Basic COst COnstruction MOdel is a simple way "
	    + "to estimate the construction costs of a " + "software project by couting the physical lines of code.";

    public static final QualityCharacteristic[] EVALUATED_QUALITY_CHARACTERISTICS = new QualityCharacteristic[] {};

    public static final Set<String> DEPENDENCIES = new HashSet<>();

    static {
	DEPENDENCIES.add(SLOCMetricCalculator.ID);
    }

    private static final String COMPLEXITY_PROPERTY = "evaluator.cocomo.basic.complexity";
    private static final String SALARY_PROPERTY = "evaluator.cocomo.basic.salary";
    private static final String CURRENCY_PROPERTY = "evaluator.cocomo.basic.currency";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {
	    new ConfigurationParameter<String>("Complexity", "", LevelOfMeasurement.NOMINAL,
		    "Specifies the complexity of the project. Valid values: LOW, MEDIUM, HIGH", String.class,
		    COMPLEXITY_PROPERTY, "/", "LOW"),
	    new ConfigurationParameter<Integer>("Yearly Salary", "currency", LevelOfMeasurement.RATIO,
		    "Specifies the average yearly salary for a single developer for cost calculation.", Integer.class,
		    SALARY_PROPERTY, "/", 56286),
	    new ConfigurationParameter<String>("Currency", "", LevelOfMeasurement.NOMINAL,
		    "Currency for cost calculation.", String.class, CURRENCY_PROPERTY, "/", "USD") };

    private SoftwareProject complexity = SoftwareProject.LOW;
    private int averageSalary = 56286;
    private String currency = "USD";

    @Inject
    private BasicCoCoMoEvaluatorDAO basicCoCoMoEvaluatorDAO;

    @Inject
    private SLOCMetricEvaluatorDAO slocMetricEvaluatorDAO;

    public BasicCoCoMoEvaluator() {
	super(ID, NAME, PLUGIN_VERSION, DESCRIPTION);

    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return PARAMETERS;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return BasicCoCoMoEvaluatorParameter.ALL;
    }

    public SoftwareProject getComplexity() {
	return complexity;
    }

    public int getAverageSalary() {
	return averageSalary;
    }

    public String getCurrency() {
	return currency;
    }

    public void setComplexity(SoftwareProject complexity) {
	this.complexity = complexity;
    }

    public void setAverageSalary(int averageSalary) {
	this.averageSalary = averageSalary;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    protected FileMetrics processFile(AnalysisRun analysisRun, CodeAnalysis analysis) throws EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	if (slocMetricEvaluatorDAO.hasFileResults(hashId)) {
	    List<SLOCResult> slocResults = slocMetricEvaluatorDAO.readFileResults(hashId);
	    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	    for (SLOCResult results : slocResults) {
		if (results.getCodeRangeType() == CodeRangeType.FILE) {
		    int phyLoc = results.getSLOCMetric().getPhyLOC();
		    BasicCoCoMoFileResults fileResults = new BasicCoCoMoFileResults(BasicCoCoMoEvaluator.ID,
			    BasicCoCoMoEvaluator.PLUGIN_VERSION, hashId, sourceCodeLocation, new Date());
		    fileResults.setAverageSalary(averageSalary, currency);
		    fileResults.setComplexity(complexity);
		    fileResults.setSloc(phyLoc);
		    CodeRange codeRange = new CodeRange(results.getCodeRangeName(), results.getCodeRangeName(),
			    results.getCodeRangeType(), analysis.getUniversalSyntaxTree());
		    basicCoCoMoEvaluatorDAO.storeFileResults(hashId, sourceCodeLocation, codeRange, fileResults);
		    return fileResults;
		}
	    }
	}
	return null;
    }

    @Override
    protected DirectoryMetrics processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	int phyLoc = 0;
	for (AnalysisFileTree child : directory.getChildren()) {
	    HashId hashId = child.getHashId();
	    if (child.isFile()) {
		if (slocMetricEvaluatorDAO.hasFileResults(hashId)) {
		    List<SLOCResult> fileResults = slocMetricEvaluatorDAO.readFileResults(hashId);
		    for (SLOCResult metrics : fileResults) {
			if (metrics.getCodeRangeType().equals(CodeRangeType.FILE)) {
			    phyLoc += metrics.getSLOCMetric().getPhyLOC();
			    break;
			}
		    }
		}
	    } else {
		if (slocMetricEvaluatorDAO.hasDirectoryResults(hashId)) {
		    SLOCResult directoryResults = slocMetricEvaluatorDAO.readDirectoryResults(hashId);
		    phyLoc += directoryResults.getSLOCMetric().getPhyLOC();
		}
	    }
	}
	HashId hashId = directory.getHashId();
	BasicCoCoMoDirectoryResults directoryResults = new BasicCoCoMoDirectoryResults(BasicCoCoMoEvaluator.ID,
		BasicCoCoMoEvaluator.PLUGIN_VERSION, hashId, new Date());
	directoryResults.setAverageSalary(averageSalary, currency);
	directoryResults.setComplexity(complexity);
	directoryResults.setSloc(phyLoc);
	basicCoCoMoEvaluatorDAO.storeDirectoryResults(hashId, directoryResults);
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
	if (COMPLEXITY_PROPERTY.equals(parameter.getPropertyKey())) {
	    setComplexity(SoftwareProject.valueOf((String) value));
	} else if (SALARY_PROPERTY.equals(parameter.getPropertyKey())) {
	    setAverageSalary(averageSalary);
	} else if (CURRENCY_PROPERTY.equals(parameter.getPropertyKey())) {
	    setCurrency((String) value);
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	if (COMPLEXITY_PROPERTY.equals(parameter.getPropertyKey())) {
	    return getComplexity().name();
	} else if (SALARY_PROPERTY.equals(parameter.getPropertyKey())) {
	    return Integer.valueOf(getAverageSalary());
	} else if (CURRENCY_PROPERTY.equals(parameter.getPropertyKey())) {
	    return getCurrency();
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }
}
