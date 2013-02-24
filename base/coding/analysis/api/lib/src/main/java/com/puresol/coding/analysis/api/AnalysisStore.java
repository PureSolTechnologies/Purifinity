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
     * This method is used to load a single Analysis by {@link UUID}.
     * 
     * @param uuid
     *            is the {@link UUID} of the analysis to be loaded.
     * @return An {@link AnalysisProject} is returned which was loaded.
     * @throws AnalysisStoreException
     *             is thrown for unexpected issues.
     */
    public AnalysisProject loadAnalysis(UUID uuid)
	    throws AnalysisStoreException;

    /**
     * This method creates a new Analysis which is specified by
     * {@link AnalysisProjectSettings}.
     * 
     * @param settings
     *            are the settings to be used for a new analysis.
     * @return An {@link AnalysisProject} is returned containing the analysis
     *         created before.
     * @throws AnalysisStoreException
     *             is thrown for unexpected issues.
     */
    public AnalysisProject createAnalysis(AnalysisProjectSettings settings)
	    throws AnalysisStoreException;

    /**
     * This method deletes an analysis from the store.
     * 
     * @param uuid
     *            is the identifier of the analysis to be deleted.
     * @throws AnalysisStoreException
     *             is thrown in cases of issues.
     */
    public void removeAnalysis(UUID uuid) throws AnalysisStoreException;

    /**
     * This method returns a list of all {@link AnalysisProject}s. Additionally
     * to {@link #getAllAnalysisInformation()}, other information is included
     * like repository location.
     * 
     * @throws AnalysisStoreException
     *             is thrown in cases of issues.
     * 
     * @return A {@link List} of {@link AnalysisProject} is returned.
     */
    public List<AnalysisProject> getAnalysisProjects()
	    throws AnalysisStoreException;
}
