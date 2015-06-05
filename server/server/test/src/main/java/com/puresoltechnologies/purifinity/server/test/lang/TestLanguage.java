package com.puresoltechnologies.purifinity.server.test.lang;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
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
    protected String[] getValidFilePatterns() {
	return FILE_SUFFIXES;
    }

    @Override
    public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
	throw new IOException(
		"Persistence not implemented in TestProgrammingLanguage!");
    }

    @Override
    public CodeAnalyzer createAnalyser(SourceCodeLocation sourceCodeLocation) {
	return new TestLanguageAnalyser(sourceCodeLocation);
    }

    @Override
    public LanguageGrammar getGrammar() {
	return TestLanguageGrammar.getInstance();
    }
}
