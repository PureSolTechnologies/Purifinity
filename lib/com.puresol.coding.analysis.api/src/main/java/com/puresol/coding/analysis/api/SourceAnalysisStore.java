package com.puresol.coding.analysis.api;


/**
 * This is the central interface for the analysis store. This interface provides
 * functionality to access the complete history of source code analysis and the
 * single analysis itself. This interface goes down the analysis via the tree
 * down to the single file content and the analysis of the content.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface SourceAnalysisStore {

    /**
     * This method adds a new analysis to the store.
     * 
     * @param dateOfAnalysis
     *            is the date and time of the analysis.
     * @param projectName
     *            is the name of the project. This is usually the name of the
     *            source project.
     * @param timeInMillisOfRun
     *            is the time how long the analysis of the whole project took in
     *            Milliseconds.
     * @return The analysis id from the store is returned.
     */
    public int addAnalysis(Analysis analysis);

    public Analysis getAnalysis(int id);

}
