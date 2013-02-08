package com.puresol.coding.analysis.api;

import java.io.File;
import java.io.IOException;

import com.puresol.uhura.source.CodeLocation;

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
	 * This method returns the version of the programming language.
	 * 
	 * @return
	 */
	public String getVersion();

	/**
	 * This method specifies whether the programming language is suitable for a
	 * specified file or not.
	 * 
	 * @return
	 */
	public boolean isSuitable(CodeLocation source);

	/**
	 * This method returns the grammar of the programming language.
	 * 
	 * @return
	 */
	public LanguageGrammar getGrammar();

	/**
	 * This method is a factory method for analyser objects for the programming
	 * language for a specified file within a specified project directory.
	 * 
	 * @param source
	 * @return
	 */
	public CodeAnalyzer createAnalyser(CodeLocation source);

	public CodeAnalyzer restoreAnalyzer(File file) throws IOException;

	/**
	 * This is a current workaround to get language specifics working for
	 * metrics and evaluators.
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> T getImplementation(Class<T> clazz);

}
