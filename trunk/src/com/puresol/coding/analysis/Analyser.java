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
import java.util.List;

import com.puresol.coding.ProgrammingLanguage;

/**
 * This is a interface to a analyser. It's used to implement a language
 * independent way to access a single analyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analyser extends Serializable {

	/**
	 * Returns the language of the file analysed.
	 * 
	 * @return The language is returned.
	 */
	public ProgrammingLanguage getLanguage();

	/**
	 * The file which was analysed is returned.
	 * 
	 * @return The file is returned.
	 */
	public File getFile();

	/**
	 * This method returns all code ranges analysed within the analysed file.
	 * 
	 * @return
	 */
	public CodeRange getRootCodeRange();

	public SymbolTable getSymbols();

	public List<CodeRange> getNamedCodeRanges();
}
