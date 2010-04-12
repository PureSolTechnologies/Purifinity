package com.puresol.coding;

abstract public class AbstractProgrammingLanguage implements
	ProgrammingLanguage {

    private final String name;

    protected AbstractProgrammingLanguage(String name) {
	this.name = name;
    }

    @Override
    public final String getName() {
	return name;
    }

}
