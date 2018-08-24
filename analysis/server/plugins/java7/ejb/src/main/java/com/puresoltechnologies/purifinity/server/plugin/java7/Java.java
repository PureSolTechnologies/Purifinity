package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearch;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;
import com.puresoltechnologies.versioning.Version;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateful
@Remote(ProgrammingLanguageAnalyzer.class)
public class Java extends AbstractProgrammingLanguageAnalyzer {

    public static final String ID = Java.class.getName();
    public static final String NAME = "Java";
    public static final String VERSION = "7";
    public static final Version PLUGIN_VERSION = BuildInformation.getVersion();

    private static final String[] FILE_PATTERNS = { "*.java" };

    private static final String FILE_PATTERNS_PROPERTY = "analyzer.java.source.patterns";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {
	    new ConfigurationParameter<String>("Java Source File Pattern", "", LevelOfMeasurement.NOMINAL,
		    "Specifies a list of file patterns in regular expression syntax which are used to mark Java sources. One line is on pattern.",
		    String.class, FILE_PATTERNS_PROPERTY, "/", patternsToString(FILE_PATTERNS)) };

    private String[] validFiles;
    private Pattern[] validFilePatterns;

    public Java() {
	super(NAME, VERSION);
	setValidFilePatterns(FILE_PATTERNS);
    }

    private void setValidFilePatterns(String[] validFiles) {
	this.validFiles = validFiles;
	validFilePatterns = new Pattern[validFiles.length];
	for (int i = 0; i < validFiles.length; i++) {
	    validFilePatterns[i] = Pattern.compile(FileSearch.wildcardsToRegExp(validFiles[i]));
	}
    }

    /**
     * {@inheritDoc}
     */
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
    public JavaAnalyzer restoreAnalyzer(File file) throws IOException {
	try {
	    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
	    try {
		return (JavaAnalyzer) ois.readObject();
	    } finally {
		ois.close();
	    }
	} catch (ClassNotFoundException e) {
	    /*
	     * XXX This needs to be null to go on with the language try out...
	     * :-(
	     */
	    return null;
	}
    }

    @Override
    public CodeAnalyzer createAnalyser(SourceCode sourceCode, HashId hashId) {
	return new JavaAnalyzer(sourceCode, hashId);
    }

    @Override
    public LanguageGrammar getGrammar() {
	return JavaGrammar.getInstance();
    }

    @Override
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value) {
	if (FILE_PATTERNS_PROPERTY.equals(parameter.getPropertyKey())) {
	    setValidFilePatterns(stringToPatterns((String) value));
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	if (FILE_PATTERNS_PROPERTY.equals(parameter.getPropertyKey())) {
	    return patternsToString(getValidFiles());
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }
}
