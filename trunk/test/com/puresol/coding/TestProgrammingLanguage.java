package com.puresol.coding;

public class TestProgrammingLanguage extends AbstractProgrammingLanguage {

    private static TestProgrammingLanguage instance = null;

    public static TestProgrammingLanguage getInstance() {
	if (instance == null) {
	    createInstance();
	}
	return instance;
    }

    private static synchronized void createInstance() {
	if (instance == null) {
	    instance = new TestProgrammingLanguage();
	}
    }

    private TestProgrammingLanguage() {
	super("Language");
    }

    @Override
    public boolean isObjectOriented() {
	return true;
    }

}
