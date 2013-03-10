package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.IOException;

import com.puresol.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.lang.api.LanguageGrammar;
import com.puresol.coding.lang.commons.AbstractProgrammingLanguage;
import com.puresol.uhura.source.CodeLocation;

public class CPP extends AbstractProgrammingLanguage implements
	AnalyzableProgrammingLanguage {

    private static final String[] FILE_SUFFIXES = { ".cpp", ".cxx" };

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

    public CPP() {
	super("C++", "11");
    }

    @Override
    public LanguageGrammar getGrammar() {
	return null;
    }

    @Override
    public <T> T getImplementation(Class<T> clazz) {
	return null;
    }

    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public CodeAnalyzer createAnalyser(CodeLocation source) {
	return null;
    }

    @Override
    public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
	return null;
    }

}
