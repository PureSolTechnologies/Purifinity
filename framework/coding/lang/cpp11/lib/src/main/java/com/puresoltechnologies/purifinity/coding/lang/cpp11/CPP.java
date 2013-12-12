package com.puresoltechnologies.purifinity.coding.lang.cpp11;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import com.puresoltechnologies.commons.ConfigurationParameter;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.coding.analysis.impl.AbstractProgrammingLanguageAnalyzer;
=======
import com.puresoltechnologies.commons.configuration.ConfigurationParameter;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeAnalyzer;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9
import com.puresoltechnologies.purifinity.lang.api.LanguageGrammar;

public class CPP extends AbstractProgrammingLanguageAnalyzer {

	public static final String[] FILE_SUFFIXES = { ".hpp", ".hxx", ".cpp",
			".cxx" };

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();
	private static CPP instance = null;

	public static CPP getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new CPP();
		}
	}

	private CPP() {
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
		return FILE_SUFFIXES;
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	public CodeAnalyzer createAnalyser(CodeLocation source) {
		return null;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		return null;
	}

}
