package com.puresol.utils.math;

public class PhysicalValueWithMoney<T> implements Value<T> {

	private final T value;
	private final PhysicalParameterWithMoney property;

	public PhysicalValueWithMoney(T value, PhysicalParameterWithMoney property) {
		super();
		this.value = value;
		this.property = property;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public PhysicalParameterWithMoney getParameter() {
		return property;
	}

}
