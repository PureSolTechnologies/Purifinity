package com.puresol.coding.analysis.api;

/**
 * This is a checked exception for the file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeStoreException extends Exception {

    private static final long serialVersionUID = -5082132187888243921L;

    public CodeStoreException() {
    }

    public CodeStoreException(String message) {
	super(message);
    }

    public CodeStoreException(Throwable cause) {
	super(cause);
    }

    public CodeStoreException(String message, Throwable cause) {
	super(message, cause);
    }

}
