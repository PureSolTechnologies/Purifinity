package com.puresol.entities.forms;

public class IllegalEntityException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalEntityException(Object entity) {
		super("Entity '" + entity.getClass().getName()
				+ "' has illegal format!");
	}

}
