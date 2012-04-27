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

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.ProgrammingLanguages;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.metrics.sloc.SLOCMetricCalculator;
import com.puresol.coding.quality.api.QualityCharacteristic;
import com.puresol.uhura.parser.ParserTree;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends AbstractEvaluator<CoCoMoEvaluatorResults> {

    private static final long serialVersionUID = 5098378023541671490L;

    private static final Logger logger = LoggerFactory.getLogger(CoCoMo.class);

    public static final String NAME = "COst COnstruction MOdel";

    public static final String DESCRIPTION = "The COst COnstruction MOdel is a simple way "
	    + "to estimate the construction costs of a "
	    + "software project by couting the physical lines of code.";

    public static final List<QualityCharacteristic> EVALUATED_QUALITY_CHARACTERISTICS = new Vector<QualityCharacteristic>();

    private final FileStore fileStore = FileStoreFactory.getInstance();
    private CoCoMoValueSet cocomoValues;
    private final Hashtable<AnalyzedFile, CoCoMoValueSet> fileCoCoMoValues = new Hashtable<AnalyzedFile, CoCoMoValueSet>();

    public CoCoMo(AnalysisRun analysisRun) {
	super(NAME, DESCRIPTION, analysisRun);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    List<AnalyzedFile> files = getAnalysisRun().getAnalyzedFiles();
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
	    cocomoValues = new CoCoMoValueSet();
	    cocomoValues.setSloc(sloc);
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (InterruptedException e) {
	    logger.error("CoCoMo evaluation was interrupted!", e);
	    return new Status(Status.ERROR, CoCoMo.class.getName(),
		    "CoCoMo evaluation was interrupted!", e);
	}
    }

    private int getFileSLOC(AnalyzedFile file) throws InterruptedException {
	try {
	    int sloc = scanFile(file);
	    CoCoMoValueSet valueSet = new CoCoMoValueSet();
	    valueSet.setSloc(sloc);
	    valueSet.setComplexity(cocomoValues.getComplexity());
	    valueSet.setAverageSalary(cocomoValues.getAverageSalary(),
		    cocomoValues.getCurrency());
	    fileCoCoMoValues.put(file, valueSet);
	    return sloc;
	} catch (FileStoreException e) {
	    logger.error(e.getMessage(), e);
	    logger.error("Process with next file...");
	    return 0;
	}
    }

    private int scanFile(AnalyzedFile file) throws FileStoreException,
	    InterruptedException {
	FileAnalysis analysis = fileStore.loadAnalysis(file.getHashId());
	ParserTree parserTree = analysis.getParserTree();
	SLOCMetricCalculator metric = new SLOCMetricCalculator(
		getAnalysisRun(), ProgrammingLanguages.findByName(
			analysis.getLanguageName(),
			analysis.getLanguageVersion()), new CodeRange("",
			CodeRangeType.FILE, parserTree));
	metric.schedule();
	metric.join();
	int sloc = metric.getSLOCResult().getProLOC();
	return sloc;
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
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return EVALUATED_QUALITY_CHARACTERISTICS;
    }

    @Override
    public CoCoMoEvaluatorResults getResults() {
	return null;
    }

    @Override
    protected void processFile(FileAnalysis analysis) {
	// TODO Auto-generated method stub
    }

    @Override
    protected void processDirectory(HashIdFileTree directory)
	    throws InterruptedException {
	// TODO Auto-generated method stub
    }

    @Override
    protected void processProject() throws InterruptedException {
	// TODO Auto-generated method stub
    }

}
