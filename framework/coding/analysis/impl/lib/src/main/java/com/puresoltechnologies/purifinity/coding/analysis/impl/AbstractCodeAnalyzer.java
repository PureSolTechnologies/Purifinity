package com.puresoltechnologies.purifinity.coding.analysis.impl;

import com.puresoltechnologies.parsers.api.source.CodeLocation;
<<<<<<< HEAD:framework/coding/analysis/impl/lib/src/main/java/com/puresoltechnologies/purifinity/coding/analysis/impl/AbstractCodeAnalyzer.java
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
=======
import com.puresoltechnologies.purifinity.coding.lang.commons.AbstractLanguageGrammar;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9:framework/coding/analysis/api/lib/src/main/java/com/puresoltechnologies/purifinity/coding/analysis/api/AbstractCodeAnalyzer.java

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
