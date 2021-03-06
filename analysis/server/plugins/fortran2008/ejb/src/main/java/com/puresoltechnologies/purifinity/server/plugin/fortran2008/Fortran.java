package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearch;
import com.puresoltechnologies.parsers.preprocessor.PreprocessorException;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.C11Preprocessor;
import com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.DefinedMacros;
import com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.IncludeDirectories;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;
import com.puresoltechnologies.versioning.Version;

@Stateful
@Remote(ProgrammingLanguageAnalyzer.class)
public class Fortran extends AbstractProgrammingLanguageAnalyzer {

    public static final String ID = Fortran.class.getName();
    public static final String NAME = "Fortran";
    public static final String VERSION = "2008";
    public static final Version PLUGIN_VERSION = BuildInformation.getVersion();

    private static final String[] DEFAULT_FIXED_FORM_FILE_SUFFIXES = { "*.f", "*.for" };
    private static final String[] DEFAULT_FREE_FORM_FILE_SUFFIXES = { "*.f90", "*.f95", "*.f03", "*.f08" };

    private static final String FIXED_FORM_FILE_PATTERNS_PROPERTY = "suffixes.form.fixed";
    private static final String FREE_FORM_FILE_PATTERNS_PROPERTY = "suffixes.form.free";
    private static final String C_PRE_PROCESSOR_USAGE_PROPERTY = "preprocessor.usage";
    private static final String AUTOMATIC_SOURCE_FORM_PROPERTY = "form.automatic";
    private static final String STRICT_FORM_CHECK_PROPERTY = "form.check.strict";

    public static final ConfigurationParameter<?>[] PARAMETERS = new ConfigurationParameter<?>[] {
	    new ConfigurationParameter<String>("Fortran Source File Patterns for Fixed Form", "",
		    LevelOfMeasurement.NOMINAL,
		    "Specifies a list of file patterns in regular expression format which are to be use to identify pre Fortran 90 fixed form sources. Each pattern is placed on its own line.",
		    String.class, FIXED_FORM_FILE_PATTERNS_PROPERTY, "/Source Files",
		    patternsToString(DEFAULT_FIXED_FORM_FILE_SUFFIXES)),
	    new ConfigurationParameter<String>("Fortran Source File Patterns for Free Form", "",
		    LevelOfMeasurement.NOMINAL,
		    "Specifies a list of file patterns in regular expression format which are to be use to identify Fortran 90 free form sources. Each pattern is placed on its own line.",
		    String.class, FREE_FORM_FILE_PATTERNS_PROPERTY, "/Source Files",
		    patternsToString(DEFAULT_FREE_FORM_FILE_SUFFIXES)),
	    new ConfigurationParameter<Boolean>("Automatic Source Form Identification", "", LevelOfMeasurement.NOMINAL,
		    "If checked, the parser tries to identify the source form (fixed or free) automaticaly. Default is true, because it is cleaner to specify source form via file suffix.",
		    Boolean.class, AUTOMATIC_SOURCE_FORM_PROPERTY, "/Source Files", true),
	    new ConfigurationParameter<Boolean>("Use C Pre-processor", "", LevelOfMeasurement.NOMINAL,
		    "Specifies wether a C pre-processor is to be used before analysis.", Boolean.class,
		    C_PRE_PROCESSOR_USAGE_PROPERTY, "/Source Files", false),
	    new ConfigurationParameter<Boolean>("Strict Fixed Form check", "", LevelOfMeasurement.NOMINAL,
		    "Specifies wether the source form is to be checked very strict.", Boolean.class,
		    STRICT_FORM_CHECK_PROPERTY, "/Source Files", true) };

    @Inject
    private Logger logger;

    private String[] freeFormFiles;
    private String[] fixedFormFiles;
    private Pattern[] fixedFormFilePatterns;
    private Pattern[] freeFormFilePatterns;
    private boolean usePreProcessor = false;
    private boolean automatedFormIdentification = false;
    private boolean strictFormCheck = true;

    public Fortran() {
	super(NAME, VERSION);
	setValidFixedFormFiles(DEFAULT_FIXED_FORM_FILE_SUFFIXES);
	setValidFreeFormFiles(DEFAULT_FREE_FORM_FILE_SUFFIXES);
    }

    @Override
    protected String[] getValidFiles() {
	List<String> patternsList = new ArrayList<>();
	patternsList.addAll(Arrays.asList(fixedFormFiles));
	patternsList.addAll(Arrays.asList(freeFormFiles));
	return patternsList.toArray(new String[patternsList.size()]);
    }

    @Override
    protected Pattern[] getValidFilePatterns() {
	List<Pattern> patternsList = new ArrayList<>();
	patternsList.addAll(Arrays.asList(fixedFormFilePatterns));
	patternsList.addAll(Arrays.asList(freeFormFilePatterns));
	return patternsList.toArray(new Pattern[patternsList.size()]);
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return PARAMETERS;
    }

    @Override
    public FortranAnalyzer restoreAnalyzer(File file) throws IOException {
	try {
	    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
	    try {
		return (FortranAnalyzer) ois.readObject();
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
    public FortranAnalyzer createAnalyser(SourceCode sourceCode, HashId hashId) {
	if (usePreProcessor) {
	    try {
		IncludeDirectories includeDirectories = new IncludeDirectories();
		DefinedMacros definedMacros = new DefinedMacros();
		C11Preprocessor c11Preprocessor = new C11Preprocessor(includeDirectories, definedMacros);
		sourceCode = c11Preprocessor.process(sourceCode);
	    } catch (PreprocessorException e) {
		logger.warn("Could not pre-process source code '" + sourceCode + "'.", e);
	    }
	}
	return new FortranAnalyzer(sourceCode, hashId, automatedFormIdentification, fixedFormFilePatterns,
		freeFormFilePatterns, strictFormCheck);
    }

    @Override
    public LanguageGrammar getGrammar() {
	return FortranGrammar.getInstance();
    }

    @Override
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value) {
	if (FIXED_FORM_FILE_PATTERNS_PROPERTY.equals(parameter.getPropertyKey())) {
	    setValidFixedFormFiles(stringToPatterns((String) value));
	} else if (FREE_FORM_FILE_PATTERNS_PROPERTY.equals(parameter.getPropertyKey())) {
	    setValidFreeFormFiles(stringToPatterns((String) value));
	} else if (C_PRE_PROCESSOR_USAGE_PROPERTY.equals(parameter.getPropertyKey())) {
	    setUsePreProcessor((Boolean) value);
	} else if (AUTOMATIC_SOURCE_FORM_PROPERTY.equals(parameter.getPropertyKey())) {
	    setAutomatedFormIdentification((Boolean) value);
	} else if (STRICT_FORM_CHECK_PROPERTY.equals(parameter.getPropertyKey())) {
	    setStrictFormCheck((Boolean) value);
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }

    private void setValidFixedFormFiles(String[] files) {
	fixedFormFiles = files;
	fixedFormFilePatterns = new Pattern[fixedFormFiles.length];
	for (int i = 0; i < fixedFormFiles.length; i++) {
	    fixedFormFilePatterns[i] = Pattern.compile(FileSearch.wildcardsToRegExp(fixedFormFiles[i]));
	}
    }

    private void setValidFreeFormFiles(String[] files) {
	freeFormFiles = files;
	freeFormFilePatterns = new Pattern[freeFormFiles.length];
	for (int i = 0; i < freeFormFiles.length; i++) {
	    freeFormFilePatterns[i] = Pattern.compile(FileSearch.wildcardsToRegExp(freeFormFiles[i]));
	}
    }

    public boolean isUsePreProcessor() {
	return usePreProcessor;
    }

    private void setUsePreProcessor(boolean usePreProcessor) {
	this.usePreProcessor = usePreProcessor;
    }

    private void setAutomatedFormIdentification(boolean automatedFormIdentification) {
	this.automatedFormIdentification = automatedFormIdentification;
    }

    private void setStrictFormCheck(boolean strictFormCheck) {
	this.strictFormCheck = strictFormCheck;
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	if (FIXED_FORM_FILE_PATTERNS_PROPERTY.equals(parameter.getPropertyKey())) {
	    return patternsToString(fixedFormFiles);
	} else if (FREE_FORM_FILE_PATTERNS_PROPERTY.equals(parameter.getPropertyKey())) {
	    return patternsToString(freeFormFiles);
	} else if (C_PRE_PROCESSOR_USAGE_PROPERTY.equals(parameter.getPropertyKey())) {
	    return usePreProcessor;
	} else if (AUTOMATIC_SOURCE_FORM_PROPERTY.equals(parameter.getPropertyKey())) {
	    return automatedFormIdentification;
	} else if (STRICT_FORM_CHECK_PROPERTY.equals(parameter.getPropertyKey())) {
	    return strictFormCheck;
	} else {
	    throw new IllegalArgumentException("Parameter '" + parameter + "' is unknown.");
	}
    }
}
