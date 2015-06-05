package com.puresoltechnologies.purifinity.framework.lang.cpp11;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;

public class CPP extends AbstractProgrammingLanguageAnalyzer {

	private static final String[] FILE_PATTERNS = { ".hpp", ".hxx", ".cpp",
			".cxx" };

	private static final String FILE_PATTERNS_PROPERY = "analyzer.cpp.source.suffixes";

	private static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();
	static {
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"C++ Source File Patterns",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a list of file patterns in regular expression format which are used to mark C++ sources. Each pattern is placed on its own line.",
						String.class, FILE_PATTERNS_PROPERY, "/",
						patternsToString(FILE_PATTERNS)));

	}

	private String[] filePatterns = FILE_PATTERNS;

	private CPP() {
		super("C++", "11");
	}

	@Override
	public LanguageGrammar getGrammar() {
		return null;
	}

	@Override
	protected String[] getValidFilePatterns() {
		return filePatterns;
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return PARAMETERS;
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		return null;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		return null;
	}

	@Override
	public void setConfigurationParameter(ConfigurationParameter<?> parameter,
			Object value) {
		if (FILE_PATTERNS_PROPERY.equals(parameter.getPropertyKey())) {
			filePatterns = stringToPatterns((String) value);
		} else {
			throw new IllegalArgumentException("Parameter '" + parameter
					+ "' is unknown.");
		}
	}

	@Override
	public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
		if (FILE_PATTERNS_PROPERY.equals(parameter.getPropertyKey())) {
			return patternsToString(filePatterns);
		} else {
			throw new IllegalArgumentException("Parameter '" + parameter
					+ "' is unknown.");
		}
	}
}
