package com.puresol.coding.analysis.api.storage;

/**
 * This is a checked exception for the file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ModuleStoreException extends Exception {

	private static final long serialVersionUID = -5082132187888243921L;

	public ModuleStoreException() {
	}

	public ModuleStoreException(String message) {
		super(message);
	}

	public ModuleStoreException(Throwable cause) {
		super(cause);
	}

	public ModuleStoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
