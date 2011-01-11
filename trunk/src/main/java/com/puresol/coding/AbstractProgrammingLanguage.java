package com.puresol.coding;

import java.io.File;

public abstract class AbstractProgrammingLanguage implements
		ProgrammingLanguage {

	private static final long serialVersionUID = -8029643842163952482L;

	private final String name;

	protected AbstractProgrammingLanguage(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
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
