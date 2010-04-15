package com.puresol.utils;

public class DIClassBuilder<T> {

	private final Class<T> clazz;
	private final Object parameter[];
	private Object injections[];

	public DIClassBuilder(Class<T> clazz, Object... parameter) {
		this.clazz = clazz;
		this.parameter = parameter;
	}

	public void setInjection(Object... injections) {
		this.injections = injections;
	}

	public T createInstance() throws ClassInstantiationException {
		T instance = Instances.createInstance(clazz, parameter);
		@SuppressWarnings("unchecked")
		T instanceT = (T) DependencyInjection.inject(instance, injections);
		return instanceT;

	}
}
