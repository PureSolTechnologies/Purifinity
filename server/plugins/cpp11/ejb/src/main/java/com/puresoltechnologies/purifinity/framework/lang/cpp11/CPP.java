package com.puresoltechnologies.purifinity.framework.lang.cpp11;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;

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
		super("C++", new Version(11, 0, 0));
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
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		return null;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		return null;
	}

}