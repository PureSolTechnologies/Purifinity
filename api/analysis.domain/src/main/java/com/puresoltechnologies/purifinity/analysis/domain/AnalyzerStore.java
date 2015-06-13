package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;

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
	 * @param sourceCode
	 *            is the {@link SourceCode} containing the source to be
	 *            analyzed.
	 * @return
	 */
	public CodeAnalyzer createAnalyser(SourceCode sourceCode, HashId hashId);

	public CodeAnalyzer restoreAnalyzer(File file) throws IOException;

}
