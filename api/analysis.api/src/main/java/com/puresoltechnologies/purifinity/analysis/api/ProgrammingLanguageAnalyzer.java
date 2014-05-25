package com.puresoltechnologies.purifinity.analysis.api;

import java.io.IOException;

import com.puresoltechnologies.commons.misc.Configurable;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;

/**
 * This interface is implemented
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgrammingLanguageAnalyzer extends ProgrammingLanguage,
		AnalyzerStore, Configurable {

	public CodeAnalysis analyze(SourceCodeLocation sourceCodeLocation)
			throws AnalyzerException, IOException;

}
