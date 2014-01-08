package com.puresoltechnologies.commons.math;

public class GeneralValue<T> extends AbstractValue<T> {

	private static final long serialVersionUID = 7987925099878079811L;

	public GeneralValue(T value, ParameterWithArbitraryUnit<T> parameter) {
		super(value, parameter);
	}

	@Override
	public ParameterWithArbitraryUnit<T> getParameter() {
		return (ParameterWithArbitraryUnit<T>) super.getParameter();
	}

}