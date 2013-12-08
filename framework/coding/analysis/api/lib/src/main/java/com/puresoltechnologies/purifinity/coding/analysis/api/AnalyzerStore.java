package com.puresoltechnologies.purifinity.coding.analysis.api;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parser.impl.source.CodeLocation;

/**
 * This interface is implemented by language classes which support creating and
 * restoring analyzers.
 * 
 * @author ludwig
 * 
 */
public interface AnalyzerStore {

	/**
	 * This method is a factory method for analyzer objects for the programming
	 * language for a specified file within a specified project directory.
	 * 
	 * @param source
	 * @return
	 */
	public CodeAnalyzer createAnalyser(CodeLocation source);

	public CodeAnalyzer restoreAnalyzer(File file) throws IOException;

}
