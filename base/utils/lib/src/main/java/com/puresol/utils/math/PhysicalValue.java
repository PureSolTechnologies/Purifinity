package com.puresol.utils.math;

public class PhysicalValue<T> implements Value<T> {

    private static final long serialVersionUID = -2822315073839441235L;

    private final T value;
    private final PhysicalParameter<T> property;

    public PhysicalValue(T value, PhysicalParameter<T> property) {
	super();
	this.value = value;
	this.property = property;
    }

    @Override
    public T getValue() {
	return value;
    }

    @Override
    public PhysicalParameter<T> getParameter() {
	return property;
    }

}
