package com.puresoltechnologies.commons.math;

public class PhysicalValue<T> extends AbstractValue<T> {

    private static final long serialVersionUID = -2822315073839441235L;

    public PhysicalValue(T value, PhysicalParameter<T> parameter) {
	super(value, parameter);
    }

    @Override
    public PhysicalParameter<T> getParameter() {
	return (PhysicalParameter<T>) super.getParameter();
    }

}
