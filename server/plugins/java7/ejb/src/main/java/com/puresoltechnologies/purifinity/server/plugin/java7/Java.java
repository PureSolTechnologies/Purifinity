package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
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
@Stateless
@Remote(ProgrammingLanguageAnalyzer.class)
public class Java extends AbstractProgrammingLanguageAnalyzer {

	public static final String ID = Java.class.getName();
	public static final String NAME = "Java";
	public static final String VERSION = "7";
	public static final Version PLUGIN_VERSION = BuildInformation.getVersion();

	public static final String[] FILE_SUFFIXES = { ".java" };

	public static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();
	static {
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"Java Source File Suffixes",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a list of comma separated file suffixes which are used to mark Java sources.",
						String.class, "analyzer.java.source.suffixes", "/",
						"java"));

	}

	public Java() {
		super(NAME, VERSION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public List<ConfigurationParameter<?>> getConfigurationParameters() {
		return PARAMETERS;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			try {
				return (CodeAnalyzer) ois.readObject();
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
	public CodeAnalyzer createAnalyser(SourceCodeLocation sourceCodeLocation) {
		return new JavaAnalyzer(sourceCodeLocation);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return JavaGrammar.getInstance();
	}
}
