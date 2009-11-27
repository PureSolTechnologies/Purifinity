package com.puresol.appserv;

public class BeanNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public BeanNotAvailableException(String name, Class<?> cast) {
		super("Bean '" + name + "' of type " + cast.getName()
				+ " is not available on server!");
	}
}
