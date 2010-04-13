package com.puresol.coding.lang.cpp;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyser;

public class CPlusPlus extends AbstractProgrammingLanguage {

	private static final String[] FILE_SUFFIXES = { ".h", ".c", ".hpp", ".cpp",
			".hxx", ".cxx" };

	private static CPlusPlus instance = null;

	public static CPlusPlus getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new CPlusPlus();
		}
	}

	private CPlusPlus() {
		super("C++");
	}

	@Override
	public boolean isObjectOriented() {
		return true;
	}

	@Override
	protected Class<? extends Analyser> getAnalyserClass() {
		return CPPAnalyser.class;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

}
