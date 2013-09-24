package com.puresol.purifinity.coding.metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.lang.java.Java;

public class TestProgrammingLanguages extends ProgrammingLanguages {

	private static final List<AnalyzableProgrammingLanguage> languages = new ArrayList<>();
	static {
		languages.add(new Java());
	}

	@Override
	public void close() throws IOException {

	}

	@Override
	public List<AnalyzableProgrammingLanguage> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
