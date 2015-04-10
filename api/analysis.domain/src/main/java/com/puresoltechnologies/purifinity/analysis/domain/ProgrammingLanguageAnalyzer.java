package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.IOException;

import com.puresoltechnologies.commons.domain.Configurable;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

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
