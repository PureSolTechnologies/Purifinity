package com.puresol.coding.lang.fortran;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyser;

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

	private Fortran() {
		super("Fortran");
	}

	@Override
	public boolean isObjectOriented() {
		return false;
	}

	@Override
	protected Class<? extends Analyser> getAnalyserClass() {
		return FortranAnalyser.class;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}
}
