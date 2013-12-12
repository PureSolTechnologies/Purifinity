package com.puresoltechnologies.commons.utils;

public enum HashAlgorithm {

	MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512(
			"SHA-512");

	private final String algorithmName;

	private HashAlgorithm(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * This method returns a {@link HashId} for a given string value. The value
	 * is the algorithm name retrieved before by {@link #getAlgorithmName()}.
	 * 
	 * @param algorithmName
	 *            is the name of the algorithm to be checked for a valid
	 *            algorithm name.
	 * @return Contains the {@link HashAlgorithm} value for the specified value.
	 *         If the hash algorithm is not found, null is returned.
	 */
	public static HashAlgorithm fromAlgorithmName(String algorithmName) {
		for (HashAlgorithm algorithm : values()) {
			if (algorithm.getAlgorithmName().equals(algorithmName)) {
				return algorithm;
			}
		}
		return null;
	}
}
