package com.puresol.coding.evaluator;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.swingx.progress.ProgressObservable;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;
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
public interface Evaluator extends ProgressObservable, Serializable {

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
	public String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException;

	public ProjectAnalyzer getProjectAnalyser();

	/**
	 * This method returns a complete list of files evaluated by the evaluator.
	 * 
	 * @return An ArrayList of File is returned containing these files.
	 */
	public List<File> getFiles();

	/**
	 * This method returns a complete list of files evaluated by the evaluator.
	 * 
	 * @return An ArrayList of File is returned containing these files.
	 */
	public List<CodeRange> getCodeRanges(File file);

	/**
	 * Here a comment as String is returned for the evaluation result of the
	 * whole project.
	 * 
	 * @return A String with the comment is returned.
	 */
	public String getProjectComment(ReportingFormat format)
			throws UnsupportedReportingFormatException;

	/**
	 * This method returns the evaluator comment for the file.
	 * 
	 * @param codeRange
	 *            is the file to be checked for a comment.
	 * @return A String with the comment is returned.
	 */
	public String getCodeRangeComment(CodeRange codeRange,
			ReportingFormat format) throws UnsupportedReportingFormatException;

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
	public QualityLevel getQuality(CodeRange codeRange);
}
