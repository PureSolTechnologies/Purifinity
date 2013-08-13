package com.puresol.purifinity.coding.analysis.api;

import com.puresol.purifinity.coding.lang.commons.AbstractLanguageGrammar;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.ust.USTCreator;
import com.puresol.purifinity.uhura.ust.USTCreatorFactory;

public abstract class AbstractCodeAnalyzer implements CodeAnalyzer {

	private final CodeLocation sourceCodeLocation;
	private final transient AbstractLanguageGrammar grammar;
	private final USTCreator ustCreator;

	public AbstractCodeAnalyzer(CodeLocation sourceCodeLocation,
			AbstractLanguageGrammar grammar,
			Class<? extends USTCreator> ustRootNodeClassCreator) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.grammar = grammar;
		this.ustCreator = USTCreatorFactory.create(ustRootNodeClassCreator
				.getPackage());
	}

	@Override
	public final CodeLocation getSource() {
		return sourceCodeLocation;
	}

	public AbstractLanguageGrammar getGrammar() {
		return grammar;
	}

	public USTCreator getUstCreator() {
		return ustCreator;
	}

}
