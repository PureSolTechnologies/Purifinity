package com.puresol.coding.analysis.api;

import java.util.List;
import java.util.UUID;

/**
 * This is the central interface for a project analyzer. This analyzer handles a
 * whole software project. It is responsible for storing and loading the
 * different analysis runs.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analysis extends AnalysisInformation {

    /**
     * This method returns all relevant meta information for the analysis for
     * user selection.
     * 
     * @return A {@link AnalysisRunInformation} object is returned containing
     *         the information.
     */
    public List<AnalysisRunInformation> getAllRunInformation();

    /**
     * This method returns the settings of the analysis.
     * 
     * @return A {@link AnalysisSettings} object is returned containing the
     *         settings.
     */
    public AnalysisSettings getSettings();

    /**
     * This method updates the settings of the analysis.
     * 
     * @param settings
     *            is a {@link AnalysisSettings} object containing the settings.
     */
    public void updateSettings(AnalysisSettings settings);

    /**
     * This method returns the analysis run defined by the uuid.
     * 
     * @param uuid
     *            is a {@link UUID} object containing the UUID for the run to
     *            load.
     * @return An {@link AnalysisRun} object is returned containing the analysis
     *         run.
     */
    public AnalysisRun loadAnalysisRun(UUID uuid);

    /**
     * A new analysis is run with this method. After the run a new
     * {@link AnalysisRun} object is created and returned.
     * 
     * @return A {@link AnalysisRun} object is returned containing the
     *         information and results about the run.
     */
    public AnalysisRun runAnalysis();

    /**
     * This method loads the last analysis run available. It is the most
     * up-to-date analysis.
     * 
     * @return An {@link AnalysisRun} is returned.
     */
    public AnalysisRun loadLastAnalysisRun();
}
