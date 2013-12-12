package com.puresoltechnologies.purifinity.coding.analysis.impl;

import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;

public abstract class AbstractCodeAnalyzer implements CodeAnalyzer {

	private final CodeLocation sourceCodeLocation;
	private final transient AbstractLanguageGrammar grammar;

	public AbstractCodeAnalyzer(CodeLocation sourceCodeLocation,
			AbstractLanguageGrammar grammar) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.grammar = grammar;
	}

	@Override
	public final CodeLocation getSource() {
		return sourceCodeLocation;
	}

	public AbstractLanguageGrammar getGrammar() {
		return grammar;
	}
}
