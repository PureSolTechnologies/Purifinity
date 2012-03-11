/***************************************************************************
 *
 *   ProjectAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;

/**
 * This class is for analyzing a whole project. All source code found is checked
 * for a known language and parsed to CodeRange objects. These CodeRange objects
 * can be used for Evaluator and Metric calculations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectAnalyzerFactory {

    /**
     * This method creates a new project analyzer with a new workspace
     * associated.
     * 
     * @param projectDirectory
     *            is the directory which is to scan for files and to be
     *            analyzed.
     * @param workspaceDirectory
     *            is the directory to put the persisted results to.
     */
    public static ProjectAnalyzer create(File projectDirectory,
	    File workspaceDirectory) {
	ProjectAnalyzer projectAnalyser = new ProjectAnalyzer(
		workspaceDirectory);
	if (!projectAnalyser.createProjectDirectory(projectDirectory)) {
	    return null;
	}
	return projectAnalyser;
    }

    /**
     * This method opens an existing project analyzer via its workspace
     * directory.
     * 
     * @param workspaceDirectory
     *            is the directory where the persisted results can be found.
     * @return
     */
    public static ProjectAnalyzer open(File workspaceDirectory) {
	ProjectAnalyzer projectAnalyser = new ProjectAnalyzer(
		workspaceDirectory);
	if (!projectAnalyser.openAndReadSettings()) {
	    return null;
	}
	return projectAnalyser;
    }

}
