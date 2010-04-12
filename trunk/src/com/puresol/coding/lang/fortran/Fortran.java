package com.puresol.coding.lang.fortran;

import com.puresol.coding.AbstractProgrammingLanguage;

public class Fortran extends AbstractProgrammingLanguage {

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
}
