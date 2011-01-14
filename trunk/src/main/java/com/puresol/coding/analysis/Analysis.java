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
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.uhura.ast.ParserTree;

/**
 * This is a interface to a single analysis. It's used to implement a language
 * independent way to access a single file analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analysis extends Serializable {

	/**
	 * This method returns the time stamp of the analysis. This can be used for
	 * validity analysis by time stamp comparison for evaluators.
	 * 
	 * @return
	 * @throws IOException
	 */
	public Date getTimeStamp() throws IOException;

	/**
	 * Returns the language of the file analysed.
	 * 
	 * @return The language is returned.
	 * @throws IOException
	 */
	public ProgrammingLanguage getLanguage() throws IOException;

	/**
	 * The file which was analyzed is returned.
	 * 
	 * @return The file is returned.
	 */
	public File getFile();

	public ParserTree getParserTree() throws IOException;

	public List<CodeRange> getAnalyzableCodeRanges() throws IOException;
}
