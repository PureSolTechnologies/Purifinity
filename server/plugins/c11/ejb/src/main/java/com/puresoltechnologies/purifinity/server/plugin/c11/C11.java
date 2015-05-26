package com.puresoltechnologies.purifinity.server.plugin.c11;

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

	public static final String[] FILE_SUFFIXES = { ".h", ".c" };

	public static final List<ConfigurationParameter<?>> PARAMETERS = new ArrayList<>();
	static {
		PARAMETERS
				.add(new ConfigurationParameter<String>(
						"C Source File Suffixes",
						"",
						LevelOfMeasurement.NOMINAL,
						"Specifies a list of comma separated file suffixes which are used to mark C sources.",
						String.class, "analyzer.c.source.suffixes", "/", "h,c"));

	}

	public C11() {
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
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		return new C11Analyzer(source);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return C11Grammar.getInstance();
	}

}
