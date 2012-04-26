package com.puresol.coding.evaluation.api;

import java.io.Serializable;

import com.puresol.coding.analysis.api.TimeAwareness;
import com.puresol.utils.HashId;

/**
 * This is the central interface for evaluator results. Each evaluator
 * implements an own implementation. This interface just binds this results to
 * the evaluator and provides common functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluatorResults extends Serializable, TimeAwareness {

    /**
     * This method returns the name of the evaluator which was used to generate
     * the results.
     * 
     * @return
     */
    public String getEvaluatorName();

    /**
     * This method looks the result for a single file up and returns it.
     * 
     * @param hashId
     * @return
     */
    public FileResult getFileResult(HashId hashId);

    /**
     * This method looks for the result of a directory and returns the result
     * for it.
     * 
     * @param hashId
     * @return
     */
    public DirectoryResult getDirectoryResult(HashId hashId);

    /**
     * This method returns the project results.
     * 
     * @return
     */
    public ProjectResult getProjectResult();

}
