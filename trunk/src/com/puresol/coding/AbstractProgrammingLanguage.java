package com.puresol.coding;

import java.io.File;

import com.puresol.coding.analysis.Analyser;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

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

	abstract protected String[] getValidFileSuffixes();

	abstract protected Class<? extends Analyser> getAnalyserClass();

	@Override
	public Analyser createAnalyser(File directory, File file)
			throws ClassInstantiationException {
		return Instances.createInstance(getAnalyserClass(), directory, file);
	}

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
