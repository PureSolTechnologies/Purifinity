package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadSymbol;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginActivatedParameter;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.CodeDepthMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.HalsteadMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.McCabeMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.SLOCMetricImpl;
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
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String[] FILE_SUFFIXES = { ".java" };

    public static final Set<ConfigurationParameter<?>> PARAMETERS = new HashSet<>();
    static {
	PARAMETERS.add(new PluginActivatedParameter());
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
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
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

    @Override
    public SLOCType getType(AbstractTerminal token) {
	return new SLOCMetricImpl().getType(token);
    }

    @Override
    public boolean cascadingNode(UniversalSyntaxTree node) {
	return new CodeDepthMetricImpl().cascadingNode(node);
    }

    @Override
    public int increasesCyclomaticComplexityBy(AbstractProduction production) {
	return new McCabeMetricImpl()
		.increasesCyclomaticComplexityBy(production);
    }

    @Override
    public HalsteadSymbol getHalsteadResult(AbstractTerminal node) {
	return new HalsteadMetricImpl().getHalsteadResult(node);
    }
}
