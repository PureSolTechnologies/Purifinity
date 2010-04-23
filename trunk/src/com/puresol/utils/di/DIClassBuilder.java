package com.puresol.utils.di;

import java.util.ArrayList;
import java.util.List;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class DIClassBuilder {

    public static DIClassBuilder forInjections(Injection... injections) {
	return new DIClassBuilder(injections);
    }

    private final List<Injection> injectionsList = new ArrayList<Injection>();

    private DIClassBuilder(Injection... injections) {
	addInjections(injections);
    }

    public void addInjections(Injection... injections) {
	for (Injection injection : injections) {
	    this.injectionsList.add(injection);
	}
    }

    public <T> T createInstance(Class<T> clazz, Object... parameter)
	    throws ClassInstantiationException {
	T instance = Instances.createInstance(clazz, parameter);
	return DependencyInjection.inject(instance, injectionsList
		.toArray(new Injection[0]));
    }
}
