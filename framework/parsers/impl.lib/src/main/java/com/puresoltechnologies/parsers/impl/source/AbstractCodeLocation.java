package com.puresoltechnologies.parsers.impl.source;

import com.puresoltechnologies.parsers.api.source.CodeLocation;

/**
 * This class is an abstract helper class for providing basic functionality of
 * source reading to {@link CodeLocation} interface implementations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractCodeLocation implements CodeLocation {

	private static final long serialVersionUID = 5092730926716733775L;

	@Override
	public final String toString() {
		return getHumanReadableLocationString();
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

}
