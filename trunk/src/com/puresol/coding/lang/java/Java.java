package com.puresol.coding.lang.java;

import com.puresol.coding.AbstractProgrammingLanguage;

public class Java extends AbstractProgrammingLanguage {

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

}
