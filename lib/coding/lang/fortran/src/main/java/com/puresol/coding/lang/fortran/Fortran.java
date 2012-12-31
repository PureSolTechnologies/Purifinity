package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

import org.osgi.framework.BundleContext;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.lang.common.LanguageGrammar;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.source.CodeLocation;

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
    public CodeAnalyzer createAnalyser(CodeLocation sourceCodeLocation) {
	return new FortranAnalyzer(sourceCodeLocation);
    }

    public void setSourceForm(SourceForm sourceForm) {
	this.sourceForm = sourceForm;
    }

    public SourceForm getSourceForm() {
	return sourceForm;
    }

    @Override
    public LanguageGrammar getGrammar() {
	return FortranGrammar.getInstance();
    }

    @Override
    public <T> T getImplementation(Class<T> clazz) {
	ServiceLoader<T> service = ServiceLoader.load(clazz);
	Iterator<T> iterator = service.iterator();
	T result = iterator.next();
	if (iterator.hasNext()) {
	    throw new RuntimeException(
		    "There is more than one implementation available for '"
			    + clazz.getName() + "'!");
	}
	return result;
    }
}
