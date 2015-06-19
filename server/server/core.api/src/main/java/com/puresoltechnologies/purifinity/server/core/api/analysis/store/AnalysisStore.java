package com.puresoltechnologies.purifinity.server.core.api.analysis.store;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;

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
	 * @param projectId
	 *            is the identifier {@link String} of the project.
	 * @param settings
	 *            are the settings to be used for a new analysis.
	 * @return An {@link AnalysisProject} is returned containing the analysis
	 *         created before.
	 * @throws AnalysisStoreException
	 *             is thrown for unexpected issues.
	 */
	public AnalysisProjectInformation createAnalysisProject(String projectId,
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
	 * @param projectId
	 *            is the identifier {@link String} of the analysis to be loaded.
	 * @return An {@link AnalysisProject} is returned which was loaded.
	 * @throws AnalysisStoreException
	 *             is thrown for unexpected issues.
	 */
	public AnalysisProjectInformation readAnalysisProjectInformation(
			String projectId) throws AnalysisStoreException;

	public AnalysisProject readAnalysisProject(String projectId)
			throws AnalysisStoreException;

	/**
	 * This method deletes an analysis from the store.
	 * 
	 * @param projectId
	 *            is the identifier of the analysis to be deleted.
	 * @throws AnalysisStoreException
	 *             is thrown in cases of issues.
	 */
	public void removeAnalysisProject(String projectId)
			throws AnalysisStoreException;

	/**
	 * Reads the default search configuration for the analysis project.
	 * 
	 * @param projectId
	 * @return
	 * @throws AnalysisStoreException
	 */
	public AnalysisProjectSettings readAnalysisProjectSettings(String projectId)
			throws AnalysisStoreException;

	public void updateAnalysisProjectSettings(String projectId,
			AnalysisProjectSettings settings) throws AnalysisStoreException;

	public List<AnalysisRunInformation> readAllRunInformation(String projectId)
			throws AnalysisStoreException;

	public AnalysisRunInformation readAnalysisRunInformation(String projectId,
			long runId) throws AnalysisStoreException;

	public AnalysisRun readAnalysisRun(AnalysisRunInformation information)
			throws AnalysisStoreException;

	public AnalysisRunInformation readLastAnalysisRun(String projectId)
			throws AnalysisStoreException;

	public void removeAnalysisRun(String projectId, long runId)
			throws AnalysisStoreException;

	/**
	 * Reads the search configuration which was applied for the analysis run
	 * specified by its UUIDs.
	 * 
	 * @param projectId
	 * @param runId
	 * @return
	 * @throws AnalysisStoreException
	 */
	public FileSearchConfiguration readSearchConfiguration(String projectId,
			long runId) throws AnalysisStoreException;

	public AnalysisFileTree readAnalysisFileTree(String projectId, long runId)
			throws AnalysisStoreException;

	public AnalysisRunInformation createAnalysisRun(String projectId,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreException;

}
