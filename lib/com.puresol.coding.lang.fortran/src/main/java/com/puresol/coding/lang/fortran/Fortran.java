package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.osgi.framework.BundleContext;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.Grammar;

public class Fortran extends AbstractProgrammingLanguage {

    private static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
	    ".f95", ".for" };

    private static Fortran instance = null;

    public static Fortran getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new Fortran();
	}
    }

    private SourceForm sourceForm = SourceForm.FREE_FORM;
    private BundleContext bundleContext;

    public Fortran() {
	super("Fortran", "2008");
    }

    public BundleContext getBundleContext() {
	return bundleContext;
    }

    public void setBundleContext(BundleContext context) {
	this.bundleContext = context;
    }

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
	return new FortranAnalyzer(file);
    }

    public void setSourceForm(SourceForm sourceForm) {
	this.sourceForm = sourceForm;
    }

    public SourceForm getSourceForm() {
	return sourceForm;
    }

    @Override
    public Grammar getGrammar() {
	return FortranGrammar.getInstance();
    }
}
