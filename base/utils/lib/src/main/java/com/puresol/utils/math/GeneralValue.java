package com.puresol.utils.math;

public class GeneralValue<T> implements Value<T> {

    private final T value;
    private final ParameterWithArbitraryUnit<T> property;

    public GeneralValue(T value, ParameterWithArbitraryUnit<T> property) {
	super();
	this.value = value;
	this.property = property;
    }

    @Override
    public T getValue() {
	return value;
    }

    @Override
    public ParameterWithArbitraryUnit<T> getParameter() {
	return property;
    }

}