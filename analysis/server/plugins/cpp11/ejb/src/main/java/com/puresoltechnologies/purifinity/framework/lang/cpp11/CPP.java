package com.puresoltechnologies.purifinity.framework.lang.cpp11;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearch;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;

public class CPP extends AbstractProgrammingLanguageAnalyzer {

    private static final String[] FILE_PATTERNS = { "*.hpp", "*.hxx", "*.cpp", "*.cxx" };

    private static final String FILE_PATTERNS_PROPERY = "analyzer.cpp.source.suffixes";

    private static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {
	    new ConfigurationParameter<String>("C++ Source File Patterns", "", LevelOfMeasurement.NOMINAL,
		    "Specifies a list of file patterns in regular expression format which are used to mark C++ sources. Each pattern is placed on its own line.",
		    String.class, FILE_PATTERNS_PROPERY, "/", patternsToString(FILE_PATTERNS)) };

    private String[] validFiles;
    private Pattern[] validFilePatterns;

    private CPP() {
	super("C++", "11");
	setValidFiles(FILE_PATTERNS);
    }

    @Override
    public LanguageGrammar getGrammar() {
	return null;
    }

    @Override
    protected String[] getValidFiles() {
	return validFiles;
    }

    @Override
    protected Pattern[] getValidFilePatterns() {
	return validFilePatterns;
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return PARAMETERS;
    }

    @Override
    public CodeAnalyzer createAnalyser(SourceCode sourceCode, HashId hashId) {
	return null;
    }

    @Override
    public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
	return null;
    }

    @Override
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value) {
	if (FILE_PATTERNS_PROPERY.equals(parameter.getPropertyKey())) {
	    setValidFiles(stringToPatterns((String) value));
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }

    private void setValidFiles(String[] files) {
	validFiles = files;
	validFilePatterns = new Pattern[validFiles.length];
	for (int i = 0; i < validFiles.length; i++) {
	    validFilePatterns[i] = Pattern.compile(FileSearch.wildcardsToRegExp(validFiles[i]));
	}
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	if (FILE_PATTERNS_PROPERY.equals(parameter.getPropertyKey())) {
	    return patternsToString(validFiles);
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }
}
