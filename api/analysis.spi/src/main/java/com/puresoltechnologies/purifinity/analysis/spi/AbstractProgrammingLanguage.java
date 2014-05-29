package com.puresoltechnologies.purifinity.analysis.spi;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;

public abstract class AbstractProgrammingLanguage implements
		ProgrammingLanguage {

	private final String name;
	private final Version version;

	protected AbstractProgrammingLanguage(String name, Version version) {
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
	public final Version getVersion() {
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
	public boolean isSuitable(SourceCodeLocation source) {
		String name = source.getHumanReadableLocationString();
		for (String suffix : getValidFileSuffixes()) {
			if (name.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}
}
