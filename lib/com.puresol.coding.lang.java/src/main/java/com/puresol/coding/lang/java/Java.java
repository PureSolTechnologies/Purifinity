package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.osgi.framework.BundleContext;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.api.Analyzer;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Java extends AbstractProgrammingLanguage {

    private static final String[] FILE_SUFFIXES = { ".java" };

    private static Java instance = null;

    public static Java getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new Java();
	}
    }

    private BundleContext bundleContext;

    public Java() {
	super("Java", "1.6");
    }

    public BundleContext getBundleContext() {
	return bundleContext;
    }

    public void setBundleContext(BundleContext context) {
	this.bundleContext = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public Analyzer restoreAnalyzer(File file) throws IOException {
	try {
	    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
		    file));
	    try {
		return (Analyzer) ois.readObject();
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
    public Analyzer createAnalyser(File file) {
	return new JavaAnalyzer(file);
    }

    @Override
    public Grammar getGrammar() {
	return JavaGrammar.getInstance();
    }
}
