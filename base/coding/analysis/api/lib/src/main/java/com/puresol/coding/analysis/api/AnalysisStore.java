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
     * @throws ModuleStoreException
     *             is thrown in cases of issues.
     */
    public List<AnalysisInformation> getAllAnalysisInformation()
	    throws ModuleStoreException;

    /**
     * This method is used to load a single Analysis by {@link UUID}.
     * 
     * @param uuid
     *            is the {@link UUID} of the analysis to be loaded.
     * @return An {@link Analysis} is returned which was loaded.
     * @throws ModuleStoreException
     *             is thrown for unexpected issues.
     */
    public Analysis loadAnalysis(UUID uuid) throws ModuleStoreException;

    /**
     * This method creates a new Analysis which is specified by
     * {@link AnalysisSettings}.
     * 
     * @param settings
     *            are the settings to be used for a new analysis.
     * @return An {@link Analysis} is returned containing the analysis created
     *         before.
     * @throws ModuleStoreException
     *             is thrown for unexpected issues.
     */
    public Analysis createAnalysis(AnalysisSettings settings)
	    throws ModuleStoreException;

    /**
     * This method deletes an analysis from the store.
     * 
     * @param uuid
     *            is the identifier of the analysis to be deleted.
     * @throws ModuleStoreException
     *             is thrown in cases of issues.
     */
    public void removeAnalysis(UUID uuid) throws ModuleStoreException;
}
