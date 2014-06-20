package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;

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
    public AnalysisProjectInformation createAnalysisProject(
	    AnalysisProjectSettings settings) throws AnalysisStoreException;

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
    public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
	    throws AnalysisStoreException;

    public List<AnalysisProject> readAllAnalysisProjects()
	    throws AnalysisStoreException;

    /**
     * This method is used to load a single Analysis by {@link UUID}.
     * 
     * @param projectUUID
     *            is the {@link UUID} of the analysis to be loaded.
     * @return An {@link AnalysisProject} is returned which was loaded.
     * @throws AnalysisStoreException
     *             is thrown for unexpected issues.
     */
    public AnalysisProjectInformation readAnalysisProjectInformation(
	    UUID projectUUID) throws AnalysisStoreException;

    public AnalysisProject readAnalysisProject(UUID projectUUID)
	    throws AnalysisStoreException;

    /**
     * This method deletes an analysis from the store.
     * 
     * @param projectUUID
     *            is the identifier of the analysis to be deleted.
     * @throws AnalysisStoreException
     *             is thrown in cases of issues.
     */
    public void removeAnalysisProject(UUID projectUUID)
	    throws AnalysisStoreException;

    /**
     * Reads the default search configuration for the analysis project.
     * 
     * @param analysisProjectUUID
     * @return
     * @throws AnalysisStoreException
     */
    public AnalysisProjectSettings readAnalysisProjectSettings(
	    UUID analysisProjectUUID) throws AnalysisStoreException;

    public void updateAnalysisProjectSettings(UUID projectUUID,
	    AnalysisProjectSettings settings) throws AnalysisStoreException;

    public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
	    throws AnalysisStoreException;

    public AnalysisRunInformation readAnalysisRunInformation(UUID projectUUID,
	    UUID analysisRunUUID) throws AnalysisStoreException;

    public AnalysisRun readAnalysisRun(AnalysisRunInformation information)
	    throws AnalysisStoreException;

    public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
	    throws AnalysisStoreException;

    public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID)
	    throws AnalysisStoreException;

    /**
     * Reads the search configuration which was applied for the analysis run
     * specified by its UUIDs.
     * 
     * @param analysisProjectUUID
     * @return
     * @throws AnalysisStoreException
     */
    public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID)
	    throws AnalysisStoreException;

    public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
	    throws AnalysisStoreException;

    public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
	    Date startTime, long duration, String description,
	    FileSearchConfiguration fileSearchConfiguration)
	    throws AnalysisStoreException;

}
