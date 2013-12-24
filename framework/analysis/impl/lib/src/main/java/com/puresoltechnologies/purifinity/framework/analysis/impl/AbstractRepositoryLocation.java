package com.puresoltechnologies.purifinity.framework.analysis.impl;

import com.puresoltechnologies.parsers.api.source.RepositoryLocation;

public abstract class AbstractRepositoryLocation implements RepositoryLocation {

	private static final long serialVersionUID = -3692883413027085776L;

	private final String name;

	public AbstractRepositoryLocation(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
