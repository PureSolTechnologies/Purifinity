package com.puresoltechnologies.purifinity.coding.analysis.api;

import com.puresoltechnologies.commons.utils.FileSearchConfiguration;

public abstract class AbstractRepositoryLocation implements RepositoryLocation {

	private static final long serialVersionUID = -3692883413027085776L;

	private final String name;
	private FileSearchConfiguration codeSearchConfiguration = new FileSearchConfiguration();

	public AbstractRepositoryLocation(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * This method returns the set {@link FileSearchConfiguration}.
	 * 
	 * @return An object of {@link FileSearchConfiguration} is returned.
	 */
	@Override
	public FileSearchConfiguration getCodeSearchConfiguration() {
		return codeSearchConfiguration.clone();
	}

	/**
	 * This method sets a new {@link FileSearchConfiguration}.
	 * 
	 * @param codeSearchConfiguration
	 */
	@Override
	public void setCodeSearchConfiguration(
			FileSearchConfiguration codeSearchConfiguration) {
		this.codeSearchConfiguration = codeSearchConfiguration.clone();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codeSearchConfiguration == null) ? 0
						: codeSearchConfiguration.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractRepositoryLocation other = (AbstractRepositoryLocation) obj;
		if (codeSearchConfiguration == null) {
			if (other.codeSearchConfiguration != null)
				return false;
		} else if (!codeSearchConfiguration
				.equals(other.codeSearchConfiguration))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
