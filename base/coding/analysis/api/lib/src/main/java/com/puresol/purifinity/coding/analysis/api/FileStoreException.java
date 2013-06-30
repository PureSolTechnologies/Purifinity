package com.puresol.purifinity.coding.analysis.api;

/**
 * This is a checked exception for the file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileStoreException extends Exception {

	private static final long serialVersionUID = -5082132187888243921L;

	public FileStoreException() {
	}

	public FileStoreException(String message) {
		super(message);
	}

	public FileStoreException(Throwable cause) {
		super(cause);
	}

	public FileStoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
