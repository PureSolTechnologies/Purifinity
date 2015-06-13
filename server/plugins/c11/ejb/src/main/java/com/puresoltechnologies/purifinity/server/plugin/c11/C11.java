package com.puresoltechnologies.purifinity.server.plugin.c11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.io.FileSearch;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.plugin.c11.grammar.C11Grammar;
import com.puresoltechnologies.versioning.Version;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Stateless
@Remote(ProgrammingLanguageAnalyzer.class)
public class C11 extends AbstractProgrammingLanguageAnalyzer {

	public static final String ID = C11.class.getName();
	public static final String NAME = "C";
	public static final String VERSION = "11";
	public static final Version PLUGIN_VERSION = BuildInformation.getVersion();

	private static final String[] FILE_PATTERNS = { "*.h", "*.c" };

	private static final String FILE_PATTERNS_PROPERY = "analyzer.c.source.suffixes";

	public static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();
	static {
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"C Source File Patterns",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a list of file patterns in regular expression format which are used to mark C sources. Each pattern is placed on its own line.",
						String.class, FILE_PATTERNS_PROPERY, "/",
						patternsToString(FILE_PATTERNS)));

	}

	private String[] validFiles;
	private Pattern[] validFilePatterns;

	public C11() {
		super(NAME, VERSION);
		setValidFiles(FILE_PATTERNS);
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
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return PARAMETERS;
	}

	@Override
	public C11Analyzer restoreAnalyzer(File file) throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			try {
				return (C11Analyzer) ois.readObject();
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
	public C11Analyzer createAnalyser(SourceCode sourceCode, HashId hashId) {
		return new C11Analyzer(sourceCode, hashId);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return C11Grammar.getInstance();
	}

	@Override
	public void setConfigurationParameter(ConfigurationParameter<?> parameter,
			Object value) {
		if (FILE_PATTERNS_PROPERY.equals(parameter.getPropertyKey())) {
			setValidFiles(stringToPatterns((String) value));
		} else {
			throw new IllegalArgumentException("Parameter '" + parameter
					+ "' is unknown.");
		}
	}

	private void setValidFiles(String[] files) {
		validFiles = files;
		validFilePatterns = new Pattern[validFiles.length];
		for (int i = 0; i < validFiles.length; i++) {
			validFilePatterns[i] = Pattern.compile(FileSearch
					.wildcardsToRegExp(validFiles[i]));
		}
	}

	@Override
	public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
		if (FILE_PATTERNS_PROPERY.equals(parameter.getPropertyKey())) {
			return patternsToString(validFiles);
		} else {
			throw new IllegalArgumentException("Parameter '" + parameter
					+ "' is unknown.");
		}
	}
}
