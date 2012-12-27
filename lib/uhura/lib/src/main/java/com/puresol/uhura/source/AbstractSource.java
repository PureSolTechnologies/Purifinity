package com.puresol.uhura.source;

/**
 * This class is an abstract helper class for providing basic functionality of
 * source reading to {@link Source} interface implementations.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractSource implements Source {

    private static final long serialVersionUID = 5092730926716733775L;

    @Override
    public String toString() {
	return getHumanReadableLocationString();
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
