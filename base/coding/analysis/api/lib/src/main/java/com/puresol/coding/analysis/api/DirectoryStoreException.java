package com.puresol.coding.analysis.api;

/**
 * This is a checked exception for the file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DirectoryStoreException extends Exception {

	private static final long serialVersionUID = -5082132187888243921L;

	public DirectoryStoreException() {
	}

	public DirectoryStoreException(String message) {
		super(message);
	}

	public DirectoryStoreException(Throwable cause) {
		super(cause);
	}

	public DirectoryStoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
