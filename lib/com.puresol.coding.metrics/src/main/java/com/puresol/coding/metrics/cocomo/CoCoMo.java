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
import java.util.Map;
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
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.quality.api.QualityCharacteristic;
import com.puresol.coding.quality.api.SourceCodeQuality;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.StopWatch;

/**
 * This class calculates the CoCoMo for a set number of sloc and a given average
 * salary and complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoCoMo extends AbstractEvaluator<CoCoMoValueSet> {

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
	super(new EvaluatorInformation(NAME, DESCRIPTION), analysisRun);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    StopWatch watch = new StopWatch();
	    watch.start();
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
	    watch.stop();
	    cocomoValues = new CoCoMoValueSet(CoCoMo.NAME,
		    watch.getStartTime(), watch.getMilliseconds());
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
	    StopWatch watch = new StopWatch();
	    watch.start();
	    int sloc = scanFile(file);
	    watch.stop();
	    CoCoMoValueSet valueSet = new CoCoMoValueSet(CoCoMo.NAME,
		    watch.getStartTime(), watch.getMilliseconds());
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
	SLOCMetric metric = new SLOCMetric(getAnalysisRun(),
		ProgrammingLanguages.findByName(analysis.getLanguageName(),
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
    public CoCoMoValueSet getResults() {
	return cocomoValues;
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(FileAnalysis analysis) {
	// intentionally left blank
	return null;
    }

}
