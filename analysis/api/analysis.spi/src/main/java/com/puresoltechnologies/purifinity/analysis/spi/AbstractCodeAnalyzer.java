package com.puresoltechnologies.purifinity.analysis.spi;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;

public abstract class AbstractCodeAnalyzer implements CodeAnalyzer {

	private final SourceCode sourceCode;
	private final HashId hashId;
	private final transient AbstractLanguageGrammar grammar;

	public AbstractCodeAnalyzer(SourceCode sourceCode, HashId hashId,
			AbstractLanguageGrammar grammar) {
		super();
		this.sourceCode = sourceCode;
		this.hashId = hashId;
		this.grammar = grammar;
	}

	@Override
	public final SourceCode getSourceCode() {
		return sourceCode;
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	public AbstractLanguageGrammar getGrammar() {
		return grammar;
	}
}
