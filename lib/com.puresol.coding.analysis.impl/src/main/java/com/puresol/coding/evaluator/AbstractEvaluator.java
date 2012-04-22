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

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorInformation;
import com.puresol.coding.evaluation.api.Result;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.HashId;

/**
 * This interface is the main interface for all evaluators and the general
 * behavior of them. All evaluators should have a name and a description as well
 * as some standard output like the report, the quality level and the evaluated
 * quality characteristics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator extends Job implements Serializable,
	Evaluator {

    private static final long serialVersionUID = -497819792461488182L;

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractEvaluator.class);

    private final Map<String, SourceCodeQuality> qualities = new HashMap<String, SourceCodeQuality>();
    private SourceCodeQuality projectQuality = SourceCodeQuality.UNSPECIFIED;

    private final AnalysisRun analysisRun;
    private final EvaluatorInformation information;
    private final Date timeStamp;
    private long timeOfRun;

    public AbstractEvaluator(EvaluatorInformation information, AnalysisRun analysisRun) {
	super(information.getName());
	this.information = information;
	this.analysisRun = analysisRun;
	timeStamp = new Date();
    }

    @Override
    public final EvaluatorInformation getInformation() {
	return information;
    }

    @Override
    public final AnalysisRun getAnalysisRun() {
	return analysisRun;
    }

    @Override
    public final Date getTimeStamp() {
	return timeStamp;
    }

    @Override
    public long getTimeOfRun() {
	return timeOfRun;
    }

    @Override
    public List<Result> getResults() {
	return new ArrayList<Result>();
    }

    @Override
    public SourceCodeQuality getQuality() {
	return projectQuality;
    }

    /**
     * This method is used to run an evaluation of an analyzed file. This method
     * is called by the run method.
     * 
     * @param file
     * @return
     * @throws IOException
     * @throws FileStoreException
     */
    abstract protected Map<String, SourceCodeQuality> processFile(
	    AnalyzedFile file) throws IOException, FileStoreException;

    @Override
    public Object getFileEvaluation(Analysis analysis, AnalysisRun analysisRun,
	    HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Object getDirectoryEvaluation(Analysis analysis,
	    AnalysisRun analysisRun, HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Object getProjectEvaluation(Analysis analysis,
	    AnalysisRun analysisRun, HashId hashId) {
	// TODO Auto-generated method stub
	return null;
    }

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
