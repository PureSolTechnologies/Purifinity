package com.puresol.coding.analysis.api;

import java.util.List;
import java.util.UUID;

/**
 * This is the central interface to access the delivered analysis store. The
 * analysis store might be an implementation of a simple file system base store,
 * a database implementation or an implementation of a whole Jave EE enterprise
 * solution.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisStore {

    /**
     * This method returns a list of all available analysis.
     * 
     * @return A {@link List} of {@link AnalysisInformation} is returned
     *         containing all available analysis.
     */
    public List<AnalysisInformation> getAllAnalysisInformation();

    /**
     * This method is used to load a single Analysis by {@link UUID}.
     * 
     * @param uuid
     *            is the {@link UUID} of the analysis to be loaded.
     * @return An {@link Analysis} is returned which was loaded.
     */
    public Analysis loadAnalysis(UUID uuid);

    /**
     * This method creates a new Analysis which is specified by
     * {@link AnalysisSettings}.
     * 
     * @param settings
     *            are the settings to be used for a new analysis.
     * @return An {@link Analysis} is returned containing the analysis created
     *         before.
     */
    public Analysis createAnalysis(AnalysisSettings settings);
}
