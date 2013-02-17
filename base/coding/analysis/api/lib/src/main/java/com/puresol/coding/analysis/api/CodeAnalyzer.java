package com.puresol.coding.analysis.api;

/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

import java.io.File;
import java.io.IOException;

import com.puresol.coding.lang.api.ProgrammingLanguage;
import com.puresol.uhura.source.CodeLocation;

/**
 * This is a interface to a analyzer. It's used to implement a language
 * independent way to access a single analyzer.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeAnalyzer {

	/**
	 * This method returns the source which is to be analyzed.
	 * 
	 * @return
	 */
	public CodeLocation getSource();

	/**
	 * This method returns from the implemented parser the supported programming
	 * language.
	 * 
	 * @return
	 */
	public ProgrammingLanguage getLanguage();

	/**
	 * This method is called to start the actual parsing process.
	 * 
	 * @throws AnalyzerException
	 */
	public void analyze() throws AnalyzerException, IOException;

	/**
	 * This method persists the analyzer into a file specified.
	 * 
	 * @param file
	 * @return
	 */
	boolean persist(File file);

	/**
	 * This method returns the analysis of the parsing of process.
	 * 
	 * @return
	 */
	public CodeAnalysis getAnalysis();
}