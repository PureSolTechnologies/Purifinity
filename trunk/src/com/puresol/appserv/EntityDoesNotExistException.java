package com.puresol.appserv;

public class EntityDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntityDoesNotExistException(Class<?> clazz, Object id) {
		super("Entity of type " + clazz.getName() + " with ID "
				+ String.valueOf(id) + " does not exist, but should exist!");
	}
}
