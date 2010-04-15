package com.puresol.utils.di;

import java.util.ArrayList;
import java.util.List;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class DIClassBuilder {

	public static DIClassBuilder forInjections(Object... injections) {
		return new DIClassBuilder(injections);
	}

	private final List<Object> injections = new ArrayList<Object>();

	private DIClassBuilder(Object... injections) {
		addInjections(injections);
	}

	public void addInjections(Object... injections) {
		for (Object injection : injections) {
			this.injections.add(injection);
		}
	}

	public <T> T createInstance(Class<T> clazz, Object... parameter)
			throws ClassInstantiationException {
		T instance = Instances.createInstance(clazz, parameter);
		return DependencyInjection.inject(instance, injections.toArray());
	}
}
