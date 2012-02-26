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

/**
 * This is a interface to a analyser. It's used to implement a language
 * independent way to access a single analyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analyzer extends Analysis {

	/**
	 * This method is called to start the actual parsing process.
	 * 
	 * @throws AnalyzerException
	 */
	public void parse() throws AnalyzerException;

	/**
	 * This method persists the analyzer into a file specified.
	 * 
	 * @param file
	 * @return
	 */
	boolean persist(File file);

}
