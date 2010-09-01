package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
	protected Class<? extends Analyzer> getAnalyserClass() {
		return FortranAnalyser.class;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Analyzer analyzer = (Analyzer) ois.readObject();
			ois.close();
			return analyzer;
		} catch (Throwable e) {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e1) {
				}
			}
			throw new PersistenceException(e);
		}
	}
}
