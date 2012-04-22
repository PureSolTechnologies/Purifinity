package com.puresol.coding.evaluation.api;

import java.util.Date;
import java.util.List;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.HashId;

/**
 * This is the central interface for an evaluator. An evaluator is responsible
 * to evaluate files, directories (summary of all files in directory) and the
 * project as a whole.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator {

    /**
     * This method returns the evaluator meta data which describes the evaluator
     * in more detail.
     * 
     * @return
     */
    public EvaluatorInformation getInformation();

    /**
     * This method returns the name of the time stamp of the evaluation. This is
     * used to track the need for a re-evaluation.
     * 
     * @return
     */
    public Date getTimeStamp();

    /**
     * This method returns the time effort which was needed for analysis.
     * 
     * @return Returns the time effort in milliseconds.
     */
    public long getTimeOfRun();

    /**
     * This method returns the analysis run which is the foundation of the
     * evaluation run.
     * 
     * @return
     */
    public AnalysisRun getAnalysisRun();

    /**
     * This method returns a list of results from the evaluator. This values are
     * used for creating a report and for storing and tracking them over a
     * longer time (SPC).
     * 
     * @return
     */
    public List<Result> getResults();

    /**
     * This method returns the quality level after an evalutation was performed.
     * 
     * @return
     */
    public SourceCodeQuality getQuality();

    /**
     * This method returns a list with quality characteristics which might be
     * evaluated by the evaluator.
     * 
     * @return
     */
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    /**
     * This method starts the acutal evaluation.
     * 
     * @throws InterruptedException
     */
    public void runEvaluation() throws InterruptedException;

    public Object getFileEvaluation(Analysis analysis, AnalysisRun analysisRun,
	    HashId hashId);

    public Object getDirectoryEvaluation(Analysis analysis,
	    AnalysisRun analysisRun, HashId hashId);

    public Object getProjectEvaluation(Analysis analysis,
	    AnalysisRun analysisRun, HashId hashId);

}
