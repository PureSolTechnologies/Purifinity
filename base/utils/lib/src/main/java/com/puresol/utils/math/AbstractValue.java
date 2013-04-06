package com.puresol.utils.math;

public abstract class AbstractValue<T> implements Value<T> {

    private static final long serialVersionUID = 1L;

    private final T value;
    private final Parameter<T> property;

    public AbstractValue(T value, Parameter<T> property) {
	super();
	this.value = value;
	this.property = property;
    }

    @Override
    public T getValue() {
	return value;
    }

    @Override
    public Parameter<T> getParameter() {
	return property;
    }

    @Override
    public String toString() {
	return value.toString();
    }

}
