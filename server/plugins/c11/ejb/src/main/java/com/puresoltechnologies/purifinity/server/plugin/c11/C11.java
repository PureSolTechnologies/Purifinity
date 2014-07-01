package com.puresoltechnologies.purifinity.server.plugin.c11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.plugin.c11.grammar.C11Grammar;

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

    public static final String NAME = "C";
    public static final String VERSION = "11";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String[] FILE_SUFFIXES = { ".h", ".c" };

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

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
    public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
	return configurationParameters;
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
