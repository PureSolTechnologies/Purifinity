package com.puresol.coding.analysis.evaluator;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.analysis.QualityLevel;

/**
 * This interface is the standard interface for all project evaluators used
 * within coding analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator {

	/**
	 * This is the name of the evaluator. This is used for section heads in
	 * reports and as name for references.
	 * 
	 * @return A string with the name is returned.
	 */
	public String getName();

	/**
	 * Here a complete description text is returned to explain the test and its
	 * purpose.
	 * 
	 * @return A string with the text is returned.
	 */
	public String getDescription();

	/**
	 * This method returns a complete list of files evaluated by the evaluator.
	 * 
	 * @return An ArrayList of File is returned containing these files.
	 */
	public ArrayList<File> getFiles();

	/**
	 * Here a comment as String is returned for the evaluation result of the
	 * whole project.
	 * 
	 * @return A String with the comment is returned.
	 */
	public String getProjectEvaluationComment();

	/**
	 * This method returns the evaluator comment for the file.
	 * 
	 * @param file
	 *            is the file to be checked for a comment.
	 * @return A String with the comment is returned.
	 */
	public String getFileEvaluationComment(File file);

	/**
	 * This method returns the overall project quality.
	 * 
	 * @return
	 */
	public QualityLevel getProjectQuality();

	/**
	 * This method returns the single quality statement for a single file.
	 * 
	 * @param file
	 * @return
	 */
	public QualityLevel getQuality(File file);
}
