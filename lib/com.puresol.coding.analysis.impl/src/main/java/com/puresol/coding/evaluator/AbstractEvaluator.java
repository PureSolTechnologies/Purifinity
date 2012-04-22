package com.puresol.coding.evaluator;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

/**
 * This interface is the main interface for all evaluators and the general
 * behavior of them. All evaluators should have a name and a description as well
 * as some standard output like the report, the quality level and the evaluated
 * quality characteristics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator extends Job implements Serializable {

    private static final long serialVersionUID = -497819792461488182L;

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractEvaluator.class);

    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;

    private final AnalysisRun analysisRun;
    private final Date timeStamp;

    public AbstractEvaluator(AnalysisRun analysisRun, String name) {
	super(name);
	this.analysisRun = analysisRun;
	timeStamp = new Date();
    }

    public final AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

    /**
     * This method returns the name of the time stamp of the evaluation. This is
     * used to track the need for a re-evaluation.
     * 
     * @return
     */
    public final Date getTimeStamp() {
	return timeStamp;
    }

    /**
     * This method returns the description of the evaluator which might be
     * displayed in reports or within applications.
     * 
     * @return
     */
    public abstract String getDescription();

    /**
     * This method returns a list of results from the evaluator. This values are
     * used for creating a report and for storing and tracking them over a
     * longer time (SPC).
     * 
     * @return
     */
    public List<Result> getResults() {
	return new ArrayList<Result>();
    }

    /**
     * This method returns the quality level after an evalutation was performed.
     * 
     * @return
     */
    public SourceCodeQuality getQuality() {
	return projectQuality;
    }

    /**
     * This method returns a list with quality characteristics which might be
     * evaluated by the evaluator.
     * 
     * @return
     */
    public abstract List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    abstract protected Map<String, SourceCodeQuality> processFile(
	    AnalyzedFile file) throws IOException, FileStoreException;

    @Override
    public IStatus run(IProgressMonitor monitor) {
	try {
	    qualities.clear();
	    List<AnalyzedFile> files = getAnalysisRun().getAnalyzedFiles();
	    monitor.beginTask(getName(), files.size());
	    int sum = 0;
	    int count = 0;
	    int qualCount = 0;
	    Collections.sort(files);
	    for (AnalyzedFile file : files) {
		if (monitor.isCanceled()) {
		    monitor.done();
		    return Status.CANCEL_STATUS;
		}
		count++;
		monitor.worked(count);
		Map<String, SourceCodeQuality> levels;
		levels = processFile(file);
		qualities.putAll(levels);
		for (SourceCodeQuality level : levels.values()) {
		    if (level != SourceCodeQuality.UNSPECIFIED) {
			sum += level.getLevel();
			qualCount++;
		    }
		}

	    }
	    int result = (int) Math.round((double) sum / (double) qualCount);
	    projectQuality = SourceCodeQuality.fromLevel(result);
	    monitor.done();
	    return Status.OK_STATUS;
	} catch (IOException e) {
	    logger.error("Could not calculate project metric!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	} catch (FileStoreException e) {
	    logger.error("Could not calculate project metric!", e);
	    monitor.setCanceled(true);
	    monitor.done();
	    return new Status(IStatus.ERROR, getName(), e.getMessage(), e);
	}
    }

}
