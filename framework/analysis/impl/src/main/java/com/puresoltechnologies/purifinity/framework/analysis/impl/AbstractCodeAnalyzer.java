package com.puresoltechnologies.purifinity.framework.analysis.impl;

import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;

public abstract class AbstractCodeAnalyzer implements CodeAnalyzer {

	private final SourceCodeLocation sourceCodeLocation;
	private final transient AbstractLanguageGrammar grammar;

	public AbstractCodeAnalyzer(SourceCodeLocation sourceCodeLocation,
			AbstractLanguageGrammar grammar) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.grammar = grammar;
	}

	@Override
	public final SourceCodeLocation getSource() {
		return sourceCodeLocation;
	}

	public AbstractLanguageGrammar getGrammar() {
		return grammar;
	}
}
