/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.puresol.coding.ProgrammingLanguage;

/**
 * This is a interface to a analyser. It's used to implement a language
 * independent way to access a single analyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analyzer extends Serializable {

	/**
	 * This method returns the time stamp of the analysis. This can be used for
	 * validity analysis by time stamp comparison for evaluators.
	 * 
	 * @return
	 */
	public Date getTimeStamp();

	/**
	 * Returns the language of the file analysed.
	 * 
	 * @return The language is returned.
	 */
	public ProgrammingLanguage getLanguage();

	/**
	 * The file which was analyzed is returned.
	 * 
	 * @return The file is returned.
	 */
	public File getFile();

	public void parse() throws AnalyserException;

	/**
	 * This method persists the analyzer into a file specified.
	 * 
	 * @param file
	 * @return
	 */
	boolean persist(File file);
}
