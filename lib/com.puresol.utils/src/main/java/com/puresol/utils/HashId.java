package com.puresol.utils;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HashId {

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

}
