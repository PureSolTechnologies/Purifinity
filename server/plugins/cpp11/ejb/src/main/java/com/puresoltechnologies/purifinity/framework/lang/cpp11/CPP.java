package com.puresoltechnologies.purifinity.framework.lang.cpp11;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadSymbol;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginActivatedParameter;

public class CPP extends AbstractProgrammingLanguageAnalyzer {

    public static final String[] FILE_SUFFIXES = { ".hpp", ".hxx", ".cpp",
	    ".cxx" };

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();
    static {
	configurationParameters.add(new PluginActivatedParameter());
    }

    private static CPP instance = null;

    public static CPP getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new CPP();
	}
    }

    private CPP() {
	super("C++", "11");
    }

    @Override
    public LanguageGrammar getGrammar() {
	return null;
    }

    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
	return configurationParameters;
    }

    @Override
    public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
	return null;
    }

    @Override
    public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
	return null;
    }

    @Override
    public SLOCType getType(AbstractTerminal token) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean cascadingNode(UniversalSyntaxTree node) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int increasesCyclomaticComplexityBy(AbstractProduction production) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public HalsteadSymbol getHalsteadResult(AbstractTerminal node) {
	// TODO Auto-generated method stub
	return null;
    }

}
