package com.puresol.utils;

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

}
