package com.puresoltechnologies.purifinity.framework.evaluation.metrics;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.framework.analysis.impl.ProgrammingLanguages;
import com.puresoltechnologies.purifinity.framework.lang.java7.Java;

public class TestProgrammingLanguages extends ProgrammingLanguages {

	private static final List<ProgrammingLanguageAnalyzer> languages = new ArrayList<>();
	static {
		languages.add(Java.getInstance());
	}

	@Override
	public void close() {
		// intentionally left blanks
	}

	@Override
	public List<ProgrammingLanguageAnalyzer> getAll() {
		return languages;
	}

}
