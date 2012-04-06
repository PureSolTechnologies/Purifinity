package com.puresol.coding.lang.test;

import java.io.File;
import java.io.IOException;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.api.FileAnalyzer;
import com.puresol.coding.lang.test.grammar.TestLanguageGrammar;
import com.puresol.uhura.grammar.Grammar;

/**
 * This is a test programming languages which is used as a mock up for real
 * languages.
 * 
 * This language is strictly for testing purposes only!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguage extends AbstractProgrammingLanguage {

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

    public TestLanguage() {
	super("TestLanguage", "0.0");
    }

    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public FileAnalyzer restoreAnalyzer(File file) throws IOException {
	throw new IOException(
		"Persistence not implemented in TestProgrammingLanguage!");
    }

    @Override
    public FileAnalyzer createAnalyser(File file) {
	return new TestLanguageAnalyser(file);
    }

    @Override
    public Grammar getGrammar() {
	return TestLanguageGrammar.getInstance();
    }
}
