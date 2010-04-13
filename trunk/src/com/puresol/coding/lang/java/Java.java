package com.puresol.coding.lang.java;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyser;

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

	private Java() {
		super("Java");
	}

	@Override
	public boolean isObjectOriented() {
		return true;
	}

	@Override
	protected Class<? extends Analyser> getAnalyserClass() {
		return JavaAnalyser.class;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

}
