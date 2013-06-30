package com.puresol.purifinity.utils;

public class ClassInstantiationException extends Exception {

	private static final long serialVersionUID = 5727964992904503204L;

	public ClassInstantiationException(Class<?> clazz, Class<?>[] params) {
		super("Could not instantiate class '" + clazz.getName()
				+ "' with constructor (" + params.toString() + ")!");
	}

	public ClassInstantiationException(String className, Class<?>[] params) {
		super("Could not instantiate class '" + className
				+ "' with constructor (" + params.toString() + ")!");
	}

}
