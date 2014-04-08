package com.puresol.passwordstore.client;

import java.security.Principal;

/**
 * This principal is used for normal, registered shop users. The registered
 * users just have the very basic rights.
 * 
 * @author Rick-Rainer Ludwig
 */
public class NamePrincipal implements Principal {

	private final String name;

	public NamePrincipal(String name) {
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
		NamePrincipal other = (NamePrincipal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("[principal: ");
		buffer.append(name);
		buffer.append("]");
		return buffer.toString();
	}
}
