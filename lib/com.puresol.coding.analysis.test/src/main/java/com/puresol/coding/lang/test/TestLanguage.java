package com.puresol.coding.lang.test;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.test.grammar.TestLanguageGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.utils.PersistenceException;

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

    private static final Logger logger = LoggerFactory
	    .getLogger(TestLanguage.class);

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
    public Analyzer restoreAnalyzer(File file) throws PersistenceException {
	throw new PersistenceException(
		"Persistence not implemented in TestProgrammingLanguage!");
    }

    @Override
    public Analyzer createAnalyser(File file) {
	return new TestLanguageAnalyser(file);
    }

    @Override
    public Grammar getGrammar() {
	return TestLanguageGrammar.getInstance();
    }
}
