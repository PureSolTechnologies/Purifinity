package com.puresol.coding;

import java.io.File;
import java.util.List;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.PersistenceException;

/**
 * This interface is the central interface for a programming language
 * specification.
 * 
 * Attention! Programming languages are singletons and therefore, it can not be
 * serialized!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgrammingLanguage { // do not Serialize!

	/**
	 * This method returns the name of the programming language.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method specifies whether the programming language is object oriented
	 * or not.
	 * 
	 * @return
	 */
	public boolean isObjectOriented();

	/**
	 * This method specifies whether the programming language is suitable for a
	 * specified file or not.
	 * 
	 * @return
	 */
	public boolean isSuitable(File file);

	/**
	 * This method is a factory method for analyser objects for the programming
	 * language for a specified file within a specified project directory.
	 * 
	 * @param directory
	 * @param file
	 * @return
	 */
	public Analyzer createAnalyser(File file);

	public Analyzer restoreAnalyzer(File file) throws PersistenceException;

	public List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree);

	public <T> T getImplementation(Class<T> clazz);
}
