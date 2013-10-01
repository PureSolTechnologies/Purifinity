package com.puresol.purifinity.coding.metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.lang.java.Java;

public class TestProgrammingLanguages extends ProgrammingLanguages {

	private static final List<ProgrammingLanguageAnalyzer> languages = new ArrayList<>();
	static {
		languages.add(Java.getInstance());
	}

	@Override
	public void close() throws IOException {

	}

	@Override
	public List<ProgrammingLanguageAnalyzer> getAll() {
		return languages;
	}

}
