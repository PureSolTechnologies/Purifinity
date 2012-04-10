/***************************************************************************
 *
 *   CoCoMo.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.cocomo;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Result;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.uhura.parser.ParserTree;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends ProjectEvaluator {

    private static final long serialVersionUID = 5098378023541671490L;

    private static final Logger logger = LoggerFactory.getLogger(CoCoMo.class);

    public static final String NAME = "COst COnstruction MOdel";

    public static final String DESCRIPTION = "The COst COnstruction MOdel is a simple way "
	    + "to estimate the construction costs of a "
	    + "software project by couting the physical lines of code.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new Vector<QualityCharacteristic>();

    private final CoCoMoValueSet cocomoValues = new CoCoMoValueSet();
    private final Hashtable<AnalyzedFile, CoCoMoValueSet> fileCoCoMoValues = new Hashtable<AnalyzedFile, CoCoMoValueSet>();
    private final AnalysisRun projectAnalyzer;

    public CoCoMo(AnalysisRun projectAnalyzer) {
	super(NAME);
	this.projectAnalyzer = projectAnalyzer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnalysisRun getProjectAnalyzer() {
	return projectAnalyzer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus run(IProgressMonitor monitor) {
	List<AnalyzedFile> files = projectAnalyzer.getAnalyzedFiles();
	monitor.beginTask(NAME, files.size());
	int sloc = 0;
	int count = 0;
	for (AnalyzedFile file : files) {
	    if (monitor.isCanceled()) {
		monitor.done();
		return Status.CANCEL_STATUS;
	    }
	    count++;
	    monitor.worked(count);
	    sloc += getFileSLOC(file);
	}
	cocomoValues.setSloc(sloc);
	monitor.done();
	return Status.OK_STATUS;
    }

    private int getFileSLOC(AnalyzedFile file) {
	try {
	    FileAnalysis analysis = projectAnalyzer.getAnalysis(file
		    .getHashId());
	    ParserTree parserTree = analysis.getParserTree();
	    SLOCMetric metric = new SLOCMetric(analysis.getLanguage(),
		    new CodeRange("", CodeRangeType.FILE, parserTree));
	    metric.schedule();
	    int sloc = metric.getSLOCResult().getProLOC();
	    addCodeRangeCoCoMo(file, sloc);
	    return sloc;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    logger.error("Process with next file...");
	    return 0;
	}
    }

    private void addCodeRangeCoCoMo(AnalyzedFile file, int sloc) {
	CoCoMoValueSet valueSet = new CoCoMoValueSet();
	valueSet.setSloc(sloc);
	valueSet.setComplexity(cocomoValues.getComplexity());
	valueSet.setAverageSalary(cocomoValues.getAverageSalary(),
		cocomoValues.getCurrency());
	fileCoCoMoValues.put(file, valueSet);
    }

    public void setComplexity(Complexity complexity) {
	cocomoValues.setComplexity(complexity);
	for (AnalyzedFile file : fileCoCoMoValues.keySet()) {
	    fileCoCoMoValues.get(file).setComplexity(complexity);
	}
    }

    public void setAverageSalary(int averageSalary, String currency) {
	cocomoValues.setAverageSalary(averageSalary, currency);
	for (AnalyzedFile file : fileCoCoMoValues.keySet()) {
	    fileCoCoMoValues.get(file)
		    .setAverageSalary(averageSalary, currency);
	}
    }

    public void setComplexity(String complexity) {
	for (Complexity complexityConstant : Complexity.class
		.getEnumConstants()) {
	    if (complexityConstant.toString().equals(complexity)) {
		setComplexity(complexityConstant);
		return;
	    }
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
	return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SourceCodeQuality getQuality() {
	return SourceCodeQuality.UNSPECIFIED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public List<Result> getResults() {
	return cocomoValues.getResults();
    }

}
