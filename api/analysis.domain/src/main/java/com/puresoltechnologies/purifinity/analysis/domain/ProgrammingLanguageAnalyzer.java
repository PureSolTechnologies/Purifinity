package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.IOException;

import com.puresoltechnologies.commons.domain.Configurable;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;

/**
 * This interface is implemented
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgrammingLanguageAnalyzer extends ProgrammingLanguage,
		AnalyzerStore, Configurable {

	public CodeAnalysis analyze(SourceCode sourceCode, HashId hashId)
			throws IOException;

}
