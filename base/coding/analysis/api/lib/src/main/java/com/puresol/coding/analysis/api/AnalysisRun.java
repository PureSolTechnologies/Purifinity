package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

import com.puresol.trees.FileTree;
import com.puresol.utils.progress.ProgressObservable;

/**
 * This is the general interface for a single project analysis. This analysis
 * represents one project for a single snap shot. The several analysis runs are
 * represented by the ProjectAnalyzer were the different snapshots and the
 * history can be retrieved.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisRun extends Serializable,
		ProgressObservable<AnalysisRun>, Callable<Boolean> {

	/**
	 * This method returns the information about the run.
	 * 
	 * @return An {@link AnalysisRunInformation} object is returned containing
	 *         detailed information about the run.
	 */
	public AnalysisRunInformation getInformation();

	/**
	 * This method returns all analyzed file as a simple list.
	 * 
	 * @return A {@link List} is returned containing the files as
	 *         {@link AnalyzedCode}.
	 */
	public List<AnalyzedCode> getAnalyzedCodes();

	/**
	 * This method returns the whole source code tree as a {@link FileTree}.
	 * This tree can be used for user output.
	 * 
	 * @return A {@link FileTree} object is returned.
	 */
	public HashIdFileTree getFileTree();

	/**
	 * This method returns a list of all files which failed an analysis as flat
	 * list.
	 * 
	 * @return A {@link List} of {@link File} is returned.
	 */
	public List<AnalyzedCode> getFailedCodes();

	/**
	 * This method performs a lookup of the file provided and find the
	 * corresponding analyzed file. The file is relative to the original project
	 * directory and is for example coming out of the file tree (without the
	 * parent element!).
	 * 
	 * @param internalPath
	 *            is the relative path of a file within the original project to
	 *            lookup.
	 * @return {@link AnalyzedCode} is returned for file. If no analyzed file
	 *         was found, null is returned.
	 */
	public AnalyzedCode findAnalyzedCode(String internalPath);

}