package com.puresoltechnologies.purifinity.analysis.spi;

import java.util.regex.Pattern;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguage;

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
	 * This method returns the valid regular expression patterns for source
	 * files of this language.
	 * 
	 * @return A String array is returned containing the regular expression
	 *         patterns.
	 */
	abstract protected String[] getValidFiles();

	abstract protected Pattern[] getValidFilePatterns();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSuitable(SourceCodeLocation source) {
		String name = source.getHumanReadableLocationString();
		for (Pattern pattern : getValidFilePatterns()) {
			// XXX Think about performance improvement here! The pattern does
			// not need to be compiled over and over again!
			if (pattern.matcher(name).matches()) {
				return true;
			}
		}
		return false;
	}
}
