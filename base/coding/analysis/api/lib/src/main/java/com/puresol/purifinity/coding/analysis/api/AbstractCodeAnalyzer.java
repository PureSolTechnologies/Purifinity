package com.puresol.purifinity.coding.analysis.api;

import com.puresol.purifinity.coding.lang.commons.AbstractLanguageGrammar;
import com.puresol.purifinity.uhura.source.CodeLocation;

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
