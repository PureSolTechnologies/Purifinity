package com.puresoltechnologies.purifinity.analysis.api;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;

/**
 * This is the general interface for a single project analysis. This analysis
 * represents one project for a single snap shot. The several analysis runs are
 * represented by the ProjectAnalyzer were the different snapshots and the
 * history can be retrieved.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisRun extends Serializable {

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
	 *         {@link AnalysisInformation}.
	 */
	public List<AnalysisInformation> getAnalyzedFiles();

	/**
	 * This method returns the whole source code tree as a {@link FileTree}.
	 * This tree can be used for user output.
	 * 
	 * @return A {@link AnalysisFileTree} object is returned.
	 */
	public AnalysisFileTree getFileTree();

	/**
	 * This method returns a list of all files which failed an analysis as flat
	 * list.
	 * 
	 * @return A {@link List} of {@link File} is returned.
	 */
	public List<AnalysisInformation> getFailedFiles();

	/**
	 * This method performs a lookup of the file provided and find the
	 * corresponding analyzed file. The file is relative to the original project
	 * directory and is for example coming out of the file tree (without the
	 * parent element!).
	 * 
	 * @param internalPath
	 *            is the relative path of a file within the original project to
	 *            lookup.
	 * @return {@link AnalysisInformation} is returned for file. If no analyzed
	 *         file was found, null is returned.
	 */
	public AnalysisInformation findAnalyzedCode(String internalPath);

	/**
	 * This method looks a {@link AnalysisFileTree} node up for a given
	 * {@link HashId}.
	 * 
	 * @param hashId
	 *            is the hash id if the node to be found.
	 * @return An {@link AnalysisFileTree} object is returned pointing to the
	 *         correct node inte analysis tree.
	 */
	public AnalysisFileTree findTreeNode(HashId hashId);

}