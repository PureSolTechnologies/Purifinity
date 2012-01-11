package com.puresol.utils;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 5493786939135627689L;

	public PersistenceException(Throwable throwable) {
		super(throwable);
	}

	public PersistenceException(String message) {
		super(message);
	}

}
