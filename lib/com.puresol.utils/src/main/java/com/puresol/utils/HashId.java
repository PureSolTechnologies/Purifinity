package com.puresol.utils;

import java.io.Serializable;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HashId implements Serializable {

    private static final long serialVersionUID = 1219606473615058203L;

    /**
     * This is the name of the algorithm used to calculate the hash.
     */
    private final HashAlgorithm algorithm;

    /**
     * This is the actual hash which is used as id.
     */
    private final String hash;

    /**
     * This is the initial value constructor for this hash id.
     * 
     * @param algorithm
     *            is the identifier of the algorithm used.
     * @param hash
     *            is the hash.
     */
    public HashId(HashAlgorithm algorithm, String hash) {
	super();
	this.algorithm = algorithm;
	this.hash = hash;
    }

    public HashAlgorithm getAlgorithm() {
	return algorithm;
    }

    public String getHash() {
	return hash;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((algorithm == null) ? 0 : algorithm.hashCode());
	result = prime * result + ((hash == null) ? 0 : hash.hashCode());
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
	HashId other = (HashId) obj;
	if (algorithm != other.algorithm)
	    return false;
	if (hash == null) {
	    if (other.hash != null)
		return false;
	} else if (!hash.equals(other.hash))
	    return false;
	return true;
    }

}
