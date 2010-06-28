package com.puresol.coding;

import java.io.File;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

abstract public class AbstractProgrammingLanguage implements
		ProgrammingLanguage {

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
	 * This method returns the class for the related Analyser.
	 * 
	 * @return The class for the Analyser is returned which has to be of
	 *         interface Analyser.
	 */
	abstract protected Class<? extends Analyzer> getAnalyserClass();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Analyzer createAnalyser(File file)
			throws ClassInstantiationException {
		return Instances.createInstance(getAnalyserClass(), file);
	}

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
