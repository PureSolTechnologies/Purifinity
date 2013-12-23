package com.puresoltechnologies.purifinity.analysis.api;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * This is the central interface for a analysis project. This analyzer handles a
 * whole software project. It is responsible for storing and loading the
 * different analysis runs.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisProject extends Serializable {

	/**
	 * This method returns detailed information about the analysis.
	 * 
	 * @return An {@link AnalysisProjectInformation} object is returned
	 *         containing the information.
	 */
	public AnalysisProjectInformation getInformation();

	/**
	 * This method returns all relevant meta information for the analysis for
	 * user selection.
	 * 
	 * @return A {@link AnalysisRunInformation} object is returned containing
	 *         the information.
	 * @throws AnalysisStoreException
	 *             is thrown in cases of issues.
	 */
	public List<AnalysisRunInformation> getAllRunInformation()
			throws AnalysisProjectException;

	/**
	 * This method returns the settings of the analysis.
	 * 
	 * @return A {@link AnalysisProjectSettings} object is returned containing
	 *         the settings.
	 */
	public AnalysisProjectSettings getSettings();

	/**
	 * This method updates the settings of the analysis.
	 * 
	 * @param settings
	 *            is a {@link AnalysisProjectSettings} object containing the
	 *            settings.
	 * @throws AnalysisStoreException
	 *             is thrown in cases of issues.
	 */
	public void updateSettings(AnalysisProjectSettings settings)
			throws AnalysisProjectException;

	/**
	 * This method returns the analysis run defined by the uuid.
	 * 
	 * @param uuid
	 *            is a {@link UUID} object containing the UUID for the run to
	 *            load.
	 * @return An {@link AnalysisRun} object is returned containing the analysis
	 *         run.
	 * @throws AnalysisStoreException
	 *             is thrown in cases of issues.
	 */
	public AnalysisRun loadAnalysisRun(UUID uuid)
			throws AnalysisProjectException;

	/**
	 * A new analysis is run with this method. After the run a new
	 * {@link AnalysisRun} object is created and returned.
	 * 
	 * ATTENTION(!): This method work synchronously! The method only returns
	 * after the analysis is finished or interrupted!
	 * 
	 * @return A {@link AnalysisRunner} object is returned containing the
	 *         information and results about the run.
	 * @throws AnalysisStoreException
	 *             is thrown in cases of issues.
	 * @throws InterruptedException
	 */
	public AnalysisRunner createAnalysisRunner();

	/**
	 * This method loads the last analysis run available. It is the most
	 * up-to-date analysis.
	 * 
	 * @return An {@link AnalysisRun} is returned.
	 * @throws AnalysisStoreException
	 *             is thrown in cases of issues.
	 */
	public AnalysisRun loadLastAnalysisRun() throws AnalysisProjectException;

	/**
	 * This method deletes an analysis run from the store.
	 * 
	 * @param uuid
	 *            is the uuid of the run to be deleted.
	 * @throws AnalysisStoreException
	 *             is thrown in events of issues.
	 */
	public void removeAnalysisRun(UUID uuid) throws AnalysisProjectException;
}
