package com.puresoltechnologies.purifinity.coding.metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.coding.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.coding.lang.java7.Java;

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
