package com.puresol.coding;

import java.io.File;

public abstract class AbstractProgrammingLanguage implements
	ProgrammingLanguage {

    private final String name;
    private final String version;

    protected AbstractProgrammingLanguage(String name, String version) {
	this.name = name;
	this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getName() {
	return name;
    }

    @Override
    public final String getVersion() {
	return version;
    }

    /**
     * This method returns the valid suffixes for source files of the language.
     * 
     * @return A String array is returned containing the suffixes.
     */
    abstract protected String[] getValidFileSuffixes();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSuitable(File file) {
	String name = file.getName();
	for (String suffix : getValidFileSuffixes()) {
	    if (name.endsWith(suffix)) {
		return true;
	    }
	}
	return false;
    }

}
