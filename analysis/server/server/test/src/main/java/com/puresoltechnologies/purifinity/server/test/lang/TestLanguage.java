package com.puresoltechnologies.purifinity.server.test.lang;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.AnalyzerStore;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguage;
import com.puresoltechnologies.purifinity.server.test.lang.grammar.TestLanguageGrammar;

/**
 * This is a test programming languages which is used as a mock up for real
 * languages.
 * 
 * This language is strictly for testing purposes only!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguage extends AbstractProgrammingLanguage implements
		AnalyzerStore {

	private static final String[] FILE_SUFFIXES = { ".d" };

	private static TestLanguage instance = null;

	public static TestLanguage getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TestLanguage();
		}
	}

	private TestLanguage() {
		super("TestLanguage", "0.0.0");
	}

	@Override
	protected String[] getValidFiles() {
		return FILE_SUFFIXES;
	}

	@Override
	protected Pattern[] getValidFilePatterns() {
		Pattern[] patterns = new Pattern[FILE_SUFFIXES.length];
		for (int i = 0; i < FILE_SUFFIXES.length; i++) {
			patterns[i] = Pattern.compile(FILE_SUFFIXES[i]);
		}
		return patterns;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		throw new IOException(
				"Persistence not implemented in TestProgrammingLanguage!");
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCode sourceCode, HashId hashId) {
		return new TestLanguageAnalyser(sourceCode, hashId);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return TestLanguageGrammar.getInstance();
	}
}
