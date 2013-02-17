package com.puresol.coding.lang.cpp;

import com.puresol.coding.lang.api.LanguageGrammar;
import com.puresol.coding.lang.commons.AbstractProgrammingLanguage;

public class CPP extends AbstractProgrammingLanguage {

	public CPP(String name, String version) {
		super("C++", "11");
	}

	@Override
	public LanguageGrammar getGrammar() {
		return null;
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		return null;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return null;
	}

}
