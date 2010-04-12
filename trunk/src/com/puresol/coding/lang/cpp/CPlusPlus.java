package com.puresol.coding.lang.cpp;

import com.puresol.coding.AbstractProgrammingLanguage;

public class CPlusPlus extends AbstractProgrammingLanguage {

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
}
