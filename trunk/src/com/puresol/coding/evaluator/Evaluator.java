package com.puresol.coding.evaluator;

import java.io.File;
import java.util.ArrayList;

import javax.swingx.progress.ProgressObservable;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.reporting.ReportingFormat;

/**
 * This interface is the standard interface for all project evaluators used
 * within coding analysis.
 * 
 * Additionally to this fixed interface, Evaluator might have some standardized
 * static methods:
 * 
 * - public static String name();
 * 
 * - public static String description();
 * 
 * - public static ArrayList<Property> supportedProperties();
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Evaluator extends ProgressObservable {

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

	public ProjectAnalyser getProjectAnalyser();

	/**
	 * This method returns a complete list of files evaluated by the evaluator.
	 * 
	 * @return An ArrayList of File is returned containing these files.
	 */
	public ArrayList<File> getFiles();

	/**
	 * This method returns a complete list of files evaluated by the evaluator.
	 * 
	 * @return An ArrayList of File is returned containing these files.
	 */
	public ArrayList<CodeRange> getCodeRanges(File file);

	/**
	 * Here a comment as String is returned for the evaluation result of the
	 * whole project.
	 * 
	 * @return A String with the comment is returned.
	 */
	public String getProjectComment(ReportingFormat format);

	/**
	 * This method returns the evaluator comment for the file.
	 * 
	 * @param file
	 *            is the file to be checked for a comment.
	 * @return A String with the comment is returned.
	 */
	public String getFileComment(File file, ReportingFormat format);

	/**
	 * This method returns the evaluator comment for the file.
	 * 
	 * @param codeRange
	 *            is the file to be checked for a comment.
	 * @return A String with the comment is returned.
	 */
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format);

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

	/**
	 * This method returns the single quality statement for a single file.
	 * 
	 * @param file
	 * @return
	 */
	public QualityLevel getQuality(CodeRange codeRange);
}
